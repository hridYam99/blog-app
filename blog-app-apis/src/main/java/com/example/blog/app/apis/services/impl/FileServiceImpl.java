package com.example.blog.app.apis.services.impl;

import com.example.blog.app.apis.services.FilseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FilseService {
    @Override
    public String uploadImage(String path, MultipartFile file){
//        File name
        String fileName = file.getOriginalFilename();

//        Full Path
        String filePath = path + File.separator+ fileName;

//        Create folder if not
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
//        Copy file
        try {
            Files.copy(file.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("image not uploaded");
        }

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

}
