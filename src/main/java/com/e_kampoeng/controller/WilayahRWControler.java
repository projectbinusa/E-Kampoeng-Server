package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.response.PaginationResponse;
import com.e_kampoeng.service.WilayahRWService;
import com.e_kampoeng.util.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class WilayahRWControler {

    @Autowired
    WilayahRWService wilayahRWService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(path="/api/wilayah-rw")
    public CommonResponse<WilayahRWModel> Post(@RequestBody WilayahRWModel wilayahRWModel) {
        return ResponseHelper.ok(wilayahRWService.addWilayahRW(modelMapper.map(wilayahRWModel, WilayahRWModel.class)));
    }

    @PutMapping(path = "/api/wilayah-rw/{id}")
    public CommonResponse<WilayahRWModel> Put(@PathVariable("id") Long id, @RequestBody WilayahRWModel wilayahRWModel) {
        return ResponseHelper.ok(wilayahRWService.putDataWilayahRW(id, modelMapper.map(wilayahRWModel, WilayahRWModel.class)));
    }

    @GetMapping(path = "/api/wilayah-rw/{id}")
    public CommonResponse<WilayahRWModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRWService.getByIdWilayahRW(id));
    }


    @GetMapping(path = "/api/wilayah-rw")
    public PaginationResponse<List<WilayahRWModel>> getAllWilayahRW(
            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
            @RequestParam(required = false) String search
    ) {

        Page<WilayahRWModel> wilayahRWModels;

        if (search != null && !search.isEmpty()) {
            wilayahRWModels = wilayahRWService.getAllWilayahRW(page, limit, search, sort);
        } else {
            wilayahRWModels = wilayahRWService.getAllWilayahRW(page, limit, null, sort);
        }

        List<WilayahRWModel> channels = wilayahRWModels.getContent();
        long totalItems = wilayahRWModels.getTotalElements();
        int totalPages = wilayahRWModels.getTotalPages();

        Map<String, Long> pagination = new HashMap<>();
        pagination.put("total", totalItems);
        pagination.put("page", page);
        pagination.put("total_page", (long) totalPages);
        return ResponseHelper.okWithPagination(channels, pagination);
    }

    @DeleteMapping(path = "/api/wilayah-rw/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRWService.deleteWilayahRW(id));
    }
}
