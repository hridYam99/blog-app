package com.example.blog.app.apis.services.impl;

import com.example.blog.app.apis.entities.Category;
import com.example.blog.app.apis.entities.Post;
import com.example.blog.app.apis.entities.User;
import com.example.blog.app.apis.execptions.ResourseNotFoundExecption;
import com.example.blog.app.apis.payloads.PostDto;
import com.example.blog.app.apis.payloads.PostResponse;
import com.example.blog.app.apis.repositories.CategoryRepo;
import com.example.blog.app.apis.repositories.PostRepo;
import com.example.blog.app.apis.repositories.UserRepo;
import com.example.blog.app.apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto getPost(Integer id) {
        Post post = postRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("Post", "id", id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByUser(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("User", "userId", id));
        List<Post> allPosts = postRepo.findByUser(user);

        List<PostDto> allPostDtos= new ArrayList<>();

        for(Post post : allPosts){
            allPostDtos.add(modelMapper.map(post, PostDto.class));
        }

        return allPostDtos;
    }

    @Override
    public List<PostDto> getPostByCategoty(Integer id) {
        Category cat = categoryRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("Category", "categoryId", id));
        List<Post> allPosts = postRepo.findByCategory(cat);

        List<PostDto> allPostDtos= new ArrayList<>();

        for(Post post : allPosts){
            allPostDtos.add(modelMapper.map(post, PostDto.class));
        }

        return allPostDtos;
    }

//    @Override
//    public List<PostDto> searchPost(String keyword) {
//        List<Post> posts = postRepo.
//        return null;
//    }

    @Override
    public PostResponse getAllPost(Integer pageNumber , Integer pageSize, String sortBy) {
        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPost = pagePost.getContent();
        PostResponse postResponse = new PostResponse();
        List<PostDto> postDtos =  allPost.stream().map((post)->modelMapper.map(post,PostDto.class))
                .toList();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        postResponse.setTotalElements(pagePost.getTotalElements());

        return postResponse;

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourseNotFoundExecption("Post", "id", postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourseNotFoundExecption("User", "userId", userId));
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourseNotFoundExecption("Category", "categoryId", categoryId));

        Post post = modelMapper.map(postDto,Post.class);
        post.setAddedDate(new Date());
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);

        postRepo.save(post);

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto updatePost(Integer postId, PostDto postDto) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourseNotFoundExecption("Post", "postId",postId));

        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }
}
