package com.blog.Blog.security;

import com.blog.Blog.service.implementation.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");

        // Bearer 2354263dlsc

        System.out.println("token: "+requestToken);

        String username=null;
        String token=null;

        if(requestToken!=null && requestToken.startsWith("Bearer")){

            token=requestToken.substring(7);
            try{
                username=this.jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException();
            }catch (ExpiredJwtException e){
                throw new RuntimeException("token has expired");
            }catch (MalformedJwtException e){
                throw new MalformedJwtException("invalid jwt");
            }
        }else {
            System.out.println("request Token is null or request Token is not start with Bearer");
        }

        //once we get token now validate

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token,userDetails)){
                //it is going right now we need to do authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("invalid jwt token");
            }

        }else {
            System.out.println("username is null or security context is not null");
        }

        filterChain.doFilter(request,response);
    }
}
