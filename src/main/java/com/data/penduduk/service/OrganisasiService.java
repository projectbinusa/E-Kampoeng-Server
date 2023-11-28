package com.data.penduduk.service;

import com.data.penduduk.model.Organisasi;

import java.util.List;
import java.util.Map;

public interface OrganisasiService {

    Organisasi add (Organisasi organisasi);

    Organisasi edit (Organisasi organisasi, Long id);

    Organisasi get(Long id);

    List<Organisasi> getAll();

    Map<String, Boolean> delete(Long id);
}
