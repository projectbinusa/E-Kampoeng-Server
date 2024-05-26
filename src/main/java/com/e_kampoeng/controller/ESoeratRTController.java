package com.e_kampoeng.controller;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.request.ESoeratApprovalRequestDTO;
import com.e_kampoeng.request.ESoeratRequestDTO;
import com.e_kampoeng.request.ESoeratUpdateRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.ESoeratRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/e-soerat-rt")
@CrossOrigin(origins = "*")
public class ESoeratRTController {

    @Autowired
    private ESoeratRTService eSoeratService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<Page<ESoeratModel>>> getAllSuratByWilayahRT(Pageable pageable) {
        Page<ESoeratModel> allSurat = eSoeratService.getAllSuratByWilayahRT(pageable);

        CustomResponse<Page<ESoeratModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(allSurat);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ajukan")
    public ResponseEntity<CustomResponse<ESoeratModel>> ajukanSurat(@RequestBody ESoeratRequestDTO suratRequestDTO) {
        ESoeratModel addedSurat = eSoeratService.ajukanSurat(suratRequestDTO);

        CustomResponse<ESoeratModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Surat successfully submitted");
        response.setData(addedSurat);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse<ESoeratModel>> editSurat(@PathVariable Long id, @RequestBody ESoeratRequestDTO suratRequestDTO) {
        ESoeratModel editedSurat = eSoeratService.editPengajuanSurat(id, suratRequestDTO);

        CustomResponse<ESoeratModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Surat successfully edited");
        response.setData(editedSurat);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/batalkan/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> batalkanSurat(@PathVariable Long id) {
        eSoeratService.batalkanPengajuanSurat(id);

        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Surat successfully cancelled");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ESoeratModel>> getSuratById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        ESoeratModel surat = eSoeratService.getSuratByIdForWarga(id, creatorEmail);

        CustomResponse<ESoeratModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(surat);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<CustomResponse<ESoeratModel>> approveSurat(@PathVariable Long id, @RequestBody ESoeratApprovalRequestDTO suratUpdateRequestDTO) {
        ESoeratModel approvedSurat = eSoeratService.approveSurat(id, suratUpdateRequestDTO);

        CustomResponse<ESoeratModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Surat successfully approved");
        response.setData(approvedSurat);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-submissions")
    public ResponseEntity<CustomResponse<Page<ESoeratModel>>> getMySubmissions(Pageable pageable) {
        Page<ESoeratModel> mySubmissions = eSoeratService.getAllSuratByCreator(pageable);

        CustomResponse<Page<ESoeratModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(mySubmissions);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/approved")
    public ResponseEntity<CustomResponse<Page<ESoeratModel>>> getApprovedSurat(Pageable pageable) {
        Page<ESoeratModel> approvedSurat = eSoeratService.getApprovedSuratByWilayahRT(pageable);

        CustomResponse<Page<ESoeratModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Approved surat retrieved successfully");
        response.setData(approvedSurat);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/unapproved")
    public ResponseEntity<CustomResponse<Page<ESoeratModel>>> getUnapprovedSurat(Pageable pageable) {
        Page<ESoeratModel> unapprovedSurat = eSoeratService.getUnapprovedSuratByWilayahRT(pageable);

        CustomResponse<Page<ESoeratModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Unapproved surat retrieved successfully");
        response.setData(unapprovedSurat);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/rejected")
    public ResponseEntity<CustomResponse<Page<ESoeratModel>>> getRejectedSurat(Pageable pageable) {
        Page<ESoeratModel> rejectedSurat = eSoeratService.getRejectedSuratByWilayahRT(pageable);

        CustomResponse<Page<ESoeratModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Rejected surat retrieved successfully");
        response.setData(rejectedSurat);

        return ResponseEntity.ok(response);
    }
}
