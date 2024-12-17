package com.blog.Blog.payLoads;

public class JwtAuthRequest {

    private String username;
    private String password;

    public JwtAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public JwtAuthRequest(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
