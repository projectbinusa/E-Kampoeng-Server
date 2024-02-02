package com.e_kampoeng.repository;

import com.e_kampoeng.model.RWModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RWDao extends JpaRepository<RWModel, Long> {
}
