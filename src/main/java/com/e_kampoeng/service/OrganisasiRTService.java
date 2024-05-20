package com.e_kampoeng.service;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.request.OrganisasiRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface OrganisasiRTService {
    Page<OrganisasiModel> getAllOrganisasiRTByCreator(String creatorEmail, Pageable pageable);
    Optional<OrganisasiModel> getOrganisasiRTByIdAndCreator(Long id, String creatorEmail);
    OrganisasiModel createOrganisasiRT(OrganisasiModel organisasiModel, String creatorEmail);
    OrganisasiModel updateOrganisasiRT(Long id, OrganisasiRequestDTO organisasi);
    void deleteOrganisasiRT(Long id);

}
