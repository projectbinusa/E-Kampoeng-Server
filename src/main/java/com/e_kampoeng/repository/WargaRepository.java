package com.e_kampoeng.repository;

import com.e_kampoeng.model.WargaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WargaRepository extends JpaRepository<WargaModel, Long> {
    List<WargaModel> findByWilayahRTId(Long idWilayahRT);

}
