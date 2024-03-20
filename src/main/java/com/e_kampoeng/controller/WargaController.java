package com.e_kampoeng.controller;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.WargaResponseDTO;
import com.e_kampoeng.service.WargaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<WargaModel> getWargaByRW(@PathVariable Long rwId) {
        return wargaService.getWargaByRW(rwId);
    }

    @GetMapping("/by-rt/{rtId}")
    public List<WargaModel> getWargaByRT(@PathVariable Long rtId) {
        return wargaService.getWargaByRT(rtId);
    }
}
