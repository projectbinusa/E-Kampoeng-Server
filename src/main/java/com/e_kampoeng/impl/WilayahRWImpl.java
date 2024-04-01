package com.e_kampoeng.impl;

import org.apache.poi.ss.usermodel.*;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.service.WilayahRWService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class WilayahRWImpl implements WilayahRWService {
    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    // GET ALL DATA WILAYAH RW WITH PAGINATION
    @Override
    public Page<WilayahRWModel> getAllWilayahRW(Pageable pageable) {
        return wilayahRWRepository.findAll(pageable);
    }

    // GET DATA WILAYAH RW BY ID
    @Override
    public WilayahRWModel getWilayahRWById(Long id) {
        WilayahRWModel wilRW = wilayahRWRepository.findById(id).orElse(null);
        if (wilRW == null) {
            throw new NotFoundException("Wilayah RW Id Not Found");
        }
        return wilRW;
    }

    // CREATE DATA WILAYAH RW
    @Override
    public WilayahRWModel createWilayahRW(WilayahRWRequestDTO requestDTO) {
        WilayahRWModel wilayahRW = new WilayahRWModel();
        wilayahRW.setNamaDusun(requestDTO.getNamaDusun());
        wilayahRW.setNomorRw(requestDTO.getNomorRw());
        return wilayahRWRepository.save(wilayahRW);
    }

    // UPDATE DATA WILAYAH RW
    @Override
    public WilayahRWModel updateWilayahRW(Long id, WilayahRWRequestDTO requestDTO) {
        WilayahRWModel wilRW = wilayahRWRepository.findById(id).orElse(null);
        if (wilRW == null) {
            throw new NotFoundException("Wilayah RW Id Not Found");
        }
        wilRW.setNamaDusun(requestDTO.getNamaDusun());
        wilRW.setNomorRw(requestDTO.getNomorRw());
        return wilayahRWRepository.save(wilRW);
    }

    // DELETE DATA WILAYAH RW BY ID
    @Override
    public Map<String, Boolean> deleteWilayahRW(Long id) {
        try {
            wilayahRWRepository.deleteById(id);
            Map<String, Boolean> obj = new HashMap<>();
            obj.put("Deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }

    // Export Excell
    @Override
    public byte[] exportToExcel() throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Wilayah RW Data");

            // Header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nama Dusun");
            headerRow.createCell(2).setCellValue("Nomor RW");

            // Data rows
            List<WilayahRWModel> wilayahRWList = wilayahRWRepository.findAll();
            int rowNum = 1;
            for (WilayahRWModel wilayahRW : wilayahRWList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(wilayahRW.getId());
                row.createCell(1).setCellValue(wilayahRW.getNamaDusun());
                row.createCell(2).setCellValue(wilayahRW.getNomorRw());
            }

            // Write to ByteArrayOutputStream
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }
    @Override
    @Transactional
    public List<WilayahRWRequestDTO> importFromExcel(MultipartFile file) throws IOException {
        List<WilayahRWRequestDTO> importedWilayahRW = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            // Skip header row
            if (iterator.hasNext()) {
                iterator.next();
            }
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                WilayahRWRequestDTO wilayahRW = createWilayahRWFromRow(currentRow);
                // Save to database
                WilayahRWModel savedWilayahRW = saveWilayahRW(wilayahRW);
                importedWilayahRW.add(convertToDTO(savedWilayahRW));
            }
        }
        return importedWilayahRW;
    }

    private WilayahRWModel saveWilayahRW(WilayahRWRequestDTO wilayahRWRequestDTO) {
        WilayahRWModel wilayahRW = new WilayahRWModel();
        wilayahRW.setNamaDusun(wilayahRWRequestDTO.getNamaDusun());
        wilayahRW.setNomorRw(wilayahRWRequestDTO.getNomorRw());
        // Save to database
        return wilayahRWRepository.save(wilayahRW);
    }

    private WilayahRWRequestDTO convertToDTO(WilayahRWModel wilayahRW) {
        WilayahRWRequestDTO dto = new WilayahRWRequestDTO();
        dto.setNamaDusun(wilayahRW.getNamaDusun());
        dto.setNomorRw(wilayahRW.getNomorRw());
        return dto;
    }

    private WilayahRWRequestDTO createWilayahRWFromRow(Row row) {
        WilayahRWRequestDTO wilayahRW = new WilayahRWRequestDTO();
        wilayahRW.setNamaDusun(getStringValueFromCell(row.getCell(0)));
        wilayahRW.setNomorRw(getLongValueFromCell(row.getCell(1)));
        return wilayahRW;
    }

    private String getStringValueFromCell(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else {
            // Handle other cell types if needed
            return null;
        }
    }

    private Long getLongValueFromCell(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            double numericValue = cell.getNumericCellValue();
            // Check if the numeric value has a fractional part
            if (numericValue == (long) numericValue) {
                return (long) numericValue; // Return as Long if it's an integer
            } else {
                // Handle decimal values here, such as rounding or throwing an exception
                // For example, rounding to the nearest whole number:
                return Math.round(numericValue);
            }
        } else {
            // Handle other cell types if needed
            return null;
        }
    }
}
