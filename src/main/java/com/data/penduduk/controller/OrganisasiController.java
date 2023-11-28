package com.data.penduduk.controller;

import com.data.penduduk.model.Organisasi;
import com.data.penduduk.response.CustomResponse;
import com.data.penduduk.response.ResponseHelper;
import com.data.penduduk.service.OrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/organisasi")
public class OrganisasiController {

    @Autowired
    private OrganisasiService organisasiService;

    @PostMapping
    public CustomResponse<Organisasi> add(@RequestBody Organisasi organisasi) {
        return ResponseHelper.ok(organisasiService.add(organisasi));
    }

    @PutMapping("/{id}")
    public CustomResponse<Organisasi> edit(@RequestBody Organisasi organisasi, @PathVariable("id") Long id) {
        return ResponseHelper.ok(organisasiService.get(id));
    }

    @GetMapping("/{id}")
    public CustomResponse<Organisasi> get(@PathVariable("id") Long id) {
        return ResponseHelper.ok(organisasiService.get(id));
    }

    @GetMapping
    public CustomResponse<List<Organisasi>> getAll() {
        return ResponseHelper.ok(organisasiService.getAll());
    }

    @DeleteMapping("/{id}")
    public CustomResponse<?> delete(@PathVariable("id")Long id) {
        return ResponseHelper.ok(organisasiService.delete(id));
    }
}
