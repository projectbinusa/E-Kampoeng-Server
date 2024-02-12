package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.repository.OrganisasiRepository;
import com.e_kampoeng.service.OrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrganisasiImpl implements OrganisasiService {

    @Autowired
    private OrganisasiRepository organisasiRepo;

    @Override
    public Page<OrganisasiModel> getAll(Pageable pageable) {
        return organisasiRepo.findAll(pageable);
    }

    @Override
    public OrganisasiModel getById(Long id) {
        OrganisasiModel organisasi = organisasiRepo.findById(id).orElse(null);
        if (organisasi == null) {
            throw new NotFoundException("Organisasi Id not found");
        }
        return organisasi;
    }

    @Override
    public OrganisasiModel post(OrganisasiModel organisasi) {
        OrganisasiModel newOrganisasi = new OrganisasiModel();
        newOrganisasi.setNama_organisasi(organisasi.getNama_organisasi());
        newOrganisasi.setWargas(organisasi.getWargas());

        return organisasiRepo.save(newOrganisasi);
    }

    @Override
    public OrganisasiModel put(Long id, OrganisasiModel organisasiModel) {
        OrganisasiModel organisasi = organisasiRepo.findById(id).orElse(null);
        if (organisasi == null) {
            throw new NotFoundException("Organisasi Id not found");
        }
        organisasi.setNama_organisasi(organisasiModel.getNama_organisasi());
        organisasi.setWargas(organisasiModel.getWargas());

        return organisasiRepo.save(organisasi);
    }

    @Override
    public Map<String, Boolean> del(Long id) {
        try {
            organisasiRepo.deleteById(id);
            Map<String, Boolean> obj = new HashMap<>();
            obj.put("Deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            throw new NotFoundException("Organisasi Id not found");
        }
    }
}
