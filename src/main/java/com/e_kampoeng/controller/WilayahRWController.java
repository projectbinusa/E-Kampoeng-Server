package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.WilRWImpl;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.response.PaginationResponse;
import com.e_kampoeng.util.Pagination;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wilayah-rw")
@CrossOrigin(origins = "http://localhost:3000")
public class WilayahRWController {

    public static final Logger logger = LoggerFactory.getLogger(WilayahRWController.class);

    @Autowired
    WilRWImpl wilRW;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping // mengambil semua data Wilayah RW dengan pagination
    public PaginationResponse<List<WilayahRWModel>> getAllWithPagination(
            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
            @RequestParam(required = false) String search
    ) {

        Page<WilayahRWModel> wilayahRWModels;

        if (search != null && !search.isEmpty()) {
            wilayahRWModels = wilRW.getAllWilayahRW(page, limit, search, sort);
        } else {
            wilayahRWModels = wilRW.getAllWilayahRW(page, limit, null, sort);
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

    @GetMapping(path = "/{id}") // mengambil data Wilayah RW bedasarkan id
    public CommonResponse<WilayahRWModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilRW.getByIdWilayahRW(id));
    }

    @PostMapping // menambahkan data Wilayah RW
    public CommonResponse<WilayahRWModel> create(@RequestBody WilayahRWModel wilayahRWModel) {
        return ResponseHelper.ok(wilRW.addWilayahRW(modelMapper.map(wilayahRWModel, WilayahRWModel.class)));
    }

    @PutMapping(path = "/{id}") // mengupdate data Wilayah RW berdasarkan id
    public CommonResponse<WilayahRWModel> update(@PathVariable("id") Long id, @RequestBody WilayahRWModel wilayahRWModel) {
        return ResponseHelper.ok(wilRW.putDataWilayahRW(id, modelMapper.map(wilayahRWModel, WilayahRWModel.class)));
    }

    @DeleteMapping(path = "/{id}") // menghapus data Wilayah RW berdasarkan id
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wilRW.deleteWilayahRW(id));
    }
}
