package com.e_kampoeng.repository;

import com.e_kampoeng.model.e_kas.PemasukanModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PemasukanRepository extends JpaRepository<PemasukanModel, Long> {
    Page<PemasukanModel> findAllByWilayahRTId(Long wilayahRTId, Pageable pageable);
    Optional<PemasukanModel> findByIdAndWilayahRTId(Long id, Long wilayahRTId);
}
