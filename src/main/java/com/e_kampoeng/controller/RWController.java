package com.e_kampoeng.controller;

import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.response.RWResponseDTO;
import com.e_kampoeng.service.RWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e-kampoeng/api/rw")
@CrossOrigin(origins = "*")
public class RWController {

    @Autowired
    private RWService rwService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<RWModel>>> getAllRW() {
        CustomResponse<List<RWModel>> response = new CustomResponse<>();
        List<RWModel> rwList = rwService.getAllRW();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(rwList);
        response.setMessage("Data semua RW berhasil ditemukan.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rw/{id}")
    public ResponseEntity<CustomResponse<RWModel>> getRWById(@PathVariable Long id) {
        CustomResponse<RWModel> response = new CustomResponse<>();
        Optional<RWModel> rwOptional = rwService.getRWById(id);
        if (rwOptional.isPresent()) {
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(rwOptional.get());
            response.setMessage("RW dengan ID " + id + " berhasil ditemukan.");
            return ResponseEntity.ok(response);
        } else {
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("RW dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/rw")
    public ResponseEntity<CustomResponse<RWModel>> assignRW(@RequestParam Long wargaId, @RequestParam Long nomorRW) {
        CustomResponse<RWModel> response = new CustomResponse<>();
        try {
            RWModel rwModel = rwService.assignRW(wargaId, nomorRW);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(rwModel);
            response.setMessage("Warga berhasil ditetapkan sebagai RW.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/rw/{rwId}")
    public ResponseEntity<CustomResponse<RWResponseDTO>> updateRW(@PathVariable Long rwId, @RequestParam Long wargaId) {
        CustomResponse<RWResponseDTO> response = new CustomResponse<>();
        try {
            RWModel rwModel = rwService.updateRW(rwId, wargaId);

            // Membuat response DTO dengan hanya menyertakan data yang diperlukan
            RWResponseDTO responseData = new RWResponseDTO();
            responseData.setId(rwModel.getId());
            responseData.setNomorRW(rwModel.getNomorRW());
            responseData.setWarga(rwModel.getWarga());

            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(responseData);
            response.setMessage("RW berhasil diperbarui.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/rw/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteRW(@PathVariable Long id) {
        CustomResponse<Void> response = new CustomResponse<>();
        try {
            rwService.deleteRW(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("RW dengan ID " + id + " berhasil dihapus.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
