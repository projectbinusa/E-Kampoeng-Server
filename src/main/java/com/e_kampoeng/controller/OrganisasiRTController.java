package com.e_kampoeng.controller;

import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.request.OrganisasiRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.OrganisasiRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/e-kampoeng/api/organisasi-rt")
@CrossOrigin(origins = "*")
public class OrganisasiRTController {

    @Autowired
    private OrganisasiRTService organisasiRTService;

    @GetMapping("/all")
    public ResponseEntity<Page<OrganisasiModel>> getAllOrganisasiRTWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                                  @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        Pageable pageable = PageRequest.of(page, size);
        Page<OrganisasiModel> organisasiRTList = organisasiRTService.getAllOrganisasiRTByCreator(creatorEmail, pageable);
        return ResponseEntity.ok(organisasiRTList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganisasiModel> getOrganisasiRTById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        Optional<OrganisasiModel> organisasiRTOptional = organisasiRTService.getOrganisasiRTByIdAndCreator(id, creatorEmail);
        return organisasiRTOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<OrganisasiModel> createOrganisasiRT(@RequestBody OrganisasiRequestDTO requestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        OrganisasiModel organisasiModel = new OrganisasiModel();
        organisasiModel.setNama_organisasi(requestDTO.getNama_organisasi());
        OrganisasiModel savedOrganisasiRT = organisasiRTService.createOrganisasiRT(organisasiModel, creatorEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganisasiRT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<OrganisasiModel>> updateOrganisasi(@PathVariable Long id, @RequestBody OrganisasiRequestDTO requestDTO) {
        OrganisasiModel updatedOrganisasi = organisasiRTService.updateOrganisasiRT(id, requestDTO);
        CustomResponse<OrganisasiModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(updatedOrganisasi);
        response.setMessage("Organisasi berhasil diperbarui.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrganisasiRT(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        organisasiRTService.deleteOrganisasiRT(id);
        return ResponseEntity.ok().build();
    }
}
