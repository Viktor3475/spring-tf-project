package com.demo.spring_tf_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
// WebSecurity Configuration for Spring Security
    @Bean
    public UserDetailsService userDetailsService(){
        // create in memory authentication user and admin accounts
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build(); // build user with username, password and role
        manager.createUser(user);
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build(); // build admin with username, password and role
        manager.createUser(admin);

        return manager; // load user specific data
    }


    @Bean
    public SecurityFilterChain redirect(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                (authorize) ->
                    authorize.requestMatchers("/update/**")
                            .hasRole("ADMIN").requestMatchers("/delete/**")
                            .hasRole("ADMIN").anyRequest().authenticated()
        ).formLogin(
                (config) ->
                        config.loginPage("/login").permitAll()
                                .defaultSuccessUrl("/getusers", true)
        );

        // when the login form on /login is submitted successfully
        // then redirect all authenticated users to /getusers(index.html) (list with users)
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // encode password with
        // BCrypt algorithm
    }
}
