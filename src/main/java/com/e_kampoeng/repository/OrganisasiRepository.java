package com.e_kampoeng.repository;

import com.e_kampoeng.model.OrganisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisasiRepository extends JpaRepository<OrganisasiModel, Long> {
}
