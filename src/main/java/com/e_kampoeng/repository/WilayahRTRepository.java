package com.e_kampoeng.repository;

import com.e_kampoeng.model.WilayahRTModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WilayahRTRepository extends JpaRepository<WilayahRTModel, Long> {
    Page<WilayahRTModel> findByWilayahRW_Id(Long rtId, Pageable pageable);

//    @Query(value = "SELECT * FROM wilayah_rt where wilayah_rw_id = ?1 and nomor_rt = ?2", nativeQuery = true)
//    WilayahRTModel wilRTExistInWilRW(Long wilayahRWId, Long nomorRT);
}
