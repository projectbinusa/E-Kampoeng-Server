package com.data.penduduk.service;

import com.data.penduduk.helper.ExcelHelper;
import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Warga;
import com.data.penduduk.repository.KkRepository;
import com.data.penduduk.repository.WargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    WargaRepository wargaRepository;

    @Autowired
    KkRepository kkRepository;


    public void save(MultipartFile file, Long id) {
        try {
            Kk kk = kkRepository.findById(id).orElse(null);
            List<Warga> warga = ExcelHelper.excelToWargas(file.getInputStream(), kk);
            wargaRepository.saveAll(warga);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
