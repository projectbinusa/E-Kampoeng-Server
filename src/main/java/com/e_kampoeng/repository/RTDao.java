package com.e_kampoeng.repository;

import com.e_kampoeng.model.RTModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RTDao extends JpaRepository<RTModel, Long> {
}
