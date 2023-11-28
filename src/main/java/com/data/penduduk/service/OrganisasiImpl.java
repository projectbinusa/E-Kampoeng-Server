package com.data.penduduk.service;

import com.data.penduduk.exception.NotFoundException;
import com.data.penduduk.model.Organisasi;
import com.data.penduduk.repository.OrganisasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrganisasiImpl implements OrganisasiService{

    @Autowired
    private OrganisasiRepository organisasiRepository;

    @Override
    public Organisasi add(Organisasi organisasi){
        return organisasiRepository.save(organisasi);
    }

    @Override
    public Organisasi edit(Organisasi organisasi, Long id) {
        Organisasi update = organisasiRepository.findById(id).orElseThrow(() ->new NotFoundException("Not Found"));
        update.setJenis_surat(organisasi.getJenis_surat());
        update.setCreate_at(organisasi.getCreate_at());
        update.setWargas(organisasi.getWargas());
        return organisasiRepository.save(update);
    }

    @Override
    public Organisasi get(Long id) {
        return organisasiRepository.findById(id).orElseThrow(()->new NotFoundException("Id Not Found"));
    }

    @Override
    public List<Organisasi> getAll() {
        return organisasiRepository.findAll();
    }

    @Override
    public Map<String , Boolean> delete(Long id) {
        try {
            organisasiRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("delete", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("Id Not Found");
        }
    }
}
