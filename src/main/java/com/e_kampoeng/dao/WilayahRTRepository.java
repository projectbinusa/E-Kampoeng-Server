package com.e_kampoeng.dao;

import com.e_kampoeng.model.WilayahRTModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WilayahRTRepository extends JpaRepository<WilayahRTModel, Long> {
    WilayahRTModel findById(long id);
    @Query(value = "SELECT * FROM wilayah_rt WHERE nomor_rt LIKE CONCAT('%',:keyword, '%')", nativeQuery = true)
    Page<WilayahRTModel> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
