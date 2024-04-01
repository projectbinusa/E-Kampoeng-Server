package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WilayahRTService {
    WilayahRTModel createWilayahRT(WilayahRTRequestDTO requestDTO);
    Page<WilayahRTModel> getAllWilayahRT(Pageable pageable);
    Page<WilayahRTModel> getWilayahRTByWilayahRWId(Long wilayahRWId, Pageable pageable);
    WilayahRTModel getWilayahRTById(Long id);
    WilayahRTRequestDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO);
    byte[] exportToExcel() throws IOException;
    byte[] exportToExcelByWilayahRWId(Long wilayahRWId) throws IOException;
    Map<String, Boolean> deleteWilayahRT(Long id);
}

