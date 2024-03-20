package com.e_kampoeng.repository;

import com.e_kampoeng.model.RTWilayah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RTWilayahRepository extends JpaRepository<RTWilayah, Long> {

    boolean existsByWilayahRTId(Long wilayahRTId);

}

