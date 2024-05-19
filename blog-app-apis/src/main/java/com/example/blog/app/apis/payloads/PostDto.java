package com.example.blog.app.apis.payloads;

import com.example.blog.app.apis.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto categoryDto;
    private  PostDto postDto;
    private Set<CommentDto> comments = new HashSet<>();

}
