package com.example.blog.app.apis.services.impl;

import com.example.blog.app.apis.entities.Post;
import com.example.blog.app.apis.execptions.ResourseNotFoundExecption;
import com.example.blog.app.apis.payloads.PostDto;
import com.example.blog.app.apis.repositories.PostRepo;
import com.example.blog.app.apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto getPost(Integer id) {
        Post post = postRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("Post", "id", id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPostByUser(Integer id) {
        return null;
    }

    @Override
    public PostDto getPostByCategoty(Integer id) {
        return null;
    }

    @Override
    public PostDto searchPost(String keyword) {
        return null;
    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public void deletePost() {

    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto,Post.class);
        post.setAddedDate(new Date());
        post.setImageName("default.png");

        postRepo.save(post);

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        return null;
    }
}
