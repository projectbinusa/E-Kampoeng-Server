package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.service.WilayahRWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WilayahRWImpl implements WilayahRWService {
    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    // GET ALL DATA WILAYAH RW WITH PAGINATION
    @Override
    public Page<WilayahRWModel> getAllWilayahRW(Pageable pageable) {
        return wilayahRWRepository.findAll(pageable);
    }

    // GET DATA WILAYAH RW BY ID
    @Override
    public WilayahRWModel getWilayahRWById(Long id) {
        WilayahRWModel wilRW = wilayahRWRepository.findById(id).orElse(null);
        if (wilRW == null) {
            throw new NotFoundException("Wilayah RW Id Not Found");
        }
        return wilRW;
    }

    // CREATE DATA WILAYAH RW
    @Override
    public WilayahRWModel createWilayahRW(WilayahRWRequestDTO requestDTO) {
        WilayahRWModel wilayahRW = new WilayahRWModel();
        wilayahRW.setNamaDusun(requestDTO.getNamaDusun());
        wilayahRW.setNomorRw(requestDTO.getNomorRw());
        return wilayahRWRepository.save(wilayahRW);
    }

    // UPDATE DATA WILAYAH RW
    @Override
    public WilayahRWModel updateWilayahRW(Long id, WilayahRWRequestDTO requestDTO) {
        WilayahRWModel wilRW = wilayahRWRepository.findById(id).orElse(null);
        if (wilRW == null) {
            throw new NotFoundException("Wilayah RW Id Not Found");
        }
        wilRW.setNamaDusun(requestDTO.getNamaDusun());
        wilRW.setNomorRw(requestDTO.getNomorRw());
        return wilayahRWRepository.save(wilRW);
    }

    // DELETE DATA WILAYAH RW BY ID
    @Override
    public Map<String, Boolean> deleteWilayahRW(Long id) {
        try {
            wilayahRWRepository.deleteById(id);
            Map<String, Boolean> obj = new HashMap<>();
            obj.put("Deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }

}
