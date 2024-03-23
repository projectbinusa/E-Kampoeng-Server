package com.e_kampoeng.controller;

import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.WilayahRWService;
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
@RequestMapping("/e-kampoeng/api/wilayah-rw")
@CrossOrigin(origins = "*")
public class WilayahRWController {
    @Autowired
    private WilayahRWService wilayahRWService;

    @GetMapping
    public Page<WilayahRWModel> getAllWilayahRW(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return wilayahRWService.getAllWilayahRW(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRWModel>> getWilayahRWById(@PathVariable Long id) {
        CustomResponse<WilayahRWModel> response = new CustomResponse<>();
        try {
            WilayahRWModel result = wilayahRWService.getWilayahRWById(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(result);
            response.setMessage("Wilayah RW Successfully Found.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public WilayahRWModel createWilayahRW(@RequestBody WilayahRWRequestDTO requestDTO) {
        return wilayahRWService.createWilayahRW(requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<WilayahRWModel>> updateWilayahRW(@PathVariable Long id, @RequestBody WilayahRWRequestDTO requestDTO) {
        WilayahRWModel responseDTO = wilayahRWService.updateWilayahRW(id, requestDTO);
        CustomResponse<WilayahRWModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(responseDTO);
        response.setMessage("Wilayah RW updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteWilayahRW(@PathVariable Long id) {
        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        try {
            Map<String, Boolean> result = wilayahRWService.deleteWilayahRW(id);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(result);
            response.setMessage("Wilayah RW successfully deleted.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


//    @GetMapping("/{rwId}/rt")
//    public ResponseEntity<CustomResponse<List<WilayahRTWithRwDTO>>> getRTsByRW(@PathVariable Long rwId) {
//        List<WilayahRTWithRwDTO> rtResponseDTOs = wilayahRWService.getRTsByRW(rwId);
//        CustomResponse<List<WilayahRTWithRwDTO>> response = new CustomResponse<>();
//        response.setStatus("success");
//        response.setCode(HttpStatus.OK.value());
//        response.setData(rtResponseDTOs);
//        response.setMessage("Wilayah RTs retrieved successfully by RW id: " + rwId);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
