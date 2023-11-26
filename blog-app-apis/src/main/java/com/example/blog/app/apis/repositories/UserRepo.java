package com.example.blog.app.apis.repositories;

import com.example.blog.app.apis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
