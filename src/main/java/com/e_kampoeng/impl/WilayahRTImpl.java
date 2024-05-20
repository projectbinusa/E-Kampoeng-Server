package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;
import com.e_kampoeng.service.WilayahRTService;
import org.apache.poi.ss.usermodel.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class WilayahRTImpl implements WilayahRTService {
    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public Page<WilayahRTResponseDTO> getAllWilayahRT(Pageable pageable) {
        Page<WilayahRTModel> wilayahRTPage = wilayahRTRepository.findAll(pageable);

        // Mengonversi entitas ke DTO
        return wilayahRTPage.map(this::convertToResponseDTO);
    }

    @Override
    public WilayahRTResponseDTO getWilayahRTById(Long id) {
        WilayahRTModel wilayahRT = wilayahRTRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wilayah RT not found with id: " + id));

        return convertToResponseDTO(wilayahRT);
    }

    @Override
    public WilayahRTModel createWilayahRT(WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRT = new WilayahRTModel();
        wilayahRT.setNomorRt(requestDTO.getNomorRt());
        return wilayahRTRepository.save(wilayahRT);
    }


    @Override
    public WilayahRTRequestDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + id));
        BeanUtils.copyProperties(requestDTO, wilayahRTModel);
        WilayahRTModel updatedModel = wilayahRTRepository.save(wilayahRTModel);
        WilayahRTRequestDTO responseDTO = new WilayahRTRequestDTO();
        BeanUtils.copyProperties(updatedModel, responseDTO);
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
            }

            // Write to ByteArrayOutputStream
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }

    // Import Excell Wilayah RT
    @Override
    @Transactional
    public List<WilayahRTRequestDTO> importFromExcel(MultipartFile file) throws IOException {
        List<WilayahRTRequestDTO> importedWilayahRT = new ArrayList<>();
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
                WilayahRTRequestDTO wilayahRT = createWilayahRTFromRow(currentRow);
                // Save to database
                 WilayahRTModel savedWilayahRT = saveWilayahRT(wilayahRT);
                importedWilayahRT.add(convertToRequestDTO(savedWilayahRT));
            }
        }
        return importedWilayahRT;
    }
    private WilayahRTRequestDTO createWilayahRTFromRow(Row row) {
        WilayahRTRequestDTO wilayahRT = new WilayahRTRequestDTO();
        wilayahRT.setNomorRt(getLongValueFromCell(row.getCell(0)));
        return wilayahRT;
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
        } else if (cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getStringCellValue();
            try {
                return Long.valueOf(cellValue);
            } catch (NumberFormatException e) {
                // Handle the parse exception
                e.printStackTrace();
                return null;
            }
        } else {
            // Handle other cell types if needed
            return null;
        }
    }

    private WilayahRTModel saveWilayahRT(WilayahRTRequestDTO wilayahRTRequestDTO) {
        WilayahRTModel wilayahRT = new WilayahRTModel();
        wilayahRT.setNomorRt(wilayahRTRequestDTO.getNomorRt());

        // Simpan ke database
        return wilayahRTRepository.save(wilayahRT);
    }
    @Override
    public WilayahRTModel addKepalaRT(Long wilayahRTId, Long wargaId) {
        WilayahRTModel wilayahRT = wilayahRTRepository.findById(wilayahRTId)
                .orElseThrow(() -> new NotFoundException("Wilayah RT not found with id: " + wilayahRTId));

        WargaModel kepalaRT = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new NotFoundException("Warga not found with id: " + wargaId));

        // Validasi apakah warga berasal dari wilayah RT yang dimaksud
        if (!kepalaRT.getWilayahRT().equals(wilayahRT)) {
            throw new RuntimeException("The specified resident does not belong to the specified RT area");
        }

        wilayahRT.setKepalaRt(kepalaRT);
        return wilayahRTRepository.save(wilayahRT);
    }
    private WilayahRTRequestDTO convertToRequestDTO(WilayahRTModel wilayahRT) {
        WilayahRTRequestDTO dto = new WilayahRTRequestDTO();
        dto.setNomorRt(wilayahRT.getNomorRt());
        return dto;
    }

    private WilayahRTResponseDTO convertToResponseDTO(WilayahRTModel wilayahRT) {
        WilayahRTResponseDTO dto = new WilayahRTResponseDTO();
        dto.setId(wilayahRT.getId());
        dto.setNomorRt(wilayahRT.getNomorRt());
        if (wilayahRT.getKepalaRt() != null) {
            dto.setKepalaRtId(wilayahRT.getKepalaRt().getId());
            dto.setKepalaRtNama(wilayahRT.getKepalaRt().getNama());
        }
        return dto;
    }

}
