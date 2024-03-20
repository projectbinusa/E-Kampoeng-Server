package com.e_kampoeng.repository;

import com.e_kampoeng.model.RWWilayah;
import com.e_kampoeng.model.WargaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RWWilayahRepository extends JpaRepository<RWWilayah, Long> {

    boolean existsByWilayahRWId(Long wilayahRWId);

    @Query("SELECT rw.warga FROM RWWilayah rw WHERE rw.warga.id = :wargaId")
    WargaModel findWargaById(@Param("wargaId") Long wargaId);
}


