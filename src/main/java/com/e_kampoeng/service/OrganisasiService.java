package com.e_kampoeng.service;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.request.OrganisasiRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrganisasiService {
    Page<OrganisasiModel> getAllOrganisasi(Pageable pageable);
    Optional<OrganisasiModel> getOrganisasiById(Long id);
    OrganisasiModel createOrganisasi(OrganisasiModel organisasiModel);
    Map<String, Boolean> deleteOrganisasi(Long id);
    OrganisasiModel updateOrganisasiRT(Long id, OrganisasiRequestDTO organisasiRequestDTO);
}
