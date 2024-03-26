package com.e_kampoeng.controller;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.RWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/rw")
@CrossOrigin(origins = "*")
public class RWController {
    @Autowired
    private RWService rwService;

    @PostMapping("/tambah-kepala")
    public ResponseEntity<CustomResponse<RWModel>> tambahKepalaRW(@RequestParam Long wilayahRWId, @RequestParam Long wargaId) {
        RWModel addedKepalaRW = rwService.tambahKepalaRW(wilayahRWId, wargaId);

        CustomResponse<RWModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Kepala RW added successfully");
        response.setData(addedKepalaRW);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CustomResponse<Page<RWModel>>> getAllRW(Pageable pageable) {
        Page<RWModel> allRW = rwService.getAllRW(pageable);

        CustomResponse<Page<RWModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(allRW);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<RWModel>> getRWById(@PathVariable Long id) {
        RWModel rw = rwService.getRWById(id);

        CustomResponse<RWModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(rw);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<RWModel>> updateRW(@PathVariable Long id, @RequestParam Long wargaId) {
        RWModel updatedRW = rwService.updateRW(id, wargaId);

        CustomResponse<RWModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("RW updated successfully");
        response.setData(updatedRW);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteRW(@PathVariable Long id) {
        Map<String, Boolean> deleteResult = rwService.deleteRW(id);

        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("RW deleted successfully");
        response.setData(deleteResult);

        return ResponseEntity.ok(response);
    }
}
