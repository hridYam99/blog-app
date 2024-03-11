package com.example.blog.app.apis.payloads;

import com.example.blog.app.apis.entities.Category;
import com.example.blog.app.apis.entities.User;

import java.util.Date;

public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private Category category;
    private User user;
}
