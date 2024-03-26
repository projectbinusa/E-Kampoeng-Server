package com.e_kampoeng.controller;

import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.RTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/rt")
@CrossOrigin(origins = "*")
public class RTController {
    @Autowired
    private RTService rtService;

    @PostMapping("/tambah-kepala")
    public ResponseEntity<CustomResponse<RTModel>> tambahKepalaRT(@RequestParam Long wilayahRTId, @RequestParam Long wargaId) {
        RTModel addedKepalaRT = rtService.tambahKepalaRT(wilayahRTId, wargaId);

        CustomResponse<RTModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Kepala RT added successfully");
        response.setData(addedKepalaRT);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CustomResponse<Page<RTModel>>> getAllRT(Pageable pageable) {
        Page<RTModel> allRT = rtService.getAllRT(pageable);

        CustomResponse<Page<RTModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(allRT);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<RTModel>> getRTById(@PathVariable Long id) {
        RTModel rt = rtService.getRTById(id);

        CustomResponse<RTModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(rt);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<RTModel>> updateRT(@PathVariable Long id, @RequestParam Long wargaId, @RequestParam Long nomorRT) {
        RTModel updatedRT = rtService.updateRT(id, wargaId, nomorRT);

        CustomResponse<RTModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("RT updated successfully");
        response.setData(updatedRT);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteRT(@PathVariable Long id) {
        Map<String, Boolean> deleteResult = rtService.deleteRT(id);

        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("RT deleted successfully");
        response.setData(deleteResult);

        return ResponseEntity.ok(response);
    }
}
