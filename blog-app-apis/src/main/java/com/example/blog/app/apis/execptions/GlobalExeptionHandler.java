package com.example.blog.app.apis.execptions;

import com.example.blog.app.apis.payloads.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(ResourseNotFoundExecption.class)
    ResponseEntity<ApiResponse> resourseNotFoundExecption(ResourseNotFoundExecption ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }
}
