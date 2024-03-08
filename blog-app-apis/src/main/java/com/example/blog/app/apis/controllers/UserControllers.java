package com.example.blog.app.apis.controllers;

import com.example.blog.app.apis.payloads.ApiResponse;
import com.example.blog.app.apis.payloads.UserDto;
import com.example.blog.app.apis.services.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserControllers {

    @Autowired
    UserService userService;


//    Post
    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

//    Get
    @GetMapping("/")
    private ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    Delete
    @DeleteMapping("/{userId}")
    private ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User deleted", true), HttpStatus.OK);
    }

//    Put
    @PutMapping("/{userId}")
    private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer id){
        UserDto updatedUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

