package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.RWImpl;
import com.e_kampoeng.model.RWModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rw")
@CrossOrigin(origins = "http://localhost:3000")
public class RWController {

    public static final Logger logger = LoggerFactory.getLogger(RWController.class);

    @Autowired
    RWImpl rwImpl;

    @GetMapping
    public CommonResponse<Page<RWModel>> getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(rwImpl.getAll(pageable));
    }

    @GetMapping("/{id}")
    public CommonResponse<RWModel> preview(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rwImpl.getById(id));
    }

    @PostMapping
    public CommonResponse<RWModel> add(@RequestBody RWModel rwModel) {
        return ResponseHelper.ok(rwImpl.add(rwModel));
    }

    @PutMapping("/{id}")
    public  CommonResponse<RWModel> update(@PathVariable("id") Long id, @RequestBody RWModel rwModel) {
        return ResponseHelper.ok(rwImpl.update(id, rwModel));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rwImpl.remove(id));
    }
}
