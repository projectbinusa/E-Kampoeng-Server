package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaOrganisasiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface WargaOrganisasiService {
    WargaOrganisasiModel addWargaToOrganisasi(Long wargaId, Long organisasiId);
//    Map<String, Boolean> removeWargaFromOrganisasi(Long wargaOrganisasiId);
    Map<String, Boolean> removeWargaOrganisasi(Long wargaOrganisasiId);
//    Map<String, Boolean> removeWargaFromOrganisasi(Long wargaId);
    Page<WargaOrganisasiModel> getAllWargaInOrganisasi(Pageable pageable, Long organisasiId);
    Page<WargaOrganisasiModel> getAllOrganisasiByWarga(Pageable pageable, Long wargaId);
}
