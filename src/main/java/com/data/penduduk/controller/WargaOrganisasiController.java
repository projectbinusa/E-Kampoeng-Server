package com.data.penduduk.controller;

import com.data.penduduk.model.WargaOrganisasi;
import com.data.penduduk.response.CustomResponse;
import com.data.penduduk.response.ResponseHelper;
import com.data.penduduk.service.WargaOrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/warga_organisasi")
public class WargaOrganisasiController {

    @Autowired
    private WargaOrganisasiService wargaOrganisasiService;

    @PostMapping
    public CustomResponse<WargaOrganisasi> add(@RequestBody WargaOrganisasi wargaOrganisasi) {
        return ResponseHelper.ok(wargaOrganisasiService.add(wargaOrganisasi));
    }

    @PutMapping("/{id}")
    public CustomResponse<WargaOrganisasi> edit(@RequestBody WargaOrganisasi wargaOrganisasi, @PathVariable("id") Long id) {
        return ResponseHelper.ok(wargaOrganisasiService.get(id));
    }

    @GetMapping("/{id}")
    public CustomResponse<WargaOrganisasi> get(@PathVariable("id") Long id){
        return ResponseHelper.ok(wargaOrganisasiService.get(id));
    }

    @GetMapping
    public CustomResponse<List<WargaOrganisasi>> getAll() {
        return ResponseHelper.ok(wargaOrganisasiService.getAll());
    }

    @DeleteMapping("/{id}")
    public CustomResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wargaOrganisasiService.delete(id));
    }
}
