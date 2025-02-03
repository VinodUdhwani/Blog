package com.blog.Blog.service.implementation;

import com.blog.Blog.entity.Category;
import com.blog.Blog.entity.Post;
import com.blog.Blog.entity.User;
import com.blog.Blog.exceptions.ResourceNotFoundException;
import com.blog.Blog.payLoads.PostDto;
import com.blog.Blog.payLoads.PostResponse;
import com.blog.Blog.repositories.CategoryRepository;
import com.blog.Blog.repositories.PostRepository;
import com.blog.Blog.repositories.UserRepository;
import com.blog.Blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));

        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        
        Post post=modelMapper.map(postDto,Post.class);
        post.setPostTitle(postDto.getPostTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post createdPost=postRepository.save(post);
        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        post.setPostTitle(postDto.getPostTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());

        Post updatedPost=postRepository.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getALlPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection) {
        Sort sort=sortDirection.equals("ascending")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePosts = postRepository.findAll(pageable);
        List<Post> pagePostsContent = pagePosts.getContent();
        List<PostDto>postsDto=pagePostsContent.stream().map((post -> modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postsDto);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Post","categoryId",categoryId));
        List<PostDto> postsDto=postRepository.findByCategory(category).stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Post","userId",userId));
        List<PostDto> postsDto=postRepository.findByUser(user).stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return postRepository.searchByTitle("%"+keyword+"%").stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }
}
