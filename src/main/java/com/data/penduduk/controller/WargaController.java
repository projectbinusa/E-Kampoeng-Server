package com.data.penduduk.controller;

import com.data.penduduk.repository.WargaRepository;
import com.data.penduduk.service.WargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/warga")
public class WargaController {

    @Autowired
    private WargaService serviceWarga;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(serviceWarga.getAllWarga(), HttpStatus.OK);
    }
}
