package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaOrganisasiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface WargaOrganisasiService {
    Page<WargaOrganisasiModel> getAll(Pageable pageable);

    WargaOrganisasiModel getById(Long id);

    WargaOrganisasiModel create(WargaOrganisasiModel wom);

    WargaOrganisasiModel update(Long id, WargaOrganisasiModel wom);

    Map<String, Boolean> delete(Long id);
}
