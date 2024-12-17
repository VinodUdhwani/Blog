package com.blog.Blog.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    private Set<User> users;

    public Role(int id, String name, Set<User> user) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Role(){
        super();
    }

    public Set<User> getUser() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
