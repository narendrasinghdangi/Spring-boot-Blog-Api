package com.blog.blog_apis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog_apis.entities.Category;
import com.blog.blog_apis.entities.Post;
import com.blog.blog_apis.entities.User;
import com.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_apis.payloads.PostDto;
import com.blog.blog_apis.payloads.PostResponse;
import com.blog.blog_apis.repositories.CategoryRepo;
import com.blog.blog_apis.repositories.PostRepo;
import com.blog.blog_apis.repositories.UserRepo;
import com.blog.blog_apis.services.PostServices;


@Service
public class PostServiceImpl implements PostServices{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto PostDto,Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId)
        .orElseThrow(()-> new ResourceNotFoundException("User", "UserID", userId));

        Category category =this.categoryRepo.findById(categoryId)
        .orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        Post post = this.modelMapper.map(PostDto, Post.class);
        post.setUser(user);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
        .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
        
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
        .orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
        .orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir) {
        Sort sort=null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
        .orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
        .orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.searchByTitle(keyword);
        List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    
}
