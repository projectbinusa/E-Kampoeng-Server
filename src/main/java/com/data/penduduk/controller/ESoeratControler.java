package com.data.penduduk.controller;

import com.data.penduduk.model.ESoeratModel;
import com.data.penduduk.response.CustomResponse;
import com.data.penduduk.response.ResponseHelper;
import com.data.penduduk.service.ESoeratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soerat")
@CrossOrigin(origins = "http://localhost:3000")
public class ESoeratControler {

    @Autowired
    private ESoeratService eSoeratService;

    @PostMapping(path = "/add")
    public CustomResponse<ESoeratModel> add(@RequestBody ESoeratModel eSoeratModel) {
        return ResponseHelper.ok(eSoeratService.add(eSoeratModel));
    }
    @PutMapping("/{id}")
    public CustomResponse<ESoeratModel> edit(@RequestBody ESoeratModel eSoeratModel , @PathVariable("id") Long id) {
        return ResponseHelper.ok(eSoeratService.edit(eSoeratModel, id));
    }
    @GetMapping("/{id}")
    public CustomResponse<ESoeratModel> get(@PathVariable("id") Long id) {
        return ResponseHelper.ok(eSoeratService.get(id));
    }
    @GetMapping
    public CustomResponse<List<ESoeratModel>> getAll() {
        return ResponseHelper.ok(eSoeratService.getAll());
    }
    @DeleteMapping("/{id}")

    public CustomResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(eSoeratService.delete(id));
    }
}
