package com.blog.Blog.controller;

import com.blog.Blog.payLoads.JwtAuthRequest;
import com.blog.Blog.payLoads.JwtAuthResponse;
import com.blog.Blog.payLoads.UserDto;
import com.blog.Blog.security.JwtTokenHelper;
import com.blog.Blog.service.UserService;
import com.blog.Blog.service.implementation.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/secure")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest){

        this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String generatedToken = this.jwtTokenHelper.generateToken(userDetails);

        return new ResponseEntity<>(new JwtAuthResponse(generatedToken), HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
    }

}
