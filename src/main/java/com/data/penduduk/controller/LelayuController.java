package com.data.penduduk.controller;

import com.data.penduduk.model.Lelayu;
import com.data.penduduk.service.LelayuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LelayuController {
    @Autowired
    LelayuService lelayuService;

    @GetMapping("/lelayu")
    public ResponseEntity<?> getAllLelayu() {
        List<Lelayu> lelayu = lelayuService.getAllLelayu();
        return new ResponseEntity<>(lelayu, HttpStatus.OK);
    }

    @GetMapping("/lelayu-{id}")
    public ResponseEntity<?> getLelayuById(@PathVariable("id") Long id) {
        Lelayu lelayu = lelayuService.getLelayuById(id);
        return new ResponseEntity<>(lelayu, HttpStatus.OK);
    }

    @PostMapping("/add-lelayu")
    public ResponseEntity<?> createLelayu(@RequestBody Lelayu lelayu) {
        Lelayu lelayu1 = lelayuService.createLelayu(lelayu);
        return new ResponseEntity<>(lelayu1, HttpStatus.CREATED);
    }

    @DeleteMapping("/lelayu-{id}")
    public ResponseEntity<?> deleteLelayu(@PathVariable("id") Long id) {
        lelayuService.deleteLelayu(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
}
