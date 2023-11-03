package com.data.penduduk.service;

import com.data.penduduk.exception.NotFoundException;
import com.data.penduduk.model.ESoeratModel;
import com.data.penduduk.repository.ESeoratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ESoeratImpl implements ESoeratService{

    @Autowired
    private ESeoratRepository eSeoratRepository;

    @Override
    public ESoeratModel add(ESoeratModel eSoeratModel) {
        return eSeoratRepository.save(eSoeratModel);
    }

    @Override
    public ESoeratModel edit(ESoeratModel eSoeratModel, Long id) {
        ESoeratModel update = eSeoratRepository.findById(id).orElseThrow(() ->new NotFoundException("Id Not Found"));
        update.setJenis_bantuan(eSoeratModel.getJenis_bantuan());
        update.setJenis_surat(eSoeratModel.getJenis_surat());
        return eSeoratRepository.save(update);
    }

    @Override
    public ESoeratModel get(Long id) {
        return eSeoratRepository.findById(id).orElseThrow(() -> new RuntimeException("Id NOt Found"));
    }

    @Override
    public List<ESoeratModel> getAll() {
        return eSeoratRepository.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            eSeoratRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("Id Not Found");
        }
    }
}
