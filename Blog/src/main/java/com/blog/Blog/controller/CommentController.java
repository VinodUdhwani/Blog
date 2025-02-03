package com.blog.Blog.controller;

import com.blog.Blog.payLoads.CommentDto;
import com.blog.Blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer userId,@PathVariable Integer postId){
        CommentDto createdComment=commentService.createComment(commentDto,userId,postId);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer commentId){
        CommentDto commentDto = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentOnPost(@PathVariable Integer postId){
        List<CommentDto> commentsOnPost = commentService.getCommentsOnPost(postId);
        return new ResponseEntity<>(commentsOnPost, HttpStatus.OK);
    }
}
