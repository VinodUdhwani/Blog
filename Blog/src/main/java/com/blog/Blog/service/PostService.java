package com.blog.Blog.service;

import com.blog.Blog.payLoads.PostDto;
import com.blog.Blog.payLoads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getALlPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection);

    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);
}
