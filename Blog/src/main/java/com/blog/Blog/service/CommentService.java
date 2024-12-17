package com.blog.Blog.service;

import com.blog.Blog.payLoads.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

    void deleteComment(Integer commentId);

    CommentDto getCommentById(Integer commentId);

    List<CommentDto> getCommentsOnPost(Integer postId);
}
