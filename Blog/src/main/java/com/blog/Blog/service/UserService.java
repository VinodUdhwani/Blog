package com.blog.Blog.service;

import com.blog.Blog.payLoads.UserDto;


import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUser();

    void deleteUser(Integer userId);
}
