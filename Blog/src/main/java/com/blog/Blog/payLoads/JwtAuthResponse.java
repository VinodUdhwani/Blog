package com.blog.Blog.payLoads;

public class JwtAuthResponse {

    private String token;

    public JwtAuthResponse(){
        super();
    }

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
