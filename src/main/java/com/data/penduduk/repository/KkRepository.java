package com.data.penduduk.repository;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Rt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KkRepository extends JpaRepository<Kk, Long> {
    List<Kk> findKkByRt(Rt rt);

    @Query("SELECT p FROM Kk p WHERE CONCAT(p.nama) LIKE %?1%")
    List<Kk> searchByNama(String nama);
}
