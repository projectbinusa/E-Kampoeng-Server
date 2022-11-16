package com.data.penduduk.payload.response;

public class JwtResponseRt {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;

    private String username;

    private String role;

    public JwtResponseRt(String accessToken, Long id, String email, String username, String role) {
        this.token = accessToken;
        this.type = type;
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
