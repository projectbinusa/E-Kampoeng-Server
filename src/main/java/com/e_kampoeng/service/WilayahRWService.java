package com.e_kampoeng.service;

import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WilayahRWService {
    WilayahRWModel createWilayahRW(WilayahRWRequestDTO requestDTO);
    WilayahRWModel updateWilayahRW(Long id, WilayahRWRequestDTO requestDTO);
    WilayahRWModel getWilayahRWById(Long id);
    byte[] exportToExcel() throws IOException;
    List<WilayahRWRequestDTO> importFromExcel(MultipartFile file) throws IOException;
    Page<WilayahRWModel> getAllWilayahRW(Pageable pageable);
    Map<String, Boolean> deleteWilayahRW(Long id);

}
