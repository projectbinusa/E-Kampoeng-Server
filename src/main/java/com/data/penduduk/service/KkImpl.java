package com.data.penduduk.service;

import com.data.penduduk.exception.NotFoundException;
import com.data.penduduk.model.ESoeratModel;
import com.data.penduduk.model.KkModel;
import com.data.penduduk.repository.KkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class    KkImpl implements KkService{

    @Autowired
    private KkRepository kkRepository;

    @Override
    public KkModel add(KkModel kkModel) {
        return kkRepository.save(kkModel);
    }

    @Override
    public KkModel edit(KkModel kkModel, Long id) {
        KkModel update = kkRepository.findById(id).orElseThrow(() ->new NotFoundException("Id Not Found"));
        update.setWarga_id(kkModel.getWarga_id());
        return kkRepository.save(update);
    }

    @Override
    public KkModel get(Long id) {
        return kkRepository.findById(id).orElseThrow(() -> new RuntimeException("Id Not Found"));
    }

    @Override
    public List<KkModel> getAll() {
        return kkRepository.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            kkRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("Id Not Found");
        }
    }
}
