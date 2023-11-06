package com.data.penduduk.repository;

import com.data.penduduk.model.Warga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WargaRepository extends JpaRepository<Warga, Long> {
    @Override
    List<Warga> findAll();
}
