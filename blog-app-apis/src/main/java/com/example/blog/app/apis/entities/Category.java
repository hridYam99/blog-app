package com.example.blog.app.apis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Category Table")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Category Title")
    private String categoryTitle;

    @Column(name = "Category Description")
    private String categoryDesc;

//    fetchtype
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<Post> allPosts = new ArrayList<>();
}
