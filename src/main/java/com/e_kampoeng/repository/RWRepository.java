package com.e_kampoeng.repository;

import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.model.WargaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RWRepository extends JpaRepository<RWModel, Long> {

    boolean existsByWilayahRWId(Long wilayahRWId);

    @Query(value = "SELECT rw.warga FROM RWWilayah rw WHERE rw.warga.id = :wargaId", nativeQuery = true)
    WargaModel findWargaById(@Param("wargaId") Long wargaId);
}


