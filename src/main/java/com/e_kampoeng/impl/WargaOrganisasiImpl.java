package com.e_kampoeng.impl;

import com.e_kampoeng.repository.WargaOrganisasiDao;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaOrganisasiModel;
import com.e_kampoeng.service.WargaOrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WargaOrganisasiImpl implements WargaOrganisasiService {

    @Autowired
    private WargaOrganisasiDao womdao;

    @Override
    public Page<WargaOrganisasiModel> getAll(Pageable pageable) {
        return womdao.findAll(pageable);
    }

    @Override
    public WargaOrganisasiModel getById(Long id) {
        return womdao.findById(id).orElse(null);
    }

    @Override
    public WargaOrganisasiModel create(WargaOrganisasiModel wargaOrganisasiModel) {
        WargaOrganisasiModel newWargaOrganisasi = new WargaOrganisasiModel();
        newWargaOrganisasi.setWarga(wargaOrganisasiModel.getWarga());
        newWargaOrganisasi.setOrganisasi(wargaOrganisasiModel.getOrganisasi());
        return womdao.save(newWargaOrganisasi);
    }

    @Override
    public WargaOrganisasiModel update(Long id, WargaOrganisasiModel wargaOrganisasiModel) {
        WargaOrganisasiModel update = womdao.findById(id).orElse(null);
        update.setWarga(wargaOrganisasiModel.getWarga());
        update.setOrganisasi(wargaOrganisasiModel.getOrganisasi());
        return womdao.save(update);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            womdao.findById(id).orElse(null);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted ", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("Warga Organisasi not found");
        }
    }
}
