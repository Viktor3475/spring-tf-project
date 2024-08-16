package com.demo.spring_tf_project.repository;

import com.demo.spring_tf_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // this repository is used for communication with our DB (H2) and the JPA repository supplies us
    // with the necessary methods for CRUD and other actions.
}
