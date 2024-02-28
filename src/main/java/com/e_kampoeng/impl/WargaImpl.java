package com.e_kampoeng.impl;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.WargaResponseDTO;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.service.WargaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WargaImpl implements WargaService {

    @Autowired
    private WargaRepository wargaRepository;

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Override
    public Page<WargaModel> getAllWarga(Pageable pageable) {
        return wargaRepository.findAll(pageable);
    }

    @Override
    public WargaResponseDTO getWargaById(Long id) {
        WargaModel wargaModel = wargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warga not found with id: " + id));
        return convertModelToDTO(wargaModel);
    }

    @Override
    public WargaResponseDTO createWarga(WargaRequestDTO requestDTO) {
        WargaModel wargaModel = new WargaModel();
        BeanUtils.copyProperties(requestDTO, wargaModel);
        if (requestDTO.getWilayah_rt_id() != null) {
            WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(requestDTO.getWilayah_rt_id())
                    .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + requestDTO.getWilayah_rt_id()));
            wargaModel.setWilayah_rt(wilayahRTModel);
        }
        WargaModel savedModel = wargaRepository.save(wargaModel);
        return convertModelToDTO(savedModel);
    }

    @Override
    public WargaResponseDTO updateWarga(Long id, WargaRequestDTO requestDTO) {
        WargaModel wargaModel = wargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warga not found with id: " + id));
        BeanUtils.copyProperties(requestDTO, wargaModel);
        if (requestDTO.getWilayah_rt_id() != null) {
            WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(requestDTO.getWilayah_rt_id())
                    .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + requestDTO.getWilayah_rt_id()));
            wargaModel.setWilayah_rt(wilayahRTModel);
        }
        WargaModel updatedModel = wargaRepository.save(wargaModel);
        return convertModelToDTO(updatedModel);
    }

    @Override
    public void deleteWarga(Long id) {
        wargaRepository.deleteById(id);
    }

    private WargaResponseDTO convertModelToDTO(WargaModel wargaModel) {
        WargaResponseDTO responseDTO = new WargaResponseDTO();
        BeanUtils.copyProperties(wargaModel, responseDTO);
        return responseDTO;
    }

    @Override
    public WilayahRTModel findWilayahRTByWargaId(Long wargaId) {
        WargaModel warga = wargaRepository.findById(wargaId).orElse(null);
        if (warga != null) {
            return warga.getWilayah_rt();
        }
        return null;
    }
}
