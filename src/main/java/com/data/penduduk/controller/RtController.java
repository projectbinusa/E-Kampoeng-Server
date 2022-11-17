package com.data.penduduk.controller;

import com.data.penduduk.model.Rt;
import com.data.penduduk.service.RtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RtController {

    @Autowired
    RtService rtService;

    @GetMapping("/rt")
    public ResponseEntity<?> getAllRt() {
        List<Rt> rt = rtService.getAllRt();
        return new ResponseEntity<>(rt, HttpStatus.OK);
    }


    @GetMapping("/rw-{id}/rt")
    public ResponseEntity<?> getRtByRwId(@PathVariable("id") Long id) {
        List<Rt> rt = rtService.getRtByUser(id);
        return new ResponseEntity<>(rt, HttpStatus.OK);
    }

    @GetMapping("/rt-{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Rt rt = rtService.getRtById(id);
        return new ResponseEntity<>(rt, HttpStatus.OK);
    }

    @DeleteMapping("/rt-{id}")
    public ResponseEntity<?> deleteRt(@PathVariable("id") Long id) {
        rtService.deleteRt(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }



}
