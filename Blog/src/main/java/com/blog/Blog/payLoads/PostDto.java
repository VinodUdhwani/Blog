package com.blog.Blog.payLoads;

import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class PostDto {

    private Integer postId;
    @NotEmpty(message = "title is required")
    private String postTitle;
    @NotEmpty
    private String content;
//    @NotEmpty
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

    public List<CommentDto> getCommentDto() {
        return commentDto;
    }

    public void setCommentDto(List<CommentDto> commentDto) {
        this.commentDto = commentDto;
    }

    public @NotEmpty String getContent() {
        return content;
    }

    public void setContent(@NotEmpty String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public @NotEmpty(message = "title is required") String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(@NotEmpty(message = "title is required") String postTitle) {
        this.postTitle = postTitle;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
