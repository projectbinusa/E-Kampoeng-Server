package com.e_kampoeng.impl;

import com.e_kampoeng.repository.ESoeratDao;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.service.ESoeratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ESoeratImpl implements ESoeratService {

    @Autowired
    ESoeratDao eSoeratRepository;

    @Override
    public Page<ESoeratModel> getAllSoerat(Pageable pageable) {
        return eSoeratRepository.findAll(pageable);
    }

    @Override
    public ESoeratModel getIdSoerat(Long id) {
        var soerat = eSoeratRepository.findById(id).get();
        return eSoeratRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
    }

    @Override
    public ESoeratModel addSoerat(ESoeratModel eSoeratModel) {
        ESoeratModel soerat = new ESoeratModel();
        soerat.setJenis_surat(eSoeratModel.getJenis_surat());
        soerat.setJenis_bantuan(eSoeratModel.getJenis_bantuan());
        return eSoeratRepository.save(soerat);
    }

    @Override
    public ESoeratModel editSoerat(Long id, ESoeratModel eSoeratModel) {
        ESoeratModel eSoeratModel1 = eSoeratRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        eSoeratModel1.setJenis_surat(eSoeratModel.getJenis_surat());
        eSoeratModel1.setJenis_bantuan(eSoeratModel.getJenis_bantuan());
        return eSoeratRepository.save(eSoeratModel1);
    }

    @Override
    public Map<String, Boolean> deleteSoerat(Long id) {
        try {
            eSoeratRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Hapus", Boolean.TRUE);
            return res;
        } catch (Exception e) {

            Map<String, Boolean> tes = new HashMap<>();
            tes.put("Hapus", Boolean.FALSE);
            return tes;
        }
    }

}
