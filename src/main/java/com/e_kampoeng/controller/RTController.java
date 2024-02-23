package com.e_kampoeng.controller;

import com.e_kampoeng.dto.RTRequestDTO;
import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.response.RTResponseDTO;
import com.e_kampoeng.service.RTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e-kampoeng/api/rt")
@CrossOrigin(origins = "*")
public class RTController {

    @Autowired
    private RTService rtService;

    @GetMapping("/rt")
    public ResponseEntity<CustomResponse<List<RTModel>>> getAllRT() {
        CustomResponse<List<RTModel>> response = new CustomResponse<>();
        List<RTModel> rtList = rtService.getAllRT();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(rtList);
        response.setMessage("Data semua RT berhasil ditemukan.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rt/{id}")
    public ResponseEntity<CustomResponse<RTModel>> getRTById(@PathVariable Long id) {
        CustomResponse<RTModel> response = new CustomResponse<>();
        Optional<RTModel> rtOptional = rtService.getRTById(id);
        if (rtOptional.isPresent()) {
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(rtOptional.get());
            response.setMessage("RT dengan ID " + id + " berhasil ditemukan.");
            return ResponseEntity.ok(response);
        } else {
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("RT dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/api/rt")
    public ResponseEntity<CustomResponse<RTResponseDTO>> assignRT(@RequestBody RTRequestDTO request) {
        CustomResponse<RTResponseDTO> response = new CustomResponse<>();
        try {
            RTModel rtModel = rtService.assignRT(request.getWargaId(), request.getNomorRT());

            // Membuat AssignRTResponse dengan hanya menyertakan data warga dan nomor RT
            RTResponseDTO responseData = new RTResponseDTO();
            responseData.setId(rtModel.getId());
            responseData.setWarga(rtModel.getWarga());
            responseData.setNomorRT(rtModel.getNomorRT());

            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(responseData);
            response.setMessage("Warga berhasil ditetapkan sebagai RT.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/update/{rtId}")
    public ResponseEntity<CustomResponse<RTResponseDTO>> updateRT(
            @PathVariable Long rtId,
            @RequestParam Long wargaId,
            @RequestParam Long nomorRT) {
        CustomResponse<RTResponseDTO> response = new CustomResponse<>();
        try {
            RTModel rtModel = rtService.updateRT(rtId, wargaId, nomorRT);

            // Membuat AssignRTResponse dengan mengatur data warga dan nomor RT
            RTResponseDTO responseData = new RTResponseDTO();
            responseData.setId(rtModel.getId()); // Atur ID warga
            responseData.setNomorRT(rtModel.getNomorRT());
            responseData.setWarga(rtModel.getWarga());

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

    @DeleteMapping("/rt/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteRT(@PathVariable Long id) {
        CustomResponse<Void> response = new CustomResponse<>();
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
