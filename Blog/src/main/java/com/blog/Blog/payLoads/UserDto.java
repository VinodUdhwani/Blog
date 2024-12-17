package com.blog.Blog.payLoads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4)
    private String name;
    @Email(message = "kindly provide valid email address")
    private String email;
    @NotEmpty
    @Length(min = 8,message = "password must be contain minimum 8 character")
//    @Pattern(regexp = "")
    private String password;
    @NotEmpty
    private String about;

    private Set<RoleDto> rolesDto=new HashSet<>();

    public UserDto(String about, String email, int id, String name, String password, Set<RoleDto> rolesDto) {
        this.about = about;
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.rolesDto = rolesDto;
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

    public Set<RoleDto> getRolesDto() {
        return rolesDto;
    }

    public void setRolesDto(Set<RoleDto> rolesDto) {
        this.rolesDto = rolesDto;
    }
}
