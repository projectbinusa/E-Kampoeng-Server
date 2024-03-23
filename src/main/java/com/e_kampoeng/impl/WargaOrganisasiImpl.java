package com.e_kampoeng.impl;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.OrganisasiRepository;
import com.e_kampoeng.repository.WargaOrganisasiRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaOrganisasiModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.service.WargaOrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WargaOrganisasiImpl implements WargaOrganisasiService {
    @Autowired
    private WargaOrganisasiRepository wargaOrganisasiRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Autowired
    private OrganisasiRepository organisasiRepository;

    @Override
    public WargaOrganisasiModel addWargaToOrganisasi(Long wargaId, Long organisasiId) {
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak ditemukan."));

        OrganisasiModel organisasi = organisasiRepository.findById(organisasiId)
                .orElseThrow(() -> new IllegalArgumentException("Organisasi dengan ID " + organisasiId + " tidak ditemukan."));

        WargaOrganisasiModel wargaOrganisasi = new WargaOrganisasiModel();
        wargaOrganisasi.setWarga(warga);
        wargaOrganisasi.setOrganisasi(organisasi);

        return wargaOrganisasiRepository.save(wargaOrganisasi);
    }

    @Override
    public Map<String, Boolean> removeWargaOrganisasi(Long wargaOrganisasiId) {
        try {
            wargaOrganisasiRepository.deleteById(wargaOrganisasiId);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (IllegalArgumentException e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }

    @Override
    public Page<WargaOrganisasiModel> getAllWargaInOrganisasi(Pageable pageable, Long organisasiId) {
        OrganisasiModel organisasi = organisasiRepository.findById(organisasiId)
                .orElseThrow(() -> new IllegalArgumentException("Organisasi dengan ID " + organisasiId + " tidak ditemukan."));
        return wargaOrganisasiRepository.findAllByOrganisasi(pageable, organisasi);
    }

    @Override
    public Page<WargaOrganisasiModel> getAllOrganisasiByWarga(Pageable pageable, Long wargaId) {
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak ditemukan."));
        return wargaOrganisasiRepository.findAllByWarga(pageable, warga);
    }

}
