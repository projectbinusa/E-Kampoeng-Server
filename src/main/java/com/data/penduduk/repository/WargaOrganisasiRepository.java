package com.data.penduduk.repository;

import com.data.penduduk.model.WargaOrganisasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WargaOrganisasiRepository extends JpaRepository<WargaOrganisasi, Long> {

    @Override
    List<WargaOrganisasi> findAll();
}
