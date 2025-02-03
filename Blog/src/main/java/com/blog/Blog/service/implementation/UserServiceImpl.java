package com.blog.Blog.service.implementation;

import com.blog.Blog.configuration.AppConstants;
import com.blog.Blog.entity.Role;
import com.blog.Blog.entity.User;
import com.blog.Blog.exceptions.ResourceNotFoundException;
import com.blog.Blog.payLoads.UserDto;
import com.blog.Blog.repositories.RoleRepository;
import com.blog.Blog.repositories.UserRepository;
import com.blog.Blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = this.roleRepository.findById(AppConstants.Role_NORMAL).get();
        user.getRoles().add(role);

        User savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User saveUser=this.userRepository.save(user);
        return this.userToDto(saveUser);
    }
 
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User saveUser=this.userRepository.save(user);
        return this.userToDto(saveUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = users.stream().map((user -> this.userToDto(user))).collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserByEmail(String username) {
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user", "email" + username, 0));
        return modelMapper.map(user,UserDto.class);
    }

    private User dtoToUser(UserDto userDto){
        User user=modelMapper.map(userDto, User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user){
        UserDto userDto=modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
