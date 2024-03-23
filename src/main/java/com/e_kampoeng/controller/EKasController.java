package com.e_kampoeng.controller;

import com.e_kampoeng.dto.EKasDTO;
import com.e_kampoeng.dto.TagsDTO;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.model.EKasModel;
import com.e_kampoeng.model.Tags;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.EKasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-kampoeng/api/e-kas")
@CrossOrigin(origins = "*")
public class EKasController {
    @Autowired
    private EKasService eKasService;

    @GetMapping
    public ResponseEntity<CommonResponse<Page<EKasModel>>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        CommonResponse<Page<EKasModel>> response = new CommonResponse<>();
        Pageable pageable = PageRequest.of(page, size);
        try {
            Page<EKasModel> kas = eKasService.findAllWithPagination(pageable);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(kas);
            response.setMessage("E-Kas list with pagination retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve E-Kas list with pagination: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<EKasModel>> findById(@PathVariable("id") Long id) {
        CommonResponse<EKasModel> res = new CommonResponse<>();
        EKasModel kas = eKasService.findById(id);
        if (kas == null) {
            res.setStatus("error");
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setData(null);
            res.setMessage("E-Kas with ID " + id + " Not Found");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        res.setStatus("success");
        res.setCode(HttpStatus.OK.value());
        res.setData(kas);
        res.setMessage("E-Kas with ID " + id + " found successfully.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<EKasModel>> create(@RequestBody EKasDTO eKasDTO) {
        CommonResponse<EKasModel> response = new CommonResponse<>();
        try {
            EKasModel kas = eKasService.create(eKasDTO);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(kas);
            response.setMessage("E-Kas created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create E-Kas : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<EKasModel>> update(@PathVariable("id") Long id, @RequestBody EKasModel eKasModel) {
        CommonResponse<EKasModel> response = new CommonResponse<>();
        try {
            EKasModel kas = eKasService.findById(id);

            if (kas == null) {
                response.setStatus("error");
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setData(null);
                response.setMessage("E-Kas with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Update E-Kas here...

            EKasModel updateKas = eKasService.update(id, eKasModel);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(updateKas);
            response.setMessage("E-Kas updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update E-Kas : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable("id") Long id) {
        CommonResponse<String> response = new CommonResponse<>();
        try {
            eKasService.delete(id);
            response.setStatus("success");
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setData("E-Kas deleted successfully.");
            response.setMessage("E-Kas with id " + id + " deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to delete E-Kas : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
