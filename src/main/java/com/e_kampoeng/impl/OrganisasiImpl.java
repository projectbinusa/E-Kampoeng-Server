package com.e_kampoeng.impl;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.repository.OrganisasiRepository;
import com.e_kampoeng.service.OrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrganisasiImpl implements OrganisasiService {

    @Autowired
    private OrganisasiRepository organisasiRepository;

    @Override
    public Page<OrganisasiModel> getAllOrganisasi(Pageable pageable) {
        return organisasiRepository.findAll(pageable);
    }

    @Override
    public Optional<OrganisasiModel> getOrganisasiById(Long id) {
        return organisasiRepository.findById(id);
    }

    @Override
    public OrganisasiModel createOrganisasi(OrganisasiModel organisasiModel) {
        return organisasiRepository.save(organisasiModel);
    }

    @Override
    public OrganisasiModel updateOrganisasi(Long id, OrganisasiModel organisasi) {
        organisasi.setId(id);
        return organisasiRepository.save(organisasi);
    }

    @Override
    public Map<String, Boolean> deleteOrganisasi(Long id) {
        try {
            organisasiRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (IllegalArgumentException e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }
}
