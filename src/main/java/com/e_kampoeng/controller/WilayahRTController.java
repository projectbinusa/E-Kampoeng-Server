package com.e_kampoeng.controller;

import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.service.WilayahRTService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kampoeng/api/wilayah-rt")
@CrossOrigin(origins = "*")
public class WilayahRTController {
   @Autowired
   private WilayahRTService wilayahRTService;

   @GetMapping
   public List<WilayahRTModel> getAllWilayahRT() {
      return wilayahRTService.getAllWilayahRT();
   }

   @PostMapping
   public WilayahRTModel createWilayahRT(@RequestBody WilayahRTRequestDTO requestDTO) {
      return wilayahRTService.createWilayahRT(requestDTO);
   }

   @GetMapping("/by-rw/{wilayahRWId}")
   public List<WilayahRTModel> getWilayahRTByWilayahRWId(@PathVariable Long wilayahRWId) {
      return wilayahRTService.getWilayahRTByWilayahRWId(wilayahRWId);
   }
}
