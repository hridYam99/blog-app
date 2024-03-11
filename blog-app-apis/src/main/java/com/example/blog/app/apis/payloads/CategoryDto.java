package com.example.blog.app.apis.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    @NotNull
    private String categoryTitle;
    @NotNull
    private String categoryDesc;
}
