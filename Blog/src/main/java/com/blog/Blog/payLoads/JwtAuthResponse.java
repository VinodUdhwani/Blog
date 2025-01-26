package com.blog.Blog.payLoads;

public class JwtAuthResponse {

    private String token;

    private UserDto userDto;

    public JwtAuthResponse(){
        super();
    }

    public JwtAuthResponse(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
