package com.e_kampoeng.controller;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WilayahRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/wilayah-rt")
@CrossOrigin(origins = "*")
public class WilayahRTController {
    @Autowired
    private WilayahRTService wilayahRTService;

    @GetMapping
    public ResponseEntity<CustomResponse<Page<WilayahRTModel>>> getAllWilayahRT(Pageable pageable) {
        Page<WilayahRTModel> allWilayahRT = wilayahRTService.getAllWilayahRT(pageable);

        CustomResponse<Page<WilayahRTModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(allWilayahRT);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<WilayahRTModel>> createWilayahRT(@RequestBody WilayahRTRequestDTO requestDTO) {
        WilayahRTModel createdWilayahRT = wilayahRTService.createWilayahRT(requestDTO);

        CustomResponse<WilayahRTModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Wilayah RT created successfully");
        response.setData(createdWilayahRT);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/by-wilrw/{wilayahRWId}")
    public ResponseEntity<CustomResponse<Page<WilayahRTModel>>> getWilayahRTByWilayahRWId(@PathVariable Long wilayahRWId, Pageable pageable) {
        Page<WilayahRTModel> wilayahRTByWilayahRWId = wilayahRTService.getWilayahRTByWilayahRWId(wilayahRWId, pageable);

        CustomResponse<Page<WilayahRTModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(wilayahRTByWilayahRWId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRTModel>> getWilayahRTById(@PathVariable Long id) {
        WilayahRTModel wilayahRTById = wilayahRTService.getWilayahRTById(id);

        CustomResponse<WilayahRTModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(wilayahRTById);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRTRequestDTO>> updateWilayahRT(@PathVariable Long id, @RequestBody WilayahRTRequestDTO requestDTO) {
        WilayahRTRequestDTO updatedWilayahRT = wilayahRTService.updateWilayahRT(id, requestDTO);

        CustomResponse<WilayahRTRequestDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Wilayah RT updated successfully");
        response.setData(updatedWilayahRT);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteWilayahRT(@PathVariable Long id) {
        Map<String, Boolean> deleteResult = wilayahRTService.deleteWilayahRT(id);

        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Wilayah RT deleted successfully");
        response.setData(deleteResult);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/export-excel")
    public ResponseEntity<byte[]> exportToExcel() {
        try {
            byte[] excelBytes = wilayahRTService.exportToExcel();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "wilayah_rt_data.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/export-excel/{wilayahRWId}")
    public ResponseEntity<byte[]> exportToExcelByWilayahRWId(@PathVariable Long wilayahRWId) {
        try {
            byte[] excelBytes = wilayahRTService.exportToExcelByWilayahRWId(wilayahRWId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "wilayah_rt_data.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/import-excel")
    public ResponseEntity<?> importWilayahRT(@RequestPart("file") MultipartFile file) {
        try {
            List<WilayahRTRequestDTO> importedWilayahRT = wilayahRTService.importFromExcel(file);
            return ResponseEntity.ok(importedWilayahRT);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import data: " + e.getMessage());
        }
    }
}
