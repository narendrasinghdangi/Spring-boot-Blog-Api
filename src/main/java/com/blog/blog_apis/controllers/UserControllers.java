package com.blog.blog_apis.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_apis.payloads.ApiResponse;
import com.blog.blog_apis.payloads.UserDto;
import com.blog.blog_apis.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserControllers {

    @Autowired
    private UserServices userServices;

    //Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userServices.createUser(userDto);
        return new ResponseEntity<UserDto>(createUserDto,HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updaUserDto = this.userServices.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(updaUserDto,HttpStatus.OK);
    }


    //Delete USer
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userServices.deleteUserById(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }

    //Get User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable Integer userId){
        UserDto userDto = this.userServices.getUserById(userId);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);

    }
    // Get all Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(this.userServices.getAllUsers());
    }

}
