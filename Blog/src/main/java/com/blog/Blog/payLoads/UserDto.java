package com.blog.Blog.payLoads;

import com.blog.Blog.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4,message = "name must be contain minimum 4 character")
    private String name;

    @Email
    private String email;

    //    @Pattern(regexp = "")
    @NotEmpty
    @Size(min = 4,message = "password must be contain minimum 4 character")
    private String password;

    @NotEmpty
    private String about;

    private Set<Role> roles=new HashSet<>();

    public UserDto(String about, String email, int id, String name, String password) {
        this.about = about;
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserDto(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
