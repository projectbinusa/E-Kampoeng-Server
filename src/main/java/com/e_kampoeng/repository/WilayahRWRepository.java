package com.e_kampoeng.repository;

import com.e_kampoeng.model.WilayahRWModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WilayahRWRepository extends JpaRepository<WilayahRWModel, Long> {

}
