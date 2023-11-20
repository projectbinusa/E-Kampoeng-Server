package com.data.penduduk.jwt;

import com.data.penduduk.model.UserModel;

public class JwtResponse {
    private final String token;


    public JwtResponse(String token, UserModel date) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
