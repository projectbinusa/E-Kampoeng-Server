package com.e_kampoeng.controller;

import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.response.*;
import com.e_kampoeng.service.WilayahRWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kampoeng/api/wilayah-rw")
@CrossOrigin(origins = "*")
public class WilayahRWController {

    @Autowired
    private WilayahRWService wilayahRWService;

//    @GetMapping("/{rwId}/warga")
//    public ResponseEntity<CustomResponse<List<WargaResponseDTO>>> getWargaByRW(@PathVariable Long rwId) {
//        List<WargaResponseDTO> wargaList = wilayahRWService.getWargaByRW(rwId);
//        CustomResponse<List<WargaResponseDTO>> response = new CustomResponse<>();
//        response.setStatus("success");
//        response.setCode(HttpStatus.OK.value());
//        response.setData(wargaList);
//        response.setMessage("Warga retrieved successfully for RW ID: " + rwId);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<CustomResponse<WilayahRWResponseDTO>> createWilayahRW(@RequestBody WilayahRWRequestDTO requestDTO) {
        WilayahRWResponseDTO responseDTO = wilayahRWService.createWilayahRW(requestDTO);
        CustomResponse<WilayahRWResponseDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setData(responseDTO);
        response.setMessage("Wilayah RW created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRWResponseDTO>> updateWilayahRW(@PathVariable Long id, @RequestBody WilayahRWRequestDTO requestDTO) {
        WilayahRWResponseDTO responseDTO = wilayahRWService.updateWilayahRW(id, requestDTO);
        CustomResponse<WilayahRWResponseDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(responseDTO);
        response.setMessage("Wilayah RW updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWilayahRW(@PathVariable Long id) {
        wilayahRWService.deleteWilayahRW(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRWResponseDTO>> getWilayahRWById(@PathVariable Long id) {
        WilayahRWResponseDTO responseDTO = wilayahRWService.getWilayahRWById(id);
        CustomResponse<WilayahRWResponseDTO> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(responseDTO);
        response.setMessage("Wilayah RW retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{rwId}/rts")
    public ResponseEntity<CustomResponse<List<WilayahRTWithRwDTO>>> getRTsByRW(@PathVariable Long rwId) {
        List<WilayahRTWithRwDTO> rtResponseDTOs = wilayahRWService.getRTsByRW(rwId);
        CustomResponse<List<WilayahRTWithRwDTO>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(rtResponseDTOs);
        response.setMessage("Wilayah RTs retrieved successfully by RW id: " + rwId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}