package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WilayahRTService {
    WilayahRTModel createWilayahRT(WilayahRTRequestDTO requestDTO);
    Page<WilayahRTResponseDTO> getAllWilayahRT(Pageable pageable);
    WilayahRTResponseDTO getWilayahRTById(Long id);
    WilayahRTRequestDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO);
    byte[] exportToExcel() throws IOException;
    Map<String, Boolean> deleteWilayahRT(Long id);
    List<WilayahRTRequestDTO> importFromExcel(MultipartFile file) throws IOException;
    WilayahRTModel addKepalaRT(Long wilayahRTId, Long wargaId);
}

