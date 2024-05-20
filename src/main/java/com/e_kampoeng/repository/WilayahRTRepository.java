package com.e_kampoeng.repository;

import com.e_kampoeng.model.WilayahRTModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WilayahRTRepository extends JpaRepository<WilayahRTModel, Long> {
    Optional<WilayahRTModel> findByKepalaRt_Email(String email);
}
