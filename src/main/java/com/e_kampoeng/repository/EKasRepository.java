package com.e_kampoeng.repository;

import com.e_kampoeng.model.EKasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EKasRepository extends JpaRepository<EKasModel, Long> {
}
