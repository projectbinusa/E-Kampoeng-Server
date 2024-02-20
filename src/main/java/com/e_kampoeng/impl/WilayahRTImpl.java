package com.e_kampoeng.impl;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.WargaByRTResponseDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.service.WilayahRTService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WilayahRTImpl implements WilayahRTService {

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public List<WilayahRTModel> getAllWilayahRT() {
        return wilayahRTRepository.findAll();
    }

    @Override
    public WilayahRTModel getWilayahRTById(Long id) {
        return wilayahRTRepository.findById(id).orElse(null);
    }


    private WilayahRTResponseDTO mapToDto(WilayahRTModel rt) {
        WilayahRTResponseDTO dto = new WilayahRTResponseDTO();
        dto.setId(rt.getId());
        dto.setNomor_rt(rt.getNomor_rt());
        // Map other attributes as needed
        return dto;
    }


    @Override
    public WilayahRTResponseDTO createWilayahRT(WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRTModel = new WilayahRTModel();
        BeanUtils.copyProperties(requestDTO, wilayahRTModel);
        WilayahRWModel wilRW = wilayahRWRepository.findById(requestDTO.getWilayah_rw_id())
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + requestDTO.getWilayah_rw_id()));
        wilayahRTModel.setWilRW(wilRW); // Menetapkan Wilayah RW ke Wilayah RT
        WilayahRTModel savedModel = wilayahRTRepository.save(wilayahRTModel);
        WilayahRTResponseDTO responseDTO = new WilayahRTResponseDTO();
        BeanUtils.copyProperties(savedModel, responseDTO);
        responseDTO.setWilayah_rw_id(wilRW.getId()); // Set ID Wilayah RW di respons
        return responseDTO;
    }

    @Override
    public WilayahRTResponseDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + id));
        BeanUtils.copyProperties(requestDTO, wilayahRTModel);
        WilayahRWModel wilRW = wilayahRWRepository.findById(requestDTO.getWilayah_rw_id())
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + requestDTO.getWilayah_rw_id()));
        wilayahRTModel.setWilRW(wilRW); // Menetapkan Wilayah RW ke Wilayah RT
        WilayahRTModel updatedModel = wilayahRTRepository.save(wilayahRTModel);
        WilayahRTResponseDTO responseDTO = new WilayahRTResponseDTO();
        BeanUtils.copyProperties(updatedModel, responseDTO);
        responseDTO.setWilayah_rw_id(wilRW.getId()); // Set ID Wilayah RW di respons
        return responseDTO;
    }

    @Override
    public void deleteWilayahRT(Long id) {
        wilayahRTRepository.deleteById(id);
    }

    @Override
    public List<WargaByRTResponseDTO> getWargaByRT(Long idWilayahRT) {
        List<WargaModel> wargaModels = wargaRepository.findByWilayahRTId(idWilayahRT);
        return wargaModels.stream()
                .map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    private WargaByRTResponseDTO convertModelToDTO(WargaModel wargaModel) {
        WargaByRTResponseDTO responseDTO = new WargaByRTResponseDTO();
        responseDTO.setId(wargaModel.getId());
        responseDTO.setNama(wargaModel.getNama());
        return responseDTO;
    }

}
