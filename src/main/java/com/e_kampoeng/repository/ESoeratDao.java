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

    Optional<ESoeratModel> findByIdAndCreatorEmail(Long id, String creatorEmail);

    Page<ESoeratModel> findAllByCreatorEmail(String creatorEmail, Pageable pageable);

    Page<ESoeratModel> findAllByWilayahRTIdAndStatus(Long wilayahRTId, String status, Pageable pageable);
}
