package com.data.penduduk.repository;

import com.data.penduduk.model.LayananWarga;
import com.data.penduduk.model.Rt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LayananWargaRepository extends JpaRepository<LayananWarga, Long> {
    List<LayananWarga> findLayananByRt(Rt rt);
}
