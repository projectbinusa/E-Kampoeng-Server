package com.e_kampoeng.controller;

import com.e_kampoeng.dto.TagsDTO;
import com.e_kampoeng.model.Tags;
import com.e_kampoeng.service.TagsRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tagsRT")
public class TagsRTController {

    @Autowired
    private TagsRTService tagsRTService;

    @PostMapping
    public Tags createTag(@RequestBody TagsDTO tagsDTO) {
        return tagsRTService.save(tagsDTO);
    }

    @GetMapping("/{id}")
    public Tags getTagById(@PathVariable Long id) {
        return tagsRTService.findById(id);
    }

    @GetMapping
    public Page<Tags> getAllTags(Pageable pageable) {
        return tagsRTService.findAllWithPagination(pageable);
    }

    @PutMapping("/{id}")
    public Tags updateTag(@PathVariable Long id, @RequestBody TagsDTO tagsDTO) {
        return tagsRTService.update(id, tagsDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTag(@PathVariable Long id) {
        return tagsRTService.delete(id);
    }
}
