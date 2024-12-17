package com.blog.Blog.entity;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;

    public Comment(int commentId, String comment, User user, Post post) {
        this.commentId = commentId;
        this.comment = comment;
        this.user = user;
        this.post = post;
    }
    public Comment(){
        super();
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
