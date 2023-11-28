package com.data.penduduk.service;

import com.data.penduduk.model.WargaOrganisasi;

import java.util.List;
import java.util.Map;

public interface WargaOrganisasiService {

    WargaOrganisasi add (WargaOrganisasi wargaOrganisasi);

    WargaOrganisasi edit (WargaOrganisasi wargaOrganisasi, Long id);

    WargaOrganisasi get(Long id);

    List<WargaOrganisasi> getAll();

    Map<String, Boolean> delete(Long id);
}
