package com.data.penduduk.repository;

import com.data.penduduk.model.Organisasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisasiRepository extends JpaRepository<Organisasi, Long> {
    @Override
    List<Organisasi> findAll();
}
