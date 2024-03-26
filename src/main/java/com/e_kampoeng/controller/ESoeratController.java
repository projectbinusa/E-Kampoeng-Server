package com.e_kampoeng.controller;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.response.CustomResponse;
import com.e_kampoeng.service.ESoeratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/e-soerat")
@CrossOrigin(origins = "*")
public class ESoeratController {
    @Autowired
    private ESoeratService eSoeratService;

    @GetMapping
    public ResponseEntity<CustomResponse<Page<ESoeratModel>>> getAllSoerat(Pageable pageable) {
        Page<ESoeratModel> allSoerat = eSoeratService.getAllSoerat(pageable);

        CustomResponse<Page<ESoeratModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(allSoerat);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ESoeratModel>> getSoeratById(@PathVariable Long id) {
        ESoeratModel soerat = eSoeratService.getIdSoerat(id);

        CustomResponse<ESoeratModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Data retrieved successfully");
        response.setData(soerat);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<ESoeratModel>> addSoerat(@RequestBody ESoeratModel eSoeratModel) {
        ESoeratModel addedSoerat = eSoeratService.addSoerat(eSoeratModel);

        CustomResponse<ESoeratModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Soerat added successfully");
        response.setData(addedSoerat);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<ESoeratModel>> editSoerat(@PathVariable Long id, @RequestBody ESoeratModel eSoeratModel) {
        ESoeratModel editedSoerat = eSoeratService.editSoerat(id, eSoeratModel);

        CustomResponse<ESoeratModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Soerat edited successfully");
        response.setData(editedSoerat);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Map<String, Boolean>>> deleteSoerat(@PathVariable Long id) {
        Map<String, Boolean> deleteResult = eSoeratService.deleteSoerat(id);

        CustomResponse<Map<String, Boolean>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Soerat deleted successfully");
        response.setData(deleteResult);

        return ResponseEntity.ok(response);
    }
}
