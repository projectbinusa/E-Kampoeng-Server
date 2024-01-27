package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.response.PaginationResponse;
import com.e_kampoeng.service.ESoeratService;
import com.e_kampoeng.util.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private ESoeratService eSoeratService;

    @Autowired
    ModelMapper modelMapper;

//    @GetMapping(path = "/getAll")
//    public PaginationResponse<List<ESoeratModel>> getAllSoerat(
//            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
//            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
//            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
//            @RequestParam(required = false) String search
//    ) {
//
//        Page<ESoeratModel> eSoeratModels;
//
//        if (search != null && !search.isEmpty()) {
//            eSoeratModels = eSoeratService.getAllSoerat(page, limit, search, sort);
//        } else {
//            eSoeratModels = eSoeratService.getAllSoerat(page, limit, null, sort);
//        }
//
//        List<ESoeratModel> channels = eSoeratModels.getContent();
//        long totalItems = eSoeratModels.getTotalElements();
//        int totalPages = eSoeratModels.getTotalPages();
//
//        Map<String, Long> pagination = new HashMap<>();
//        pagination.put("total", totalItems);
//        pagination.put("page", page);
//        pagination.put("total_page", (long) totalPages);
//        return ResponseHelper.okWithPagination(channels, pagination);
//    }

    @GetMapping("/{id}") //untuk melihat sesuai id
    public CommonResponse<ESoeratModel> getIdSoerat(@PathVariable("id")Long id) {
        return ResponseHelper.ok(eSoeratService.getIdSoerat(id)) ;
    }

    @PostMapping
    public CommonResponse<ESoeratModel> addSoerat(ESoeratModel eSoeratModel ){
        return ResponseHelper.ok(eSoeratService.addSoerat(modelMapper.map(eSoeratModel,ESoeratModel.class)));
    }


    @PutMapping("/{id}") // untuk mengedit data sesuai id
    public CommonResponse<ESoeratModel> editSoerat(@PathVariable("id") Long id, @RequestBody ESoeratModel eSoeratModel) {
        return ResponseHelper.ok(eSoeratService.editSoerat(id, eSoeratModel));
    }

    @DeleteMapping("/{id}") // untuk menghapus data sesuai id
    public CommonResponse <?> deleteSoerat(@PathVariable("id") Long id) {
        return ResponseHelper.ok(eSoeratService.deleteSoerat(id));}

//    @GetMapping("/all-soerat")
//    public CommonResponse<List<ESoeratModel>> allSoerat() {
//        return ResponseHelper.ok(eSoeratService.allSoerat());
//    }
}
