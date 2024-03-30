package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.service.WilayahRTService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WilayahRTImpl implements WilayahRTService {
    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    @Autowired
    private WargaRepository wargaRepository;

    public Page<WilayahRTModel> getAllWilayahRT(Pageable pageable) {
        return wilayahRTRepository.findAll(pageable);
    }

    @Override
    public WilayahRTModel createWilayahRT(WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRT = new WilayahRTModel();
        wilayahRT.setNomorRt(requestDTO.getNomorRt());

        // Mengambil data Wilayah_RW dari repositori
        WilayahRWModel wilayahRW = wilayahRWRepository.findById(requestDTO.getWilayahRWId()).orElse(null);
        if (wilayahRW == null) {
            throw new NotFoundException("Wilayah RW Id Not Found");
        }
        wilayahRT.setWilayahRW(wilayahRW);

        return wilayahRTRepository.save(wilayahRT);
    }

    @Override
    public Page<WilayahRTModel> getWilayahRTByWilayahRWId(Long wilayahRWId, Pageable pageable) {
        return wilayahRTRepository.findByWilayahRW_Id(wilayahRWId, pageable);
    }

    @Override
    public WilayahRTModel getWilayahRTById(Long id) {
        return wilayahRTRepository.findById(id).orElse(null);
    }

    @Override
    public WilayahRTRequestDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + id));
        BeanUtils.copyProperties(requestDTO, wilayahRTModel);
        WilayahRWModel wilRW = wilayahRWRepository.findById(requestDTO.getWilayahRWId())
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + requestDTO.getWilayahRWId()));
        wilayahRTModel.setWilayahRW(wilRW); // Menetapkan Wilayah RW ke Wilayah RT
        WilayahRTModel updatedModel = wilayahRTRepository.save(wilayahRTModel);
        WilayahRTRequestDTO responseDTO = new WilayahRTRequestDTO();
        BeanUtils.copyProperties(updatedModel, responseDTO);
        responseDTO.setWilayahRWId(wilRW.getId()); // Set ID Wilayah RW di respons
        return responseDTO;
    }

    @Override
    public Map<String, Boolean> deleteWilayahRT(Long id) {
        try {
            wilayahRTRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }

    @Override
    public byte[] exportToExcel() throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Wilayah RT Data");

            // Header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nomor RT");
            headerRow.createCell(2).setCellValue("Wilayah RW ID"); // Sesuaikan dengan kebutuhan Anda

            // Data rows
            List<WilayahRTModel> wilayahRTList = wilayahRTRepository.findAll();
            int rowNum = 1;
            for (WilayahRTModel wilayahRT : wilayahRTList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(wilayahRT.getId());
                row.createCell(1).setCellValue(wilayahRT.getNomorRt());
                row.createCell(2).setCellValue(wilayahRT.getWilayahRW().getId()); // Sesuaikan dengan kebutuhan Anda
            }

            // Write to ByteArrayOutputStream
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }

    @Override
    public byte[] exportToExcelByWilayahRWId(Long wilayahRWId) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Wilayah RT Data");

            // Header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nomor RT");
            headerRow.createCell(2).setCellValue("Wilayah RW ID"); // Sesuaikan dengan kebutuhan Anda

            // Data rows
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE); // Mengambil semua data
            Page<WilayahRTModel> wilayahRTPage = wilayahRTRepository.findByWilayahRW_Id(wilayahRWId, pageable);
            List<WilayahRTModel> wilayahRTList = wilayahRTPage.getContent(); // Mendapatkan daftar konten dari halaman

            int rowNum = 1;
            for (WilayahRTModel wilayahRT : wilayahRTList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(wilayahRT.getId());
                row.createCell(1).setCellValue(wilayahRT.getNomorRt());
                row.createCell(2).setCellValue(wilayahRT.getWilayahRW().getId()); // Sesuaikan dengan kebutuhan Anda
            }

            // Write to ByteArrayOutputStream
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }

}
