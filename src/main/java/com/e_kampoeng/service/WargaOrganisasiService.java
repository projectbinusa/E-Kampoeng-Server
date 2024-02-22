package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaOrganisasiModel;

import java.util.List;

public interface WargaOrganisasiService {
    WargaOrganisasiModel addWargaToOrganisasi(Long wargaId, Long organisasiId);
    void removeWargaFromOrganisasi(Long wargaOrganisasiId);
    List<WargaOrganisasiModel> getAllWargaInOrganisasi(Long organisasiId);
    List<WargaOrganisasiModel> getAllOrganisasiByWarga(Long wargaId);
}
