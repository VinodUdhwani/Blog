package com.blog.Blog.payLoads;

import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentDto {
    private int commentId;
    @NotEmpty(message = "comment cannot be null")
    private String comment;

    public CommentDto(String comment, int commentId) {
        this.comment = comment;
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}

