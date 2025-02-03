package com.blog.Blog.controller;

import com.blog.Blog.payLoads.JwtAuthRequest;
import com.blog.Blog.payLoads.JwtAuthResponse;
import com.blog.Blog.payLoads.UserDto;
import com.blog.Blog.security.JwtTokenHelper;
import com.blog.Blog.service.UserService;
import com.blog.Blog.service.implementation.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken( @RequestBody JwtAuthRequest jwtAuthRequest){

        this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String generatedToken = this.jwtTokenHelper.generateToken(userDetails);
        UserDto userDto = this.userService.getUserByEmail(userDetails.getUsername());
        return new ResponseEntity<>(new JwtAuthResponse(generatedToken,userDto), HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        try{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,password);
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("invalid password !");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
    }
}
