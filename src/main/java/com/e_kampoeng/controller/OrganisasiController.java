package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.OrganisasiImpl;
import com.e_kampoeng.model.OrganisasiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organisasi")
@CrossOrigin("http://localhost:3000")
public class OrganisasiController {

    @Autowired
    private OrganisasiImpl organisasiImpl;

    @GetMapping
    public CommonResponse<Page<OrganisasiModel>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(organisasiImpl.getAll(pageable));
    }

    @GetMapping("/{id}")
    public CommonResponse<OrganisasiModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(organisasiImpl.getById(id));
    }

    @PostMapping
    public CommonResponse<OrganisasiModel> create(@RequestBody OrganisasiModel organisasiModel) {
        return ResponseHelper.ok(organisasiImpl.post(organisasiModel));
    }

    @PutMapping("/{id}")
    public CommonResponse<OrganisasiModel> update(@PathVariable("id") Long id, @RequestBody OrganisasiModel organisasiModel) {
        return ResponseHelper.ok(organisasiImpl.put(id, organisasiModel));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(organisasiImpl.del(id));
    }
}
