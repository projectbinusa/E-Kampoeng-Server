package com.e_kampoeng.controller;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kampoeng/api/warga")
@CrossOrigin(origins = "*")
public class WargaController {
    @Autowired
    private WargaService wargaService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<WargaModel>>> getAllWarga() {
        List<WargaModel> allWarga = wargaService.getAllWarga();

        CustomResponse<List<WargaModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(allWarga);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<WargaModel>> createWarga(@RequestBody WargaRequestDTO requestDTO) {
        WargaModel createdWarga = wargaService.createWarga(requestDTO);

        CustomResponse<WargaModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Warga created successfully");
        response.setData(createdWarga);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/by-rw/{rwId}")
    public ResponseEntity<CustomResponse<Page<WargaModel>>> getWargaByRW(@PathVariable Long rwId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WargaModel> wargaByRW = wargaService.getWargaByRW(rwId, pageable);

        CustomResponse<Page<WargaModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(wargaByRW);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-rt/{rtId}")
    public ResponseEntity<CustomResponse<Page<WargaModel>>> getWargaByRT(@PathVariable Long rtId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WargaModel> wargaByRT = wargaService.getWargaByRT(rtId, pageable);

        CustomResponse<Page<WargaModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(wargaByRT);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<WargaModel>> updateWarga(@PathVariable Long id, @RequestBody WargaRequestDTO requestDTO) {
        WargaModel updatedWarga = wargaService.updateWarga(id, requestDTO);

        CustomResponse<WargaModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Warga updated successfully");
        response.setData(updatedWarga);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteWarga(@PathVariable Long id) {
        wargaService.deleteWarga(id);

        CustomResponse<Void> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.NO_CONTENT.value());
        response.setMessage("Warga deleted successfully");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
