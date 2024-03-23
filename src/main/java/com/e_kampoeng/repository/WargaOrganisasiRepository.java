package com.e_kampoeng.repository;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WargaOrganisasiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WargaOrganisasiRepository extends JpaRepository<WargaOrganisasiModel, Long> {
    Page<WargaOrganisasiModel> findAllByOrganisasi(Pageable pageable, OrganisasiModel organisasi);
    Page<WargaOrganisasiModel> findAllByWarga(Pageable pageable, WargaModel warga);
}
