package com.example.blog.app.apis.execptions;

public class ResourseNotFoundExecption extends RuntimeException{
    String resourceName;
    String fieldName;
    Integer fieldVal;


    public ResourseNotFoundExecption(String resourceName, String fieldName, Integer fieldVal) {
        super(String.format("%s not found with %s : %d", resourceName, fieldName, fieldVal));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldVal = fieldVal;
    }
}
