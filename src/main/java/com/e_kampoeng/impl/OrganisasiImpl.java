package com.e_kampoeng.impl;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.repository.OrganisasiRepository;
import com.e_kampoeng.service.OrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisasiImpl implements OrganisasiService {

    @Autowired
    private OrganisasiRepository organisasiRepository;

    @Override
    public List<OrganisasiModel> getAllOrganisasi() {
        return organisasiRepository.findAll();
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
    public void deleteOrganisasi(Long id) {
        organisasiRepository.deleteById(id);
    }
}
