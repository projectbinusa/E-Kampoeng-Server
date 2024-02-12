package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.ESoeratImpl;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.response.PaginationResponse;
import com.e_kampoeng.service.ESoeratService;
import com.e_kampoeng.util.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/e_soerat")
@CrossOrigin(origins = "http://localhost:3000")
public class ESoeratController {

    @Autowired
    private ESoeratImpl eSoerat;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping // mengambil semua data E-Soerat dengan pagination
    public CommonResponse<Page<ESoeratModel>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(eSoerat.getAllSoerat(pageable));
    }

    @GetMapping("/{id}") // mengambil data E-Soerat berdasarkan id
    public CommonResponse<ESoeratModel> getById(@PathVariable("id")Long id) {
        return ResponseHelper.ok(eSoerat.getIdSoerat(id)) ;
    }

    @PostMapping // menambahkan data E-Soerat
    public CommonResponse<ESoeratModel> create(ESoeratModel eSoeratModel){
        return ResponseHelper.ok(eSoerat.addSoerat(modelMapper.map(eSoeratModel, ESoeratModel.class)));
    }


    @PutMapping("/{id}") // mengedit data E-Soerat berdasarkan id
    public CommonResponse<ESoeratModel> update(@PathVariable("id") Long id, @RequestBody ESoeratModel eSoeratModel) {
        return ResponseHelper.ok(eSoerat.editSoerat(id, eSoeratModel));
    }

    @DeleteMapping("/{id}") // menghapus data E-Soerat berdasarkan id
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(eSoerat.deleteSoerat(id));}

}
