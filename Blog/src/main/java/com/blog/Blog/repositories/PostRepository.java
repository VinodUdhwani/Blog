package com.blog.Blog.repositories;

import com.blog.Blog.entity.Category;
import com.blog.Blog.entity.Post;
import com.blog.Blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.postTitle like :key")
    List<Post> searchByTitle(@Param("key") String title);


    @Modifying
    @Transactional
    @Query("DELETE FROM Post p WHERE p.postId = :id")
    void deleteByIdCustom(@Param("id") Integer id);
}
