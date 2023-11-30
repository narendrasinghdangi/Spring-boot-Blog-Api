package com.blog.blog_apis.services;

import java.util.List;

import com.blog.blog_apis.payloads.UserDto;

public interface UserServices {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUserById(Integer userId);
}
