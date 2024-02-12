package com.e_kampoeng.service;

import com.e_kampoeng.model.OrganisasiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface OrganisasiService {
    Page<OrganisasiModel> getAll(Pageable pageable);

    OrganisasiModel getById(Long id);

    OrganisasiModel post(OrganisasiModel organisasiModel);

    OrganisasiModel put(Long id, OrganisasiModel organisasiModel);

    Map<String, Boolean> del(Long id);
}
