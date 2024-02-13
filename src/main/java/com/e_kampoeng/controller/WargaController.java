package com.e_kampoeng.controller;

import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.response.WargaResponseDTO;
import com.e_kampoeng.service.WargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warga")
@CrossOrigin(origins = "http://localhost:3000")
public class WargaController {

    @Autowired
    private WargaService wargaService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<WargaResponseDTO>>> getAllWarga() {
        List<WargaResponseDTO> responseDTOs = wargaService.getAllWarga();
        CustomResponse<List<WargaResponseDTO>> response = new CustomResponse<>();
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
