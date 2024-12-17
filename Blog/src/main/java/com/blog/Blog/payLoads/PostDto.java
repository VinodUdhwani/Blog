package com.blog.Blog.payLoads;

import com.blog.Blog.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class PostDto {

    @NotEmpty(message = "title is required")
    private String postTitle;
    @NotEmpty
    private String content;
    @NotEmpty
    private String imageName;
    private Date addedDate;
    private CategoryDto categoryDto;
    private UserDto userDto;
    private List<CommentDto> commentDto;
    public PostDto(Date addedDate, CategoryDto categoryDto, String content, String imageName, String postTitle, UserDto userDto, List<CommentDto> commentDto) {
        this.addedDate = addedDate;
        this.categoryDto = categoryDto;
        this.content = content;
        this.imageName = imageName;
        this.postTitle = postTitle;
        this.userDto = userDto;
        this.commentDto= commentDto;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<CommentDto> getCommentDto() {
        return commentDto;
    }

    public void setCommentDto(List<CommentDto> commentDto) {
        this.commentDto = commentDto;
    }
}
