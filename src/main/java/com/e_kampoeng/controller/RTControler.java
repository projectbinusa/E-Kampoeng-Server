package com.e_kampoeng.controller;

import com.e_kampoeng.dto.RTDto;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.response.PaginationResponse;
import com.e_kampoeng.service.RTService;
import com.e_kampoeng.util.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RTControler {

    @Autowired
    RTService rtService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(path="/rt")
    public CommonResponse<RTModel> Post(@RequestBody RTDto rtModel) {
        return ResponseHelper.ok(rtService.add(modelMapper.map(rtModel, RTModel.class)));
    }

    @PutMapping(path = "/rt/{id}")
    public CommonResponse<RTModel> Put(@PathVariable("id") Long id, @RequestBody RTDto rtModel) {
        return ResponseHelper.ok(rtService.putData(id, modelMapper.map(rtModel, RTModel.class)));
    }

    @GetMapping(path = "/rt/{id}")
    public CommonResponse<RTModel> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rtService.getById(id));
    }


    @GetMapping(path = "/rt")
    public PaginationResponse<List<RTModel>> getAll(
            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
            @RequestParam(required = false) String search
    ) {

        Page<RTModel> rtModels;

        if (search != null && !search.isEmpty()) {
            rtModels = rtService.getAll(page, limit, search, sort);
        } else {
            rtModels = rtService.getAll(page, limit, null, sort);
        }

        List<RTModel> channels = rtModels.getContent();
        long totalItems = rtModels.getTotalElements();
        int totalPages = rtModels.getTotalPages();

        Map<String, Long> pagination = new HashMap<>();
        pagination.put("total", totalItems);
        pagination.put("page", page);
        pagination.put("total_page", (long) totalPages);
        return ResponseHelper.okWithPagination(channels, pagination);
    }

    @DeleteMapping(path = "/rt/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rtService.delete(id));
    }
}
