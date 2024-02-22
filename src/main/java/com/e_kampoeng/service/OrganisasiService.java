package com.e_kampoeng.service;

import com.e_kampoeng.model.OrganisasiModel;

import java.util.List;
import java.util.Optional;

public interface OrganisasiService {
    List<OrganisasiModel> getAllOrganisasi();
    Optional<OrganisasiModel> getOrganisasiById(Long id);
    OrganisasiModel createOrganisasi(OrganisasiModel organisasiModel);
    void deleteOrganisasi(Long id);
    OrganisasiModel updateOrganisasi(Long id, OrganisasiModel organisasi);
}
