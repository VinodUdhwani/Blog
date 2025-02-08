package com.blog.Blog.repositories;

import com.blog.Blog.entity.Comment;
import com.blog.Blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.post = :post")
    void deleteAllCommentsByPost(@Param("post") Post post);
}
