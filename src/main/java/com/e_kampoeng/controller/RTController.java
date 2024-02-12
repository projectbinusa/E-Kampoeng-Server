package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.RTImpl;
import com.e_kampoeng.model.RTModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rt")
@CrossOrigin("http://localhost:3000")
public class RTController {

    @Autowired
    private RTImpl rtImpl;

    @GetMapping // mengambil semua data RT dengan pagination
    public CommonResponse<Page<RTModel>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(rtImpl.getAll(pageable));
    }

    @GetMapping("/{id}")
    public CommonResponse<RTModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rtImpl.getById(id));
    }

    @PostMapping
    public CommonResponse<RTModel> create(@RequestBody RTModel rtModel) {
        return ResponseHelper.ok(rtImpl.post(rtModel));
    }

    @PutMapping("/{id}")
    public CommonResponse<RTModel> update(@RequestBody RTModel rtModel, @PathVariable("id") Long id) {
        return ResponseHelper.ok(rtImpl.put(rtModel, id));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rtImpl.del(id));
    }
}
