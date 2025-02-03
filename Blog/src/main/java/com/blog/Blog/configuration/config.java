package com.blog.Blog.configuration;

import com.blog.Blog.entity.Post;
import com.blog.Blog.payLoads.PostDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        // Configure the mapping for nested DTOs
        modelMapper.addMappings(new PropertyMap<Post, PostDto>() {
            @Override
            protected void configure() {
                map(source.getCategory(), destination.getCategoryDto());
                map(source.getUser(), destination.getUserDto());
                map(source.getComment(), destination.getCommentDto());
            }
        });

        return modelMapper;
    }
}
