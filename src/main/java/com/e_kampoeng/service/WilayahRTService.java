package com.e_kampoeng.service;

import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.WargaByRTResponseDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;

import java.util.List;

public interface WilayahRTService {
    WilayahRTResponseDTO createWilayahRT(WilayahRTRequestDTO requestDTO);
    WilayahRTResponseDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO);
    void deleteWilayahRT(Long id);
    List<WargaByRTResponseDTO> getWargaByRT(Long idWilayahRT);
}
