package com.data.penduduk.controller;

import com.data.penduduk.model.ESoeratModel;
import com.data.penduduk.model.KkModel;
import com.data.penduduk.response.CustomResponse;
import com.data.penduduk.response.ResponseHelper;
import com.data.penduduk.service.KkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/kk")
@CrossOrigin(origins = "http://localhost:3000")
public class KkControler {

    @Autowired
    private KkService kkService;

    @PostMapping
    public CustomResponse<KkModel> add(@RequestBody KkModel kkModel) {
        return ResponseHelper.ok(kkService.add(kkModel));
    }
    @PutMapping("/{id}")
    public CustomResponse<KkModel> edit(@RequestBody KkModel kkModel , @PathVariable("id") Long id) {
        return ResponseHelper.ok(kkService.edit(kkModel, id));
    }
    @GetMapping("/{id}")
    public CustomResponse<KkModel> get(@PathVariable("id") Long id) {
        return ResponseHelper.ok(kkService.get(id));
    }
    @GetMapping
    public CustomResponse<List<KkModel>> getAll() {
        return ResponseHelper.ok(kkService.getAll());
    }
    @DeleteMapping("/{id}")

    public CustomResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(kkService.delete(id));
    }
}
