package com.e_kampoeng.controller;

import com.e_kampoeng.model.RTWilayah;
import com.e_kampoeng.service.RTWilayahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-kampoeng/api/rt")
public class RTWilayahController {
    @Autowired
    private RTWilayahService rtWilayahService;

    @PostMapping("/tambah-kepala-rt")
    public ResponseEntity<?> tambahKepalaRT(
            @RequestParam("wilayahRTId") Long wilayahRTId,
            @RequestParam("wargaId") Long wargaId) {
        try {
            RTWilayah rtWilayah = rtWilayahService.tambahKepalaRT(wilayahRTId, wargaId);
            return ResponseEntity.ok(rtWilayah);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

