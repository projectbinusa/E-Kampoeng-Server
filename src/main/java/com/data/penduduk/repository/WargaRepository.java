package com.data.penduduk.repository;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Warga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WargaRepository extends JpaRepository<Warga, Long> {
    List<Warga> findWargaByKk(Kk kk);
}
