package com.blog.Blog.service.implementation;

import com.blog.Blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile multipartFile) throws IOException {

        String name= multipartFile.getOriginalFilename();

        String randomName= UUID.randomUUID().toString();
        String fileName=randomName+name.substring(name.lastIndexOf("."));

        String fullFileName=path+ File.separator+fileName;

        File file=new File(path);
        if(!file.exists())
            file.mkdir();

        Files.copy(multipartFile.getInputStream(), Path.of(fullFileName));
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String imageName) throws FileNotFoundException {
        String fileName=path+File.separator+imageName;
        InputStream inputStream=new FileInputStream(fileName);
        return inputStream;
    }
}
