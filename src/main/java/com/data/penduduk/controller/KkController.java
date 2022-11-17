package com.data.penduduk.controller;

import com.data.penduduk.model.Kk;
import com.data.penduduk.service.KkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class KkController {

    @Autowired
    KkService kkService;

    @GetMapping("/kk")
    public ResponseEntity<?> getAllKk() {
        List<Kk> kk = kkService.getAllKk();
        return new ResponseEntity<>(kk, HttpStatus.OK);
    }

    @GetMapping("/rt-{id}/kk")
    public ResponseEntity<?> getKkByRtId(@PathVariable("id") Long id) {
        List<Kk> kk = kkService.getKkByRt(id);
        return new ResponseEntity<>(kk, HttpStatus.OK);
    }

    @GetMapping("/kk-{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Kk kk = kkService.getKkById(id);
        return new ResponseEntity<>(kk, HttpStatus.OK);
    }

    @PostMapping("/rt-{id}/add-kk")
    public ResponseEntity<?> addKk(@RequestBody Kk kk, @PathVariable("id") Long id) {
        Kk kk1 = kkService.createKk(kk, id);
        return new ResponseEntity<>(kk1, HttpStatus.CREATED);
    }

    @PutMapping("/kk-{id}")
    public ResponseEntity<?> updateKk(@PathVariable("id") Long id, @RequestBody Kk kk) {
        Kk kk1 = kkService.editKk(id, kk.getNama(), kk.getTempat_lahir(), kk.getTgl_lahir(), kk.getGender(), kk.getAgama(), kk.getStatus());
        return new ResponseEntity<>(kk1, HttpStatus.OK);
    }

    @DeleteMapping("/kk-{id}")
    public ResponseEntity<?> deleteKk(@PathVariable("id") Long id) {
        kkService.deleteKk(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
}
