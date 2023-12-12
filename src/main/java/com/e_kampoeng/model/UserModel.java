package com.e_kampoeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserModel {

    //  MAKE TABLE USER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String role;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String image;


    //    getter setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}