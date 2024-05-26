package com.e_kampoeng.repository;

import com.e_kampoeng.model.e_kas.PengeluaranModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PengeluaranRepository extends JpaRepository<PengeluaranModel, Long> {
    Page<PengeluaranModel> findAllByWilayahRTId(Long wilayahRTId, Pageable pageable);
    Optional<PengeluaranModel> findByIdAndWilayahRTId(Long id, Long wilayahRTId);

}
