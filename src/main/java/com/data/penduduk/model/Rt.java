package com.data.penduduk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rt")
public class Rt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "nomor_rt")
    private String nomor_rt;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "rt", cascade = CascadeType.REMOVE)
    private List<LayananWarga> layananWargas;

    @OneToMany(mappedBy = "rt", cascade = CascadeType.REMOVE)
    private List<Kk> kk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomor_rt() {
        return nomor_rt;
    }

    public void setNomor_rt(String nomor_rt) {
        this.nomor_rt = nomor_rt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Kk> getKk() {
        return kk;
    }

    public void setKk(List<Kk> kk) {
        this.kk = kk;
    }

    public List<LayananWarga> getLayananWargas() {
        return layananWargas;
    }

    public void setLayananWargas(List<LayananWarga> layananWargas) {
        this.layananWargas = layananWargas;
    }
}
