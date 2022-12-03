package com.data.penduduk.payload.response;

import com.data.penduduk.model.Rt;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;

    private String username;

    private String role;

    @JsonIgnore
    private List<Rt> rt;

    public JwtResponse(String accessToken, Long id, String username, String role, List<Rt> rt) {
        this.token = accessToken;
        this.type = type;
        this.id = id;
        this.username = username;
        this.role = role;
        this.rt = rt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Rt> getRt() {
        return rt;
    }

    public void setRt(List<Rt> rt) {
        this.rt = rt;
    }
}
