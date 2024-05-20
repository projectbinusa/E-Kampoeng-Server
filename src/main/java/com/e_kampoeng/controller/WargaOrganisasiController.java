package com.e_kampoeng.controller;

import com.e_kampoeng.model.WargaOrganisasiModel;
import com.e_kampoeng.request.WargaOrganisasiRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WargaOrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/organisasi-warga")
@CrossOrigin(origins = "*")
public class WargaOrganisasiController {
    @Autowired
    private WargaOrganisasiService wargaOrganisasiService;

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<WargaOrganisasiModel>> addWargaToOrganisasi(
            @RequestBody WargaOrganisasiRequestDTO requestDTO) {
        CustomResponse<WargaOrganisasiModel> response = new CustomResponse<>();
        try {
            WargaOrganisasiModel wargaOrganisasi = wargaOrganisasiService.addWargaToOrganisasi(
                    requestDTO.getWargaId(), requestDTO.getOrganisasiId());

            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(wargaOrganisasi);
            response.setMessage("Warga berhasil ditambahkan ke organisasi.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> removeWargaFromOrganisasi(@PathVariable Long id) {
        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        try {
            wargaOrganisasiService.removeWargaOrganisasi(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Warga berhasil dihapus dari organisasi.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/{organisasiId}/warga")
    public ResponseEntity<CustomResponse<Page<WargaOrganisasiModel>>> getAllWargaInOrganisasiWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size, @PathVariable Long organisasiId) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse<Page<WargaOrganisasiModel>> response = new CustomResponse<>();
        Page<WargaOrganisasiModel> wargaOrganisasiList = wargaOrganisasiService.getAllWargaInOrganisasi(pageable, organisasiId);
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(wargaOrganisasiList);
        response.setMessage("Daftar semua warga dalam organisasi berhasil ditemukan.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/warga/{wargaId}")
    public ResponseEntity<CustomResponse<Page<WargaOrganisasiModel>>> getAllOrganisasiByWargaWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size, @PathVariable Long wargaId) {
        Pageable pageable = PageRequest.of(page, size);
        CustomResponse<Page<WargaOrganisasiModel>> response = new CustomResponse<>();
        Page<WargaOrganisasiModel> organisasiList = wargaOrganisasiService.getAllOrganisasiByWarga(pageable, wargaId);
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(organisasiList);
        response.setMessage("Daftar semua organisasi yang diikuti oleh warga berhasil ditemukan.");
        return ResponseEntity.ok(response);
    }
}
