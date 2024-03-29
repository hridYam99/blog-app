package com.example.blog.app.apis.execptions;

import com.example.blog.app.apis.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(ResourseNotFoundExecption.class)
    ResponseEntity<ApiResponse> resourseNotFoundExecptionHandler(ResourseNotFoundExecption ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> methodArgumentNotValidExecption(MethodArgumentNotValidException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ApiResponse(ex.getMessage(),false),HttpStatus.NOT_FOUND);
    }
}
