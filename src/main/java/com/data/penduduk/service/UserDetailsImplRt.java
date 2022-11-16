package com.data.penduduk.service;

import com.data.penduduk.model.Rt;
import com.data.penduduk.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImplRt implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String username;

    private String role;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplRt(Long id, String email, String password, String username, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public static UserDetailsImplRt build(Rt rt) {
        return new UserDetailsImplRt(
                rt.getId(),
                rt.getEmail(),
                rt.getPassword(),
                rt.getUsername(),
                rt.getRole()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImplRt rt = (UserDetailsImplRt) o;
        return Objects.equals(id, rt.id);
    }
}
