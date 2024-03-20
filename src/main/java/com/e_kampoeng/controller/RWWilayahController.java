package com.e_kampoeng.controller;

import com.e_kampoeng.model.RWWilayah;
import com.e_kampoeng.service.RWWilayahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-kampoeng/api/rw")
public class RWWilayahController {
    @Autowired
    private RWWilayahService rwWilayahService;

    @PostMapping("/tambah-kepala-rw")
    public ResponseEntity<?> tambahKepalaRW(
            @RequestParam("wilayahRWId") Long wilayahRWId,
            @RequestParam("wargaId") Long wargaId) {
        try {
            RWWilayah rwWilayah = rwWilayahService.tambahKepalaRW(wilayahRWId, wargaId);
            return ResponseEntity.ok(rwWilayah);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Implementasi endpoint lain jika diperlukan
}
