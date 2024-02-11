package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.response.PaginationResponse;
import com.e_kampoeng.service.WilayahRTService;
import com.e_kampoeng.util.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class WilayahRTControler {

    @Autowired
    private WilayahRTService wilayahRTService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(path="/wilayah-rt")
    public CommonResponse<WilayahRTModel> Post(@RequestBody WilayahRTModel rtModel) {
        return ResponseHelper.ok(wilayahRTService.addWilayahRt(modelMapper.map(rtModel, WilayahRTModel.class)));
    }

    @PutMapping(path = "/wilayah-rt/{id}")
    public CommonResponse<WilayahRTModel> Put(@PathVariable("id") Long id, @RequestBody WilayahRTModel rtModel) {
        return ResponseHelper.ok(wilayahRTService.putWilayahRt(id, modelMapper.map(rtModel, WilayahRTModel.class)));
    }

    @GetMapping(path = "/wilayah-rt/{id}")
    public CommonResponse<WilayahRTModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRTService.getByIdWilayahRt(id));
    }


    @GetMapping(path = "/wilayah-rt")
    public PaginationResponse<List<WilayahRTModel>> getAll(
            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
            @RequestParam(required = false) String search
    ) {

        Page<WilayahRTModel> rtModels;

        if (search != null && !search.isEmpty()) {
            rtModels = wilayahRTService.getAllWilayahRt(page, limit, search, sort);
        } else {
            rtModels = wilayahRTService.getAllWilayahRt(page, limit, null, sort);
        }

        List<WilayahRTModel> channels = rtModels.getContent();
        long totalItems = rtModels.getTotalElements();
        int totalPages = rtModels.getTotalPages();

        Map<String, Long> pagination = new HashMap<>();
        pagination.put("total", totalItems);
        pagination.put("page", page);
        pagination.put("total_page", (long) totalPages);
        return ResponseHelper.okWithPagination(channels, pagination);
    }

    @DeleteMapping(path = "/wilayah-rt/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRTService.deleteWilayahRt(id));
    }
}
