package com.data.penduduk.service;

import com.data.penduduk.model.Rt;
import com.data.penduduk.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;

    @JsonIgnore
    private String password;

    private String username;

    private String role;

    private List<Rt> rt;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String password, String username, String role, List<Rt> rt) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.role = role;
        this.rt = rt;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                user.getRole(),
                user.getRt()
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

    public List<Rt> getRt() {
        return rt;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}
