package com.data.penduduk.service;

import com.data.penduduk.helper.ExcelHelper;
import com.data.penduduk.helper.ExcelKkHelper;
import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Rt;
import com.data.penduduk.repository.KkRepository;
import com.data.penduduk.repository.RtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelKkService {
    @Autowired
    KkRepository kkRepository;

    @Autowired
    RtRepository rtRepository;

    public void save(MultipartFile file, Long id) {
        try {
            Rt rt = rtRepository.findById(id).orElse(null);
            List<Kk> kk = ExcelKkHelper.excelToKka(file.getInputStream(), rt);
            kkRepository.saveAll(kk);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
