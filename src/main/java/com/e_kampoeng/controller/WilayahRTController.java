package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.WilayahRTImpl;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.response.PaginationResponse;
import com.e_kampoeng.service.WilayahRTService;
import com.e_kampoeng.util.Pagination;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wilayah-rt")
@CrossOrigin(origins = "http://localhost:3000")
public class WilayahRTController {

    public static final Logger logger = LoggerFactory.getLogger(WilayahRTController.class);

    @Autowired
    WilayahRTImpl wilayahRT;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping()
    public CommonResponse<WilayahRTModel> create(@RequestBody WilayahRTModel wilayahRTModel) {
        return ResponseHelper.ok(wilayahRT.add(modelMapper.map(wilayahRTModel, WilayahRTModel.class)));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<WilayahRTModel> update(@PathVariable("id") Long id, @RequestBody WilayahRTModel wilayahRTModel) {
        return ResponseHelper.ok(wilayahRT.putData(id, modelMapper.map(wilayahRTModel, WilayahRTModel.class)));
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<WilayahRTModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRT.getById(id));
    }


    @GetMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaginationResponse<List<WilayahRTModel>> getAll(
            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
            @RequestParam(required = false) String search
    ) {

        Page<WilayahRTModel> wilayahRTModels;

        if (search != null && !search.isEmpty()) {
            wilayahRTModels = wilayahRT.getAll(page, limit, search, sort);
        } else {
            wilayahRTModels = wilayahRT.getAll(page, limit, null, sort);
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

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<?> remove(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilayahRT.delete(id));
    }
}
