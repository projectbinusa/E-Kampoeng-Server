package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.WargaByRTResponseDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;

import java.util.List;

public interface WilayahRTService {
    List<WilayahRTResponseDTO> getAllWilayahRT();

    WilayahRTResponseDTO getWilayahRTById(Long id);
    WilayahRTResponseDTO createWilayahRT(WilayahRTRequestDTO requestDTO);
    WilayahRTResponseDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO);
    void deleteWilayahRT(Long id);
    List<WargaByRTResponseDTO> getWargaByRT(Long idWilayahRT);


}
