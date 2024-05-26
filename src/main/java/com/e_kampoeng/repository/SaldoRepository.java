package com.e_kampoeng.repository;

import com.e_kampoeng.model.e_kas.SaldoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoModel, Long> {
    Optional<SaldoModel> findByWilayahRTId(Long wilayahRTId);
}

