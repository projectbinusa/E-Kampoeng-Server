package com.data.penduduk.controller;

import com.data.penduduk.model.LaporanWarga;
import com.data.penduduk.service.LaporanWargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LaporanWargaController {
    @Autowired
    LaporanWargaService laporanWargaService;

    @GetMapping("/laporan-warga")
    public ResponseEntity<?> getAllLaporan() {
        List<LaporanWarga> laporanWarga = laporanWargaService.getAllLaporan();
        return new ResponseEntity<>(laporanWarga, HttpStatus.OK);
    }

    @GetMapping("/laporan-warga-{id}")
    public ResponseEntity<?> getLaporanById(@PathVariable("id") Long id) {
        LaporanWarga laporanWarga = laporanWargaService.getLaporanById(id);
        return new ResponseEntity<>(laporanWarga, HttpStatus.OK);
    }

    @PostMapping("/add-laporan")
    public ResponseEntity<?> addLaporan(@RequestBody LaporanWarga laporanWarga) {
        LaporanWarga laporanWarga1 = laporanWargaService.createLaporan(laporanWarga);
        return new ResponseEntity<>(laporanWarga1, HttpStatus.CREATED);
    }

    @PutMapping("/laporan-warga-{id}")
    public ResponseEntity<?> updateLaporan(@PathVariable("id") Long id, @RequestBody LaporanWarga laporanWarga) {
        LaporanWarga laporanWarga1 = laporanWargaService.editLaporan(id, laporanWarga.getNama_pelapor(), laporanWarga.getJudul_laporan(), laporanWarga.getLaporan(), laporanWarga.getTgl_laporan());
        return new ResponseEntity<>(laporanWarga1, HttpStatus.OK);
    }

    @DeleteMapping("/laporan-warga-{id}")
    public ResponseEntity<?> deleteLaporan(@PathVariable("id") Long id) {
        laporanWargaService.deleteLaporan(id);
        return new ResponseEntity<>("succsess delete!", HttpStatus.OK);
    }
}
