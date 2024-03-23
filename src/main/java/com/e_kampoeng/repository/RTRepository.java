package com.e_kampoeng.repository;

import com.e_kampoeng.model.RTModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RTRepository extends JpaRepository<RTModel, Long> {

    boolean existsByWilayahRTId(Long wilayahRTId);

}

