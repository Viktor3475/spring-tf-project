package com.demo.spring_tf_project.controller;

import com.demo.spring_tf_project.entity.User;
import com.demo.spring_tf_project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Model attributes which are supplying with data the templates
    @ModelAttribute("users") // list of all users which are created
    public List<User> populateList(){
        return userService.findAll();
    }

    @ModelAttribute("user") // user which we are creating or updating
    public User newUser(){
        return new User();
    }


    @GetMapping("/getusers") // lists all available users
    public String getUsers(Model model){
        model.getAttribute("users"); // supply index.html with the users list
        return "view/index"; // return index page
    }


    @GetMapping("/create") // get create page to submit form for creating a user
    public String createPage(Model model){

        model.getAttribute("user"); // supply the form with the attributes of the new user
        return "view/create";
    }

    @PostMapping("/adduser")// action which is performed when the form in create page is submitted
    public String userPost(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if(result.hasErrors()){ // we are using BindingResult to validate that the attributes of the created user are valid
            // if the validation of user has error, then the page will be redirected again to create page
            return "redirect:/create";
        }
        userService.save(user); // otherwise if the validation is OK, then the user will be saved
        return "redirect:/getusers"; // redirect to the getusers
    }


    @GetMapping("/update/{id}") // page with update form
    public String updatePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id)); // supplying the update template(update.html)
        // with the found user by its id
        return "view/update"; // return update template
    }

    @PostMapping("/update/{id}") // action which is performed when form is submitted in update.html
    public String userPut(@ModelAttribute("user") @Valid User user, BindingResult result, @PathVariable("id") long id) {
        if(result.hasErrors()){ // if the validation of user has errors then we are redirected again to update.html
            return "redirect:/update/{id}";
        }
        user.setId(id); // otherwise set the id of the user which we are updating, if the id is not set, then new user will be created
        userService.save(user); // save the updated data of the existing user
        return "redirect:/getusers"; // redirect to index.html (list wil all users)
    }

    @GetMapping("/delete/{id}") // deleting user
    public String userDelete(@PathVariable("id") long id){
        userService.deleteById(id); // delete user by its id
        return "redirect:/getusers"; // redirect to index.html (list with all users)
    }

}
