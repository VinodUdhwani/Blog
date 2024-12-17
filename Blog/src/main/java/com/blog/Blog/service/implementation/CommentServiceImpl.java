package com.blog.Blog.service.implementation;

import com.blog.Blog.entity.Comment;
import com.blog.Blog.entity.Post;
import com.blog.Blog.entity.User;
import com.blog.Blog.exceptions.ResourceNotFoundException;
import com.blog.Blog.payLoads.CommentDto;
import com.blog.Blog.repositories.CommentRepository;
import com.blog.Blog.repositories.PostRepository;
import com.blog.Blog.repositories.UserRepository;
import com.blog.Blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer userId, Integer postId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment createdComment = commentRepository.save(comment);
        return modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsOnPost(Integer postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        List<Comment> comments = post.getComment();
        return comments.stream().map(comment -> modelMapper.map(comment,CommentDto.class)).toList();
    }

}
