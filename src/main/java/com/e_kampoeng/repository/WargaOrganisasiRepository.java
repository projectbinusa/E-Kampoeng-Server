package com.e_kampoeng.repository;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WargaOrganisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WargaOrganisasiRepository extends JpaRepository<WargaOrganisasiModel, Long> {
    List<WargaOrganisasiModel> findAllByOrganisasi(OrganisasiModel organisasi);
    List<WargaOrganisasiModel> findAllByWarga(WargaModel warga);
}
