package com.example.blog.app.apis.payloads;

public class FileResponse {
    private String fileName;
    private String msg;

    public FileResponse(String fileName,String msg) {
        this.fileName = fileName;
        this.msg = msg;
    }
}
