package com.data.penduduk.service;

import com.data.penduduk.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface User extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);

    UserModel findById(long userId);
}