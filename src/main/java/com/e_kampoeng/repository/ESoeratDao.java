package com.e_kampoeng.repository;

import com.e_kampoeng.model.ESoeratModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ESoeratDao extends JpaRepository<ESoeratModel, Long> {

    Page<ESoeratModel> findAllByWilayahRTId(Long wilayahRTId, Pageable pageable);

    Optional<ESoeratModel> findByIdAndWilayahRTId(Long id, Long wilayahRTId);

    void deleteByIdAndWilayahRTId(Long id, Long wilayahRTId);
}
