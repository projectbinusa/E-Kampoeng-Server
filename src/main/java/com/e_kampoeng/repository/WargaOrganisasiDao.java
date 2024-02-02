package com.e_kampoeng.repository;

import com.e_kampoeng.model.WargaOrganisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WargaOrganisasiDao extends JpaRepository<WargaOrganisasiModel, Long> {
}
