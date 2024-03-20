package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WilayahRWService {
    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    public List<WilayahRWModel> getAllWilayahRW() {
        return wilayahRWRepository.findAll();
    }

    public WilayahRWModel createWilayahRW(WilayahRWRequestDTO requestDTO) {
        WilayahRWModel wilayahRW = new WilayahRWModel();
        wilayahRW.setNamaDusun(requestDTO.getNamaDusun());
        wilayahRW.setNomorRw(requestDTO.getNomerRw());
        return wilayahRWRepository.save(wilayahRW);
    }

}
