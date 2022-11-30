package com.data.penduduk.controller;

import com.data.penduduk.model.UndanganWarga;
import com.data.penduduk.service.UndanganWargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UndanganWargaController {
    @Autowired
    UndanganWargaService undanganWargaService;

    @GetMapping("/undangan")
    public ResponseEntity<?> getAllUndangan() {
        List<UndanganWarga> undanganWarga = undanganWargaService.getAllUndangan();
        return new ResponseEntity<>(undanganWarga, HttpStatus.OK);
    }

    @GetMapping("/undangan-{id}")
    public ResponseEntity<?> getUndanganById(@PathVariable("id") Long id) {
        UndanganWarga undanganWarga = undanganWargaService.getUndanganById(id);
        return new ResponseEntity<>(undanganWarga, HttpStatus.OK);
    }

    @PostMapping("/add-undangan")
    public ResponseEntity<?> createUndangan(@RequestBody UndanganWarga undanganWarga) {
        UndanganWarga undanganWarga1 = undanganWargaService.createUndangan(undanganWarga);
        return new ResponseEntity<>(undanganWarga1, HttpStatus.CREATED);
    }

    @DeleteMapping("/undangan-{id}")
    public ResponseEntity<?> deleteUndangan(@PathVariable("id") Long id) {
        undanganWargaService.deleteUndangan(id);
        return new ResponseEntity<>("deleted success!", HttpStatus.OK);
    }


}
