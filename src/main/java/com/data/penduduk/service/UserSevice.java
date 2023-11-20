package com.data.penduduk.service;

import com.data.penduduk.model.UserModel;

import java.util.Map;

public interface UserSevice {

    UserModel register(UserModel userModel);

    Map<String, Object> login(UserModel userModel);

    UserModel getById(Long id);

    UserModel update(Long d, UserModel userModel);

    Map<String, Boolean> delete(Long id);
}
