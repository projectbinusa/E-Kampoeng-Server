package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.response.WilayahRTWithRwDTO;
import com.e_kampoeng.response.WilayahRWResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WilayahRWService {
    WilayahRWResponseDTO createWilayahRW(WilayahRWRequestDTO requestDTO);
    WilayahRWResponseDTO updateWilayahRW(Long id, WilayahRWRequestDTO requestDTO);
    void deleteWilayahRW(Long id);
    WilayahRWResponseDTO getWilayahRWById(Long id);
    List<WilayahRTWithRwDTO> getRTsByRW(Long rwId);
    Page<WilayahRWModel> getAllWilayahRW(Pageable pageable);
}
