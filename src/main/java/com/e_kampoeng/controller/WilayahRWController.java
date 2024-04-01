package com.e_kampoeng.controller;

import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WilayahRWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/wilayah-rw")
@CrossOrigin(origins = "*")
public class WilayahRWController {
    @Autowired
    private WilayahRWService wilayahRWService;

    @GetMapping
    public ResponseEntity<CustomResponse<Page<WilayahRWModel>>> getAllWilayahRW(Pageable pageable) {
        Page<WilayahRWModel> allWilayahRW = wilayahRWService.getAllWilayahRW(pageable);

        CustomResponse<Page<WilayahRWModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(allWilayahRW);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRWModel>> getWilayahRWById(@PathVariable Long id) {
        WilayahRWModel wilayahRWById = wilayahRWService.getWilayahRWById(id);

        CustomResponse<WilayahRWModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(wilayahRWById);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<WilayahRWModel>> createWilayahRW(@RequestBody WilayahRWRequestDTO requestDTO) {
        WilayahRWModel createdWilayahRW = wilayahRWService.createWilayahRW(requestDTO);

        CustomResponse<WilayahRWModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Wilayah RW created successfully");
        response.setData(createdWilayahRW);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRWModel>> updateWilayahRW(@PathVariable Long id, @RequestBody WilayahRWRequestDTO requestDTO) {
        WilayahRWModel updatedWilayahRW = wilayahRWService.updateWilayahRW(id, requestDTO);

        CustomResponse<WilayahRWModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Wilayah RW updated successfully");
        response.setData(updatedWilayahRW);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteWilayahRW(@PathVariable Long id) {
        Map<String, Boolean> deleteResult = wilayahRWService.deleteWilayahRW(id);

        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Wilayah RW deleted successfully");
        response.setData(deleteResult);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/export-excel")
    public ResponseEntity<byte[]> exportToExcel() {
        try {
            byte[] excelBytes = wilayahRWService.exportToExcel();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "wilayah_rw_data.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
