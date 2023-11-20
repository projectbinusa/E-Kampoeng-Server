package com.data.penduduk.service;

import com.data.penduduk.exception.NotFoundException;
import com.data.penduduk.model.ESoeratModel;
import com.data.penduduk.model.KkModel;
import com.data.penduduk.model.RtModel;
import com.data.penduduk.model.WargaPendatang;
import com.data.penduduk.repository.WargaPendatangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WargaPendatangImpl implements WargaPendatangService{

    @Autowired
    private WargaPendatangRepository wargaPendatangRepository;

    @Override
    public WargaPendatang add(WargaPendatang wargaPendatang) {
        return wargaPendatangRepository.save(wargaPendatang);
    }

    @Override
    public WargaPendatang edit(WargaPendatang wargaPendatang, Long id) {
        WargaPendatang update = wargaPendatangRepository.findById(id).orElseThrow(() ->new NotFoundException("Id Not Found"));
        update.setWarga_id(wargaPendatang.getWarga_id());
        update.setStatus_penduduk(wargaPendatang.getStatus_penduduk());
        return wargaPendatangRepository.save(update);
    }

    @Override
    public WargaPendatang get(Long id) {
        return wargaPendatangRepository.findById(id).orElseThrow(() -> new RuntimeException("Id Not Found"));
    }

    @Override
    public List<WargaPendatang> getAll() {
        return wargaPendatangRepository.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            wargaPendatangRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("Id Not Found");
        }
    }}
