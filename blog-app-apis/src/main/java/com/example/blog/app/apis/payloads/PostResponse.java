package com.example.blog.app.apis.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor
@Getter@Setter
public class PostResponse {
    private List<PostDto> content = new ArrayList<>();
    private Integer pageNumber, pageSize, totalPages;
    Long totalElements;
    private Boolean lastPage;
}
