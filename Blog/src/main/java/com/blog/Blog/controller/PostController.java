package com.blog.Blog.controller;

import com.blog.Blog.configuration.AppConstants;
import com.blog.Blog.payLoads.FileResponse;
import com.blog.Blog.payLoads.PostDto;
import com.blog.Blog.payLoads.PostResponse;
import com.blog.Blog.service.FileService;
import com.blog.Blog.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/blog/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto createdPost=postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPost= postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("post deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }

    @GetMapping("/")
    public PostResponse getALlPost(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
                                   @RequestParam(value = "sortBy",required = false,defaultValue = AppConstants.SORT_BY_DATE)String sortBy,
                                   @RequestParam(value = "sortDirection",required = false,defaultValue =AppConstants.SORT_DIRECTION)String sortDirection){
        return postService.getALlPost(pageNumber,pageSize,sortBy,sortDirection);
    }

    @GetMapping("/category/{categoryId}")
    public List<PostDto> getPostByCategory(@PathVariable Integer categoryId){
        return postService.getPostByCategory(categoryId);
    }

    @GetMapping("/user/{userId}")
    public List<PostDto> getPostByUser(@PathVariable Integer userId) {
        return postService.getPostByUser(userId);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keywords") String keywords){
        List<PostDto> postsDto = postService.searchPost(keywords);
        return new ResponseEntity<>(postsDto,HttpStatus.FOUND);
    }

    @PostMapping("/upload/image/{postId}")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile multipartFile, @PathVariable Integer postId){
        try {
            PostDto postDto=postService.getPostById(postId);
            String fileName=this.fileService.uploadFile(path,multipartFile);
            postDto.setImageName(fileName);
            PostDto updatePost = this.postService.updatePost(postDto, postId);
            return new ResponseEntity<>(new FileResponse(fileName,"file uploaded successfully",true),HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(null,"file not uploaded due to server error",false),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/image/{imageName}")
    public void getFile(@PathVariable String imageName, HttpServletResponse httpServletResponse){
        try {
            InputStream inputStream=this.fileService.getResource(path,imageName);
            httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(inputStream,httpServletResponse.getOutputStream());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
