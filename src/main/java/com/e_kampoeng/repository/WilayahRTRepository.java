package com.e_kampoeng.repository;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WilayahRTRepository extends JpaRepository<WilayahRTModel, Long> {
//    List<WargaModel> findByWilayahRt_Id(Long rtId);
}
