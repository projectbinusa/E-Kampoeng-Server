package com.e_kampoeng.controller;

import com.e_kampoeng.dto.CategoryBeritaDTO;
import com.e_kampoeng.model.CategoryBerita;
import com.e_kampoeng.service.CategoryBeritaRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/e-kampoeng/api/category-berita-rt")
@CrossOrigin(origins = "*")
public class CategoryBeritaRTController {

    @Autowired
    private CategoryBeritaRTService categoryBeritaRTService;

    @GetMapping("/all")
    public ResponseEntity<Page<CategoryBerita>> getAllCategoryBerita(Pageable pageable) {
        Page<CategoryBerita> categories = categoryBeritaRTService.findAll(pageable);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryBerita> getCategoryBeritaById(@PathVariable Long id) {
        CategoryBerita category = categoryBeritaRTService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryBerita> createCategoryBerita(@RequestBody CategoryBeritaDTO categoryBeritaDTO) {
        CategoryBerita category = categoryBeritaRTService.save(categoryBeritaDTO);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryBerita> updateCategoryBerita(@PathVariable Long id, @RequestBody CategoryBeritaDTO categoryBeritaDTO) {
        CategoryBerita category = categoryBeritaRTService.update(id, categoryBeritaDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategoryBerita(@PathVariable Long id) {
        categoryBeritaRTService.delete(id);
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
