package com.e_kampoeng.repository;

import com.e_kampoeng.model.ESoeratModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ESoeratRepository extends JpaRepository<ESoeratModel, Long> {

    @Query(value = "SELECT * FROM e_soerat WHERE jenis_surat LIKE CONCAT('%',:keyword, '%')", nativeQuery = true)
    Page<ESoeratModel> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

//    Page<ESoeratModel> findById(String query, Pageable pageable);
}
