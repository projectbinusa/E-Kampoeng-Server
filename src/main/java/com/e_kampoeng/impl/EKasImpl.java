package com.e_kampoeng.impl;

import com.e_kampoeng.dto.EKasDTO;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.EKasModel;
import com.e_kampoeng.repository.EKasRepository;
import com.e_kampoeng.service.EKasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EKasImpl implements EKasService {

    @Autowired
    private EKasRepository eKasRepository;

    @Override
    public Page<EKasModel> findAllWithPagination(Pageable pageable) {
        return eKasRepository.findAll(pageable);
    }

    @Override
    public EKasModel findById(Long id) {
        EKasModel eKasModel = eKasRepository.findById(id).orElse(null);
        if (eKasModel == null) {
            throw new NotFoundException("ID E-Kas Not Found");
        }
        return eKasModel;
    }

    @Override
    public EKasModel create(EKasDTO eKasDTO) {
        EKasModel newKas = new EKasModel();
        newKas.setPemasukan(eKasDTO.getPemasukan());
        newKas.setPengeluaran(eKasDTO.getPengeluaran());
        newKas.setSisa_kas_bulan_lalu(eKasDTO.getSisa_kas_bulan_lalu());
        newKas.setSisa_kas_bulan_ini(eKasDTO.getSisa_kas_bulan_ini());
        newKas.setKet_pemasukan(eKasDTO.getKet_pemasukan());
        newKas.setKet_pengeluaran(eKasDTO.getKet_pengeluaran());
        return eKasRepository.save(newKas);
    }

    @Override
    public EKasModel update(Long id, EKasModel eKasModel) {
        EKasModel kas = eKasRepository.findById(id).orElse(null);
        if (kas == null) {
            throw new NotFoundException("ID E-Kas Not Found");
        }
        kas.setPemasukan(eKasModel.getPemasukan());
        kas.setPengeluaran(eKasModel.getPengeluaran());
        kas.setSisa_kas_bulan_lalu(eKasModel.getSisa_kas_bulan_lalu());
        kas.setSisa_kas_bulan_ini(eKasModel.getSisa_kas_bulan_ini());
        kas.setKet_pemasukan(eKasModel.getKet_pemasukan());
        kas.setKet_pengeluaran(eKasModel.getKet_pengeluaran());
        return eKasRepository.save(kas);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            eKasRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }
}
