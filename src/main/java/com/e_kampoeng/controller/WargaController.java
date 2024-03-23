package com.e_kampoeng.controller;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.WargaResponseDTO;
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
    public List<WargaModel> getAllWarga() {
        return wargaService.getAllWarga();
    }

    @PostMapping
    public WargaModel createWarga(@RequestBody WargaRequestDTO requestDTO) {
        return wargaService.createWarga(requestDTO);
    }

    @GetMapping("/by-rw/{rwId}")
    public Page<WargaModel> getWargaByRW(@PathVariable Long rwId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return wargaService.getWargaByRW(rwId, pageable);
    }

    @GetMapping("/by-rt/{rtId}")
    public Page<WargaModel> getWargaByRT(@PathVariable Long rtId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return wargaService.getWargaByRT(rtId, pageable);
    }
}
