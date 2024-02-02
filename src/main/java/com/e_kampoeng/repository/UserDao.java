package com.e_kampoeng.repository;


import com.e_kampoeng.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
}