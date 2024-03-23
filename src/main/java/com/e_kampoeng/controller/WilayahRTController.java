package com.e_kampoeng.controller;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WilayahRTService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/wilayah-rt")
@CrossOrigin(origins = "*")
public class WilayahRTController {
    @Autowired
    private WilayahRTService wilayahRTService;

    @GetMapping
    public ResponseEntity<Page<WilayahRTModel>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WilayahRTModel> wilayahRTList = wilayahRTService.getAllWilayahRT(pageable);
        return new ResponseEntity<>(wilayahRTList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRTModel>> getById(@PathVariable("id") Long id) {
        CustomResponse<WilayahRTModel> response = new CustomResponse<>();
        try {
            WilayahRTModel wilRT = wilayahRTService.getWilayahRTById(id);
            if (wilRT == null) {
                response.setStatus("error");
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setData(wilRT);
                response.setMessage("Wilayah RT ID Not Found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                response.setStatus("success");
                response.setCode(HttpStatus.OK.value());
                response.setData(wilRT);
                response.setMessage("Wilayah RT ID Successfully Found.");
                return ResponseEntity.ok(response);
            }
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping
    public WilayahRTModel createWilayahRT(@RequestBody WilayahRTRequestDTO requestDTO) {
        return wilayahRTService.createWilayahRT(requestDTO);
    }

    @GetMapping("/by-rw/{wilayahRWId}")
    public ResponseEntity<Page<WilayahRTModel>> getWilayahRTByWilayahRWId(@PathVariable Long wilayahRWId, @RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WilayahRTModel> wilRTsByRW = wilayahRTService.getWilayahRTByWilayahRWId(wilayahRWId, pageable);
        return new ResponseEntity<>(wilRTsByRW, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRTRequestDTO>> updateWilayahRT(@PathVariable Long id, @RequestBody WilayahRTRequestDTO requestDTO) {
        WilayahRTRequestDTO responseDTO = wilayahRTService.updateWilayahRT(id, requestDTO);
        CustomResponse<WilayahRTRequestDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(responseDTO);
        response.setMessage("Wilayah RT updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteWilayahRT(@PathVariable Long id) {
        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        try {
            Map<String, Boolean> result = wilayahRTService.deleteWilayahRT(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(result);
            response.setMessage("Wilayah RT successfully deleted.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

//    @GetMapping("/{id}/warga")
//    public ResponseEntity<CustomResponse<List<WargaByRTResponseDTO>>> getWargaByRT(@PathVariable Long id) {
//        List<WargaByRTResponseDTO> responseDTOs = wilayahRTService.getWargaByRT(id);
//        CustomResponse<List<WargaByRTResponseDTO>> response = new CustomResponse<>();
//        response.setStatus("success");
//        response.setCode(HttpStatus.OK.value());
//        response.setData(responseDTOs);
//        response.setMessage("Warga retrieved successfully by WilayahRT id: " + id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
