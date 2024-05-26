package com.e_kampoeng.controller;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.request.ChangePasswordRequestDTO;
import com.e_kampoeng.request.WargaRequestRoleWargaDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WargaService;
import com.google.api.pathtemplate.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/e-kampoeng/api/warga")
@CrossOrigin(origins = "*")
public class WargaController {
    @Autowired
    private WargaService wargaService;

    @PostMapping("/rt/register-warga")
    public ResponseEntity<?> saveWargaRoleWarga(@RequestBody WargaRequestRoleWargaDTO wargaRequestRoleWargaDTO) {
        try {
            // Mendapatkan informasi warga yang sedang login
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName(); // Mendapatkan username warga yang login

            WargaModel warga = wargaService.saveWargaRoleWarga(wargaRequestRoleWargaDTO, email);
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("Warga saved successfully");
            response.setData(warga);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (NotFoundException e) {
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (ValidationException e) {
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to save warga: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/admin/warga-by-role/{role}")
    public ResponseEntity<CustomResponse<List<WargaModel>>> getWargaByRole(@PathVariable String role) {
        try {
            List<WargaModel> wargaList = wargaService.getAllWargaByRole(role);
            CustomResponse<List<WargaModel>> response = new CustomResponse<>();
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Warga with role " + role + " retrieved successfully");
            response.setData(wargaList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            CustomResponse<List<WargaModel>> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to retrieve warga with role " + role + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/export-excel")
    public ResponseEntity<byte[]> exportAllWargaToExcel() {
        try {
            byte[] excelContent = wargaService.exportToExcel();
            return ResponseEntity
                    .ok()
                    .header("Content-Disposition", "attachment; filename=warga_data.xlsx")
                    .body(excelContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/admin/by-rt/{rtId}")
    public ResponseEntity<CustomResponse<Page<WargaModel>>> getWargaByRT(@PathVariable Long rtId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WargaModel> wargaByRT = wargaService.getWargaByRT(rtId, pageable);

        CustomResponse<Page<WargaModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(wargaByRT);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/by-rt/{rtId}/export-excel")
    public ResponseEntity<byte[]> exportWargaByRTToExcel(@PathVariable Long rtId) {
        try {
            byte[] excelContent = wargaService.exportToExcelByRTId(rtId);
            return ResponseEntity
                    .ok()
                    .header("Content-Disposition", "attachment; filename=warga_data_by_rt.xlsx")
                    .body(excelContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/import-excel")
    public ResponseEntity<String> importExcel(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            return ResponseEntity.badRequest().body("File format not supported. Please provide an Excel file with .xlsx extension");
        }

        try {
            ResponseEntity<?> response = wargaService.importFromExcel(file);
            return ResponseEntity.ok().body((String) response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import data: " + e.getMessage());
        }
    }

    @GetMapping("/rt/all")
    public ResponseEntity<?> getAllWargaByRT() {
        try {
            // Mendapatkan informasi kepala RT yang sedang login
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Mendapatkan semua warga sesuai dengan wilayah RT dari kepala RT yang digunakan untuk login
            List<WargaModel> wargaList = wargaService.getAllWargaByRT(username);

            CustomResponse<List<WargaModel>> response = new CustomResponse<>();
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("All warga retrieved successfully");
            response.setData(wargaList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to retrieve warga: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/rt/update/{id}")
    public ResponseEntity<CustomResponse<WargaModel>> updateWarga(@PathVariable Long id, @RequestBody WargaRequestRoleWargaDTO wargaDTO, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String email = userDetails.getUsername();
            WargaModel updatedWarga = wargaService.updateWargaRoleWarga(id, wargaDTO, email);
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Warga updated successfully");
            response.setData(updatedWarga);
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (ValidationException e) {
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to update warga: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/warga/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO requestDTO) {
        try {
            WargaModel updatedWarga = wargaService.changePasswordWarga(requestDTO);
            return ResponseEntity.ok(updatedWarga);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to change password: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWargaById(@PathVariable("id") Long id) {
        WargaModel warga = wargaService.getWargaById(id);
        if (warga != null) {
            return ResponseEntity.ok(warga);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
