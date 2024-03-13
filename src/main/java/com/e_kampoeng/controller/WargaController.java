package com.e_kampoeng.controller;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.response.WargaResponseDTO;
import com.e_kampoeng.service.WargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-kampoeng/api/warga")
@CrossOrigin(origins = "http://localhost:3000")
public class WargaController {

    @Autowired
    private WargaService wargaService;


    @GetMapping
    public ResponseEntity<CustomResponse<Page<WargaResponseDTO>>> getAllWarga(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WargaResponseDTO> responseDTOs = wargaService.getAllWarga(pageable);
        CustomResponse<Page<WargaResponseDTO>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(responseDTOs);
        response.setMessage("All Warga retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<WargaResponseDTO>> getWargaById(@PathVariable Long id) {
        WargaResponseDTO responseDTO = wargaService.getWargaById(id);
        CustomResponse<WargaResponseDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(responseDTO);
        response.setMessage("Warga retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<WargaResponseDTO>> createWarga(@RequestBody WargaRequestDTO requestDTO) {
        WargaResponseDTO responseDTO = wargaService.createWarga(requestDTO);
        CustomResponse<WargaResponseDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setData(responseDTO);
        response.setMessage("Warga created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<WargaResponseDTO>> updateWarga(@PathVariable Long id, @RequestBody WargaRequestDTO requestDTO) {
        WargaResponseDTO responseDTO = wargaService.updateWarga(id, requestDTO);
        CustomResponse<WargaResponseDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(responseDTO);
        response.setMessage("Warga updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteWarga(@PathVariable Long id) {
        wargaService.deleteWarga(id);
        CustomResponse<Void> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.NO_CONTENT.value());
        response.setMessage("Warga deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
