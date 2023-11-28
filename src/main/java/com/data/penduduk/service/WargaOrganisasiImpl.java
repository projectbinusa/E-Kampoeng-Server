package com.data.penduduk.service;

import com.data.penduduk.exception.NotFoundException;
import com.data.penduduk.model.WargaOrganisasi;
import com.data.penduduk.repository.WargaOrganisasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WargaOrganisasiImpl implements WargaOrganisasiService{

    @Autowired
    private WargaOrganisasiRepository wargaOrganisasiRepository;

    @Override
    public WargaOrganisasi add(WargaOrganisasi wargaOrganisasi) {
        return wargaOrganisasiRepository.save(wargaOrganisasi);
    }

    @Override
    public WargaOrganisasi edit(WargaOrganisasi wargaOrganisasi, Long id) {
        WargaOrganisasi update = wargaOrganisasiRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        update.setWarga_id(wargaOrganisasi.getWarga_id());
        update.setOrganisasi_id(wargaOrganisasi.getOrganisasi_id());
        return wargaOrganisasiRepository.save(update);
    }

    @Override
    public WargaOrganisasi get(Long id) {
        return wargaOrganisasiRepository.findById(id).orElseThrow(() -> new NotFoundException("Id Not Found"));
    }

    @Override
    public List<WargaOrganisasi> getAll() {
        return wargaOrganisasiRepository.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            wargaOrganisasiRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("delete", Boolean.TRUE);
            return res;
        } catch (Exception e ) {
            throw new NotFoundException("Id Not Found");
        }
    }
}
