package com.e_kampoeng.controller;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.request.OrganisasiRequestDTO;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.response.WargaResponseDTO;
import com.e_kampoeng.service.OrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/e-kampoeng/api/organisasi")
@CrossOrigin(origins = "*")
public class OrganisasiController {
  @Autowired
  private OrganisasiService organisasiService;

  @GetMapping
  public ResponseEntity<CustomResponse<Page<OrganisasiModel>>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<OrganisasiModel> organisasiList = organisasiService.getAllOrganisasi(pageable);
    CustomResponse<Page<OrganisasiModel>> response = new CustomResponse<>();
    response.setStatus("success");
    response.setCode(HttpStatus.OK.value());
    response.setData(organisasiList);
    response.setMessage("Data semua organisasi berhasil ditemukan.");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomResponse<OrganisasiModel>> getOrganisasiById(@PathVariable Long id) {
    Optional<OrganisasiModel> organisasiOptional = organisasiService.getOrganisasiById(id);
    CustomResponse<OrganisasiModel> response = new CustomResponse<>();
    if (organisasiOptional.isPresent()) {
      response.setStatus("success");
      response.setCode(HttpStatus.OK.value());
      response.setData(organisasiOptional.get());
      response.setMessage("Organisasi dengan ID " + id + " berhasil ditemukan.");
      return ResponseEntity.ok(response);
    } else {
      response.setStatus("error");
      response.setCode(HttpStatus.NOT_FOUND.value());
      response.setMessage("Organisasi dengan ID " + id + " tidak ditemukan.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  @PostMapping
  public ResponseEntity<CustomResponse<OrganisasiModel>> createOrganisasi(@RequestBody OrganisasiRequestDTO requestDTO) {
    OrganisasiModel organisasiModel = new OrganisasiModel();
    organisasiModel.setNama_organisasi(requestDTO.getNama_organisasi());

    OrganisasiModel savedOrganisasi = organisasiService.createOrganisasi(organisasiModel);

    CustomResponse<OrganisasiModel> response = new CustomResponse<>();
    response.setStatus("success");
    response.setCode(HttpStatus.CREATED.value());
    response.setData(savedOrganisasi);
    response.setMessage("Organisasi berhasil dibuat.");

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomResponse<OrganisasiModel>> updateWarga(@PathVariable Long id, @RequestBody OrganisasiModel organisasiModel) {
    OrganisasiModel responseDTO = organisasiService.updateOrganisasi(id, organisasiModel);
    CustomResponse<OrganisasiModel> response = new CustomResponse<>();
    response.setStatus("success");
    response.setCode(HttpStatus.OK.value());
    response.setData(responseDTO);
    response.setMessage("Organisasi Berhasil Di Update");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteOrganisasi(@PathVariable Long id) {
    organisasiService.deleteOrganisasi(id);
    CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
    response.setStatus("success");
    response.setCode(HttpStatus.OK.value());
    response.setMessage("Organisasi dengan ID " + id + " berhasil dihapus.");
    return ResponseEntity.ok(response);
  }
}
