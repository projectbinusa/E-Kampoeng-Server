package com.e_kampoeng.service;

import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.WargaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WargaService {
    Page<WargaResponseDTO> getAllWarga(Pageable pageable);
    WargaResponseDTO getWargaById(Long id);
    WargaResponseDTO createWarga(WargaRequestDTO requestDTO);
    WargaResponseDTO updateWarga(Long id, WargaRequestDTO requestDTO);
    void deleteWarga(Long id);
}
