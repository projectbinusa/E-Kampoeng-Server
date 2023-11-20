package com.data.penduduk.controller;

import com.data.penduduk.model.WargaPendatang;
import com.data.penduduk.response.CustomResponse;
import com.data.penduduk.response.ResponseHelper;
import com.data.penduduk.service.WargaPendatangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/warga_pendatang")
public class WargaPendatangController {

    @Autowired
    private WargaPendatangService wargaPendatangService;

    @PostMapping
    public CustomResponse<WargaPendatang> add(@RequestBody WargaPendatang wargaPendatang) {
        return ResponseHelper.ok(wargaPendatangService.add(wargaPendatang));
    }
    @PutMapping("/{id}")
    public CustomResponse<WargaPendatang> edit(@RequestBody WargaPendatang wargaPendatang , @PathVariable("id") Long id) {
        return ResponseHelper.ok(wargaPendatangService.edit(wargaPendatang, id));
    }
    @GetMapping("/{id}")
    public CustomResponse<WargaPendatang> get(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wargaPendatangService.get(id));
    }
    @GetMapping
    public CustomResponse<List<WargaPendatang>> getAll() {
        return ResponseHelper.ok(wargaPendatangService.getAll());
    }
    @DeleteMapping("/{id}")

    public CustomResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(wargaPendatangService.delete(id));
    }
}
