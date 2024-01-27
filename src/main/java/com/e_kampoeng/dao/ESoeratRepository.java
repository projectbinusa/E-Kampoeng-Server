package com.e_kampoeng.dao;

import com.e_kampoeng.model.ESoeratModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ESoeratRepository extends JpaRepository<ESoeratModel, Long> {

//    @Query(value = "SELECT * FROM e_soerat WHERE jenis_surat LIKE CONCAT('%',:keyword, '%')", nativeQuery = true)
//    Page<ESoeratModel> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

//    List<ESoeratModel> findById(String query, Pageable pageable);

//    List<ESoeratModel> findById();
}
