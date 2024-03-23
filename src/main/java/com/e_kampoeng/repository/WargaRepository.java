package com.e_kampoeng.repository;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WargaRepository extends JpaRepository<WargaModel, Long> {
    Page<WargaModel> findByWilayahRT_WilayahRW_Id(Long rwId, Pageable pageable);
    Page<WargaModel> findByWilayahRT_Id(Long rtId, Pageable pageable);
}
