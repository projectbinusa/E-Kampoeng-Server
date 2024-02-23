package com.e_kampoeng.controller;

import com.e_kampoeng.model.WargaOrganisasiModel;
import com.e_kampoeng.request.WargaOrganisasiRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WargaOrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kampoeng/api/organisasi-warga")
@CrossOrigin(origins = "*")
public class WargaOrganisasiController {
  @Autowired
  private WargaOrganisasiService wargaOrganisasiService;

  @PostMapping("/warga_organisasi")
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

  @DeleteMapping("/warga_organisasi/{id}")
  public ResponseEntity<CustomResponse<Void>> removeWargaFromOrganisasi(@PathVariable Long id) {
    CustomResponse<Void> response = new CustomResponse<>();
    try {
      wargaOrganisasiService.removeWargaFromOrganisasi(id);
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

  @GetMapping("/warga_organisasi/organisasi/{organisasiId}")
  public ResponseEntity<CustomResponse<List<WargaOrganisasiModel>>> getAllWargaInOrganisasi(@PathVariable Long organisasiId) {
    CustomResponse<List<WargaOrganisasiModel>> response = new CustomResponse<>();
    List<WargaOrganisasiModel> wargaOrganisasiList = wargaOrganisasiService.getAllWargaInOrganisasi(organisasiId);
    response.setStatus("success");
    response.setCode(HttpStatus.OK.value());
    response.setData(wargaOrganisasiList);
    response.setMessage("Daftar semua warga dalam organisasi berhasil ditemukan.");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/warga_organisasi/warga/{wargaId}")
  public ResponseEntity<CustomResponse<List<WargaOrganisasiModel>>> getAllOrganisasiByWarga(@PathVariable Long wargaId) {
    CustomResponse<List<WargaOrganisasiModel>> response = new CustomResponse<>();
    List<WargaOrganisasiModel> organisasiList = wargaOrganisasiService.getAllOrganisasiByWarga(wargaId);
    response.setStatus("success");
    response.setCode(HttpStatus.OK.value());
    response.setData(organisasiList);
    response.setMessage("Daftar semua organisasi yang diikuti oleh warga berhasil ditemukan.");
    return ResponseEntity.ok(response);
  }
  }
