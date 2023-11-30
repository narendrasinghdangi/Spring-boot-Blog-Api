package com.blog.blog_apis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.blog_apis.entities.Category;
import com.blog.blog_apis.entities.Post;
import com.blog.blog_apis.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.title like %:key%")
    //@Query(value = "SELECT * FROM post p WHERE p.title LIKE %:key%",nativeQuery = true)
    List<Post> searchByTitle(@Param("key") String title);
}
