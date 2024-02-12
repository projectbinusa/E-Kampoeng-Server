package com.e_kampoeng.controller;

import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.WargaOrganisasiImpl;
import com.e_kampoeng.model.WargaOrganisasiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warga_organisasi")
@CrossOrigin(origins = "http://localhost:3000")
public class WargaOrganisasiController {

    public static final Logger logger = LoggerFactory.getLogger(WargaOrganisasiController.class);

    @Autowired
    private WargaOrganisasiImpl woi;

    @GetMapping // mengambil semua data Warga Organisasi dengan pagination
    public CommonResponse<Page<WargaOrganisasiModel>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(woi.getAll(pageable));
    }

    @GetMapping("/{id}") // mengambil data Warga Organisasi berdasarkan id
    public CommonResponse<WargaOrganisasiModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(woi.getById(id));
    }

    @PostMapping // menambahkan data Warga Organisasi
    public CommonResponse<WargaOrganisasiModel> create(@RequestBody WargaOrganisasiModel wom) {
        return ResponseHelper.ok(woi.create(wom));
    }

    @PutMapping("/{id}") // mengupdate data Warga Organisasi berdasarkan id
    public CommonResponse<WargaOrganisasiModel> update(@PathVariable("id") Long id, @RequestBody WargaOrganisasiModel wom) {
        return ResponseHelper.ok(woi.update(id, wom));
    }

    @DeleteMapping("/{id}") // menghapus data Warga Organisasi berdasarkan id
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(woi.delete(id));
    }
}
