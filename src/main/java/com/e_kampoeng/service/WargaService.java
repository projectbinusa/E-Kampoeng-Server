package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.response.WargaByRTResponseDTO;
import com.e_kampoeng.response.WargaResponseDTO;
import java.util.List;

public interface WargaService {
    List<WargaResponseDTO> getAllWarga();
    WargaResponseDTO getWargaById(Long id);
    WargaResponseDTO createWarga(WargaRequestDTO requestDTO);
    WargaResponseDTO updateWarga(Long id, WargaRequestDTO requestDTO);
    void deleteWarga(Long id);
}
