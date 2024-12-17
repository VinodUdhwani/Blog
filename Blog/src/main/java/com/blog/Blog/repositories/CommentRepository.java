package com.blog.Blog.repositories;

import com.blog.Blog.entity.Comment;
import com.blog.Blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
