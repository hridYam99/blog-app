package com.example.blog.app.apis.controllers;

import com.example.blog.app.apis.payloads.ApiResponse;
import com.example.blog.app.apis.payloads.FileResponse;
import com.example.blog.app.apis.payloads.PostDto;
import com.example.blog.app.apis.payloads.PostResponse;
import com.example.blog.app.apis.services.FilseService;
import com.example.blog.app.apis.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.sql.Struct;
import java.util.List;

@Controller
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    FilseService filseService;

    @Value("${blog.image}")
    private String path;

//    Post
    @PostMapping("/user/{userId}/category/{categoryId}")
    ResponseEntity<PostDto> createPost(PostDto postDto, @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId){
        PostDto postDto1 = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

//    GET

    @GetMapping("posts/{postId}")
    ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer id){
        PostDto postDto = postService.getPost(id);
        return new ResponseEntity<>(postDto,HttpStatus.FOUND);
    }

    @GetMapping("/posts")
    ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSizer", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "asc", required = false) String sortBy
    ){
        return new ResponseEntity<>(postService.getAllPost(pageNumber,pageSize, sortBy),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getPostByUser(userId),HttpStatus.FOUND);
    }

    @GetMapping("category/{categoryId}/posts")
    ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(postService.getPostByCategoty(categoryId),HttpStatus.FOUND);
    }

//    @GetMapping("keyword/{keyword/posts}")
//    ResponseEntity<List<PostDto>> getPostByKeyword(@PathVariable("keyword") String keyword){
//        return new ResponseEntity<>(postService.searchPost(keyword),HttpStatus.FOUND);
//    }

//    Delete
    @DeleteMapping("/posts/{postId}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>( new ApiResponse( "post deleted", true),HttpStatus.OK);
    }

//    Update
    @PutMapping("/posts/{postId}")
    ResponseEntity<PostDto> updatePost(@PathVariable("postId") Integer postId, @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.updatePost(postId,postDto), HttpStatus.OK);
    }

    @PostMapping("/posts/uploadImage{postId}")
    ResponseEntity<FileResponse> uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable("postId") Integer postId
            ) throws IOException {
        String fileName = null;
        try {
            fileName = filseService.uploadImage(path , image);
        } catch (RuntimeException e){
            return new ResponseEntity<>(new FileResponse(fileName, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto postDto = postService.getPost(postId);
        postDto.setImageName(fileName);
        PostDto updatedPost = postService.updatePost(postId,postDto);
        return new ResponseEntity<>(new FileResponse(fileName, "file uploaded successfully"), HttpStatus.OK);
    }

//    Get
    @GetMapping(value = "/posts/download/{imgName}", produces = MediaType.IMAGE_JPEG_VALUE)
    void downloadImg(@PathVariable("imgName") String imgName, HttpServletResponse response) throws IOException {
        InputStream is = filseService.getResource(path , imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is,response.getOutputStream());
    }


}
