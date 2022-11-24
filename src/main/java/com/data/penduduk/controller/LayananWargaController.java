package com.data.penduduk.controller;

import com.data.penduduk.model.LayananWarga;
import com.data.penduduk.service.LayananWargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LayananWargaController {
    @Autowired
    LayananWargaService layananWargaService;

    @RequestMapping("/add-layananwarga/rt-{id}")
    public ResponseEntity<?> addLayananWarga(@PathVariable("id") Long id, @RequestBody LayananWarga layananWarga) {
        LayananWarga layananWarga1 = layananWargaService.craeteLayananWarga(layananWarga, id);
        return new ResponseEntity<>(layananWarga1, HttpStatus.OK);
    }
}
