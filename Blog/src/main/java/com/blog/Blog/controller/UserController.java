package com.blog.Blog.controller;

import com.blog.Blog.payLoads.UserDto;
import com.blog.Blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){
        UserDto createdUser=userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto userDto1=userService.updateUser(userDto,userId);
        return new ResponseEntity<>(userDto1,HttpStatus.ACCEPTED);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("user deleted successfully.",HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Integer userId){
        return this.userService.getUserById(userId);
    }

    @GetMapping("/")
    public List<UserDto> getAll(){
        return this.userService.getAllUser();
    }
}
