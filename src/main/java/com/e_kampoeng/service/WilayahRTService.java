package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WilayahRTService {
    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    public List<WilayahRTModel> getAllWilayahRT() {
        return wilayahRTRepository.findAll();
    }

    public WilayahRTModel createWilayahRT(WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRT = new WilayahRTModel();
        wilayahRT.setNomorRt(requestDTO.getNomerRt());

        // Mengambil data Wilayah_RW dari repositori
        WilayahRWModel wilayahRW = wilayahRWRepository.findById(requestDTO.getWilayahRWId()).orElse(null);
        wilayahRT.setWilayahRW(wilayahRW);

        return wilayahRTRepository.save(wilayahRT);
    }

    public List<WilayahRTModel> getWilayahRTByWilayahRWId(Long wilayahRWId) {
        return wilayahRTRepository.findByWilayahRW_Id(wilayahRWId);
    }
}

