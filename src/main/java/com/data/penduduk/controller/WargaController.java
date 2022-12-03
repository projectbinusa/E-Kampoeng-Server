package com.data.penduduk.controller;

import com.data.penduduk.model.Warga;
import com.data.penduduk.service.WargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class WargaController {

    @Autowired
    WargaService wargaService;

    @GetMapping("/warga")
    public ResponseEntity<?> getAllWarga(@Param("nama") String nama) {
        List<Warga> warga = wargaService.getAllWarga(nama);
        return new ResponseEntity<>(warga, HttpStatus.OK);
    }

    @GetMapping("/kk-{id}/warga")
    public ResponseEntity<?> getWargaByKkId(@PathVariable("id") Long id) {
        List<Warga> warga = wargaService.getWargaByKk(id);
        return new ResponseEntity<>(warga, HttpStatus.OK);
    }

    @GetMapping("/warga-{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Warga warga = wargaService.getWargaById(id);
        return new ResponseEntity<>(warga, HttpStatus.OK);
    }

    @PostMapping("/kk-{id}/add-warga")
    public ResponseEntity<?> addWarga(@RequestBody Warga warga, @PathVariable("id") Long id) {
        Warga warga1 = wargaService.createWarga(warga, id);
        return new ResponseEntity<>(warga1, HttpStatus.CREATED);
    }

    @PutMapping("/warga-{id}")
    public ResponseEntity<?> updateWarga(@PathVariable("id") Long id, @RequestBody Warga warga) {
        Warga warga1 = wargaService.editWarga(id, warga.getNama(), warga.getTempat_lahir(), warga.getTgl_lahir(), warga.getNo_kk(), warga.getNik(), warga.getGender(), warga.getAgama(), warga.getStatus());
        return new ResponseEntity<>(warga1, HttpStatus.OK);
    }

    @DeleteMapping("/warga-{id}")
    public ResponseEntity<?> deleteWarga(@PathVariable("id") Long id) {
        wargaService.deleteWarga(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }

}
