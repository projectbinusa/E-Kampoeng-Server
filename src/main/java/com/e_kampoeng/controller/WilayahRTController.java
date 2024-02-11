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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WilayahRTController {

    @Autowired
     WilayahRTService wilayahRTService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(path="/wilayah-rt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<WilayahRTModel> Post(@RequestBody WilayahRTModel wilayahRTModel) {
        return ResponseHelper.ok(wilayahRTService.add(modelMapper.map(wilayahRTModel, WilayahRTModel.class)));
    }

    @PutMapping(path = "/wilayah-rt/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<WilayahRTModel> Put(@PathVariable("id") Long id, @RequestBody WilayahRTModel wilayahRTModel) {
        return ResponseHelper.ok(wilayahRTService.putData(id, modelMapper.map(wilayahRTModel, WilayahRTModel.class)));
    }

    @GetMapping(path = "/wilayah-rt/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<WilayahRTModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRTService.getById(id));
    }


    @GetMapping(path = "/wilayah-rt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaginationResponse<List<WilayahRTModel>> getAll(
            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
            @RequestParam(required = false) String search
    ) {

        Page<WilayahRTModel> wilayahRTModels;

        if (search != null && !search.isEmpty()) {
            wilayahRTModels = wilayahRTService.getAll(page, limit, search, sort);
        } else {
            wilayahRTModels = wilayahRTService.getAll(page, limit, null, sort);
        }

        List<WilayahRTModel> channels = wilayahRTModels.getContent();
        long totalItems = wilayahRTModels.getTotalElements();
        int totalPages = wilayahRTModels.getTotalPages();

        Map<String, Long> pagination = new HashMap<>();
        pagination.put("total", totalItems);
        pagination.put("page", page);
        pagination.put("total_page", (long) totalPages);
        return ResponseHelper.okWithPagination(channels, pagination);
    }

    @DeleteMapping(path = "/wilayah-rt/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRTService.delete(id));
    }
}
