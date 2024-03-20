package com.e_kampoeng.controller;

import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.request.WilayahRWRequestDTO;
import com.e_kampoeng.service.WilayahRWService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kampoeng/api/wilayah-rw")
@CrossOrigin(origins = "*")
public class WilayahRWController {
    @Autowired
    private WilayahRWService wilayahRWService;

    @GetMapping
    public List<WilayahRWModel> getAllWilayahRW() {
        return wilayahRWService.getAllWilayahRW();
    }

    @PostMapping
    public WilayahRWModel createWilayahRW(@RequestBody WilayahRWRequestDTO requestDTO) {
        return wilayahRWService.createWilayahRW(requestDTO);
    }

}
