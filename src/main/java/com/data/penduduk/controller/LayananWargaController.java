package com.data.penduduk.controller;

import com.data.penduduk.model.LayananWarga;
import com.data.penduduk.service.LayananWargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LayananWargaController {
    @Autowired
    LayananWargaService layananWargaService;

    @GetMapping("/layanan-warga")
    public ResponseEntity<?> getAll() {
        List<LayananWarga> layananWargas = layananWargaService.getAll();
        return new ResponseEntity<>(layananWargas, HttpStatus.OK);
    }

    @GetMapping("/rt-{id}/layanan-warga")
    public ResponseEntity<?> getLayananByRt(@PathVariable("id") Long id) {
        List<LayananWarga> layananWargas = layananWargaService.getLayananWarga(id);
        return new ResponseEntity<>(layananWargas, HttpStatus.OK);
    }

    @RequestMapping("/add-layanan-warga/rt-{id}")
    public ResponseEntity<?> addLayananWarga(@PathVariable("id") Long id, @RequestBody LayananWarga layananWarga) {
        LayananWarga layananWarga1 = layananWargaService.createLayananWarga(layananWarga, id);
        return new ResponseEntity<>(layananWarga1, HttpStatus.OK);
    }

    @DeleteMapping("/layanan-warga-{id}")
    public ResponseEntity<?> deleteLayananWarga(@PathVariable("id") Long id) {
        layananWargaService.deleteLayananWarga(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
}
