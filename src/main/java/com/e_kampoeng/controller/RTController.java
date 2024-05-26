package com.e_kampoeng.controller;

import com.e_kampoeng.dto.WargaDTO;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.request.ChangePasswordRequestDTO;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WargaService;
import com.google.api.pathtemplate.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RTController {
    @Autowired
    WargaService wargaService;

    @PostMapping("/register/rt")
    public ResponseEntity<CustomResponse<WargaModel>> registerRt(@RequestBody WargaRequestDTO wargaDTO) {
        try {
            WargaModel warga = wargaService.saveWargaRoleRt(wargaDTO);
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("RT head added successfully");
            response.setData(warga);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (NotFoundException e) {
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (RuntimeException e) {
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            CustomResponse<WargaModel> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to add RT head: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/wilayah-rt/{rtId}/lepas-jabatan")
    public ResponseEntity<?> releaseHeadRT(@PathVariable("rtId") Long rtId) {
        try {
            wargaService.releaseHeadRT(rtId);
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Head RT released successfully");
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to release head RT: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/wilayah-rt/{rtId}/set-head")
    public ResponseEntity<?> setHeadRT(@PathVariable("rtId") Long rtId, @RequestParam("wargaId") Long wargaId) {
        try {
            wargaService.setHeadRT(wargaId, rtId);
            CustomResponse<Void> response = new CustomResponse<>();
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Head RT set successfully");
            return ResponseEntity.ok(response);
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
            response.setMessage("Failed to set head RT: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/rt/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO requestDTO) {
        try {
            WargaModel updatedWarga = wargaService.changePasswordRt(requestDTO);
            return ResponseEntity.ok(updatedWarga);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to change password: " + e.getMessage());
        }
    }
}
