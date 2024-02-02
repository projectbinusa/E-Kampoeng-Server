package com.e_kampoeng.repository;

import com.e_kampoeng.model.WilayahRWModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WilayahRWRepository extends JpaRepository<WilayahRWModel, Long> {
    @Query(value = "SELECT * FROM wilayah_rw WHERE nomor_rw LIKE CONCAT('%',:keyword, '%') OR nama_dusun LIKE CONCAT('%',:keyword, '%')", nativeQuery = true)
    Page<WilayahRWModel> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
