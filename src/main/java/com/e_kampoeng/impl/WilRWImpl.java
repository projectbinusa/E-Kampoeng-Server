package com.e_kampoeng.impl;

import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;
import com.e_kampoeng.response.WilayahRTWithRwDTO;
import com.e_kampoeng.response.WilayahRWResponseDTO;
import com.e_kampoeng.service.WilayahRWService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WilRWImpl implements WilayahRWService {

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    @Override
    public WilayahRWResponseDTO createWilayahRW(WilayahRWRequestDTO requestDTO) {
        WilayahRWModel wilayahRWModel = new WilayahRWModel();
        BeanUtils.copyProperties(requestDTO, wilayahRWModel);
        WilayahRWModel savedModel = wilayahRWRepository.save(wilayahRWModel);
        WilayahRWResponseDTO responseDTO = new WilayahRWResponseDTO();
        BeanUtils.copyProperties(savedModel, responseDTO);
        return responseDTO;
    }

    @Override
    public WilayahRWResponseDTO updateWilayahRW(Long id, WilayahRWRequestDTO requestDTO) {
        WilayahRWModel wilayahRWModel = wilayahRWRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + id));
        BeanUtils.copyProperties(requestDTO, wilayahRWModel);
        WilayahRWModel updatedModel = wilayahRWRepository.save(wilayahRWModel);
        WilayahRWResponseDTO responseDTO = new WilayahRWResponseDTO();
        BeanUtils.copyProperties(updatedModel, responseDTO);
        return responseDTO;
    }

    @Override
    public void deleteWilayahRW(Long id) {
        wilayahRWRepository.deleteById(id);
    }

    @Override
    public WilayahRWResponseDTO getWilayahRWById(Long id) {
        WilayahRWModel wilayahRWModel = wilayahRWRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + id));
        WilayahRWResponseDTO responseDTO = new WilayahRWResponseDTO();
        BeanUtils.copyProperties(wilayahRWModel, responseDTO);
        return responseDTO;
    }
    @Override
    public List<WilayahRTWithRwDTO> getRTsByRW(Long rwId) {
        WilayahRWModel wilRWModel = wilayahRWRepository.findById(rwId)
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + rwId));

        List<WilayahRTModel> rtModels = wilRWModel.getRtModels();
        List<WilayahRTWithRwDTO> rtResponseDTOs = new ArrayList<>();
        for (WilayahRTModel rtModel : rtModels) {
            WilayahRTWithRwDTO responseDTO = new WilayahRTWithRwDTO();
            BeanUtils.copyProperties(rtModel, responseDTO);
            rtResponseDTOs.add(responseDTO);
        }
        return rtResponseDTOs;
    }

    @Override
    public Page<WilayahRWModel> getAllWilayahRW(Pageable pageable) {
        return wilayahRWRepository.findAll(pageable);
    }
}
