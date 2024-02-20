package com.e_kampoeng.controller;


import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.response.WargaByRTResponseDTO;
import com.e_kampoeng.response.WargaResponseDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;
import com.e_kampoeng.service.WilayahRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/wilayah-rt")
@CrossOrigin(origins = "http://localhost:3000")
public class WilayahRTController {

   @Autowired
   private WilayahRTService wilayahRTService;

   @GetMapping
   public ResponseEntity<List<WilayahRTModel>> getAllWilayahRT() {
      List<WilayahRTModel> wilayahRTList = wilayahRTService.getAllWilayahRT();
      return new ResponseEntity<>(wilayahRTList, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<WilayahRTModel> getWilayahRTById(@PathVariable Long id) {
      WilayahRTModel wilayahRT = wilayahRTService.getWilayahRTById(id);
      return new ResponseEntity<>(wilayahRT, HttpStatus.OK);
   }
   @PostMapping
   public ResponseEntity<CustomResponse<WilayahRTResponseDTO>> createWilayahRT(@RequestBody WilayahRTRequestDTO requestDTO) {
      WilayahRTResponseDTO responseDTO = wilayahRTService.createWilayahRT(requestDTO);
      CustomResponse<WilayahRTResponseDTO> response = new CustomResponse<>();
      response.setStatus("success");
      response.setCode(HttpStatus.CREATED.value());
      response.setData(responseDTO);
      response.setMessage("Wilayah RT created successfully");
      return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

   @PutMapping("/{id}")
   public ResponseEntity<CustomResponse<WilayahRTResponseDTO>> updateWilayahRT(@PathVariable Long id, @RequestBody WilayahRTRequestDTO requestDTO) {
      WilayahRTResponseDTO responseDTO = wilayahRTService.updateWilayahRT(id, requestDTO);
      CustomResponse<WilayahRTResponseDTO> response = new CustomResponse<>();
      response.setStatus("success");
      response.setCode(HttpStatus.OK.value());
      response.setData(responseDTO);
      response.setMessage("Wilayah RT updated successfully");
      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteWilayahRT(@PathVariable Long id) {
      wilayahRTService.deleteWilayahRT(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @GetMapping("/{id}/warga")
   public ResponseEntity<CustomResponse<List<WargaByRTResponseDTO>>> getWargaByRT(@PathVariable Long id) {
      List<WargaByRTResponseDTO> responseDTOs = wilayahRTService.getWargaByRT(id);
      CustomResponse<List<WargaByRTResponseDTO>> response = new CustomResponse<>();
      response.setStatus("success");
      response.setCode(HttpStatus.OK.value());
      response.setData(responseDTOs);
      response.setMessage("Warga retrieved successfully by WilayahRT id: " + id);
      return new ResponseEntity<>(response, HttpStatus.OK);



   }

}
