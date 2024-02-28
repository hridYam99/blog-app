package com.example.blog.app.apis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    private String CategoryTitle;
    private String CategoryDesc;
}
