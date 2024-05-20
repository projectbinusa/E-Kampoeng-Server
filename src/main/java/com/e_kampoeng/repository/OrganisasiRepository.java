package com.e_kampoeng.repository;

import com.e_kampoeng.model.OrganisasiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganisasiRepository extends JpaRepository<OrganisasiModel, Long> {
    Page<OrganisasiModel> findAllByWilayahRTId(Long wilayahRTId, Pageable pageable);
    Optional<OrganisasiModel> findByIdAndWilayahRTId(Long id, Long wilayahRTId);
    Optional<OrganisasiModel> findByIdAndCreatorEmail(Long id, String creatorEmail);
    void deleteByIdAndCreatorEmail(Long id, String creatorEmail);
}
