package com.blog.Blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadFile(String path, MultipartFile multipartFile) throws IOException;
    InputStream getResource(String path,String imageName) throws FileNotFoundException;
}
