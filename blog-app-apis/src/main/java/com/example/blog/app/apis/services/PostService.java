package com.example.blog.app.apis.services;

import com.example.blog.app.apis.payloads.PostDto;
import com.example.blog.app.apis.payloads.PostResponse;
import com.example.blog.app.apis.repositories.PostRepo;

import java.util.List;

public interface PostService {
//    GET POST BY ID
    PostDto getPost(Integer id);
//    GET POST BY USER
    List<PostDto> getPostByUser(Integer id);
//    GET POST BY CATEGORY
    List<PostDto> getPostByCategoty(Integer id);
//    SEARCH POST BY KEYWORD
//    List<PostDto> searchPost(String keyword);
//    GET ALL
    PostResponse getAllPost(Integer pageNumber , Integer pageSize, String sortBy);
//    DELETE
    void deletePost(Integer postId);
//    CREATE
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
//    UPDATE
    PostDto updatePost(Integer postId, PostDto postDto);
}
