package com.data.penduduk.repository;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Rt;
import com.data.penduduk.model.Warga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WargaRepository extends JpaRepository<Warga, Long> {
    List<Warga> findWargaByKk(Kk kk);

    @Query("SELECT p FROM Warga p WHERE CONCAT(p.nama) LIKE %?1%")
    List<Warga> searchByNama(String nama);
}
