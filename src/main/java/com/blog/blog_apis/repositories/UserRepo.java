package com.blog.blog_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_apis.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
    
}
