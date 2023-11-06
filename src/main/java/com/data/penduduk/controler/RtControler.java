package com.data.penduduk.controler;

import com.data.penduduk.model.RtModel;
import com.data.penduduk.response.CustomResponse;
import com.data.penduduk.response.PaginationResponse;
import com.data.penduduk.response.ResponseHelper;
import com.data.penduduk.service.RtService;
import com.data.penduduk.util.Pagination;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rt")
@CrossOrigin(origins = "http://localhost:3000")
public class RtControler {

    @Autowired
    RtService rtService;

    @PostMapping
    public CustomResponse<RtModel> add(@RequestBody RtModel rtModel) {
        return ResponseHelper.ok(rtService.add(rtModel));
    }
    @PutMapping("/{id}")
    public CustomResponse<RtModel> edit(@RequestBody RtModel rtModel , @PathVariable("id") Long id) {
        return ResponseHelper.ok(rtService.edit(rtModel, id));
    }
    @GetMapping("/{id}")
    public CustomResponse<RtModel> getId(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rtService.getId(id));
    }
    @GetMapping
    public PaginationResponse<List<RtModel>> getAll(
            HttpServletRequest request,
            @RequestParam(defaultValue = Pagination.page, required = false) Long page,
            @RequestParam(defaultValue = Pagination.limit, required = false) Long limit,
            @RequestParam(defaultValue = Pagination.sort, required = false) String sort,
            @RequestParam(required = false) String search) {
        Page<RtModel> rtModelPage;

        if (search != null && !search.isEmpty()) {
            rtModelPage = rtService.getAll(page, limit, sort, search);
        } else {
            rtModelPage = rtService.getAll(page, limit, sort, null);
        }

        List<RtModel> rtModels = rtModelPage.getContent();
        long totalItems = rtModelPage.getTotalElements();
        int totalPages = rtModelPage.getTotalPages();

        Map<String, Integer> pagination = new HashMap<>();
        pagination.put("total", (int) totalItems);
        pagination.put("page", Math.toIntExact(page));
        pagination.put("total_page", totalPages);

        return ResponseHelper.okWithPagination(rtModels, pagination);
    }
    @DeleteMapping("/{id}")

    public CustomResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(rtService.delete(id));
    }
}
