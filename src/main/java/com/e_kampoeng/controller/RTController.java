package com.e_kampoeng.controller;

import com.e_kampoeng.dto.RTRequestDTO;
import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.RTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/rt")
public class RTController {
    @Autowired
    private RTService rtService;

    // CREATE Ketua RT
    @PostMapping
    public ResponseEntity<?> tambahKepalaRT(
            @RequestParam("wilayahRTId") Long wilayahRTId,
            @RequestParam("wargaId") Long wargaId) {
        try {
            RTModel rtWilayah = rtService.tambahKepalaRT(wilayahRTId, wargaId);
            return ResponseEntity.ok(rtWilayah);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET LIST RT WITH PAGINATION
    @GetMapping
    public ResponseEntity<CustomResponse<Page<RTModel>>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse<Page<RTModel>> response = new CustomResponse<>();
        Page<RTModel> rtList = rtService.getAllRT(pageable);
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(rtList);
        response.setMessage("Data semua RT berhasil ditemukan.");
        return ResponseEntity.ok(response);
    }

    // GET DATA RT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<RTModel>> getRTById(@PathVariable Long id) {
        CustomResponse<RTModel> response = new CustomResponse<>();
        RTModel rtOptional = rtService.getRTById(id);
        if (rtOptional != null) {
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(rtOptional);
            response.setMessage("RT dengan ID " + id + " berhasil ditemukan.");
            return ResponseEntity.ok(response);
        } else {
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("RT dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // UPDATE DATA RT BY ID
    @PutMapping("/{rtId}")
    public ResponseEntity<CustomResponse<RTRequestDTO>> updateRT(@PathVariable Long rtId, @RequestBody RTRequestDTO rtRequestDTO) {
        CustomResponse<RTRequestDTO> response = new CustomResponse<>();
        try {
            var wargaId = rtRequestDTO.getWargaId();
            var nomorRT = rtRequestDTO.getNomorRT();
            RTModel rtModel = rtService.updateRT(rtId, wargaId, nomorRT);
            // Membuat AssignRTResponse dengan mengatur data warga dan nomor RT
            RTRequestDTO responseData = new RTRequestDTO();
            responseData.setNomorRT(rtModel.getWilayahRT().getNomorRt());
            responseData.setWargaId(rtModel.getWarga().getId()); // Atur ID Warga

            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(responseData);
            response.setMessage("RT berhasil diperbarui.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // DELETE DATA RT
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteRT(@PathVariable Long id) {
        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        try {
            rtService.deleteRT(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("RT dengan ID " + id + " berhasil dihapus.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

