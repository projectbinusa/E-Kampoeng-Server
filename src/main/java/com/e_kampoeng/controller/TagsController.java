package com.e_kampoeng.controller;

import com.e_kampoeng.dto.TagsDTO;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.model.Tags;
import com.e_kampoeng.service.BeritaService;
import com.e_kampoeng.impl.TagsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e-kampoeng/api/tags-berita")
@CrossOrigin(origins = "*")
public class TagsController {
    @Autowired
    private TagsImpl tagsService;

    @PostMapping
    public ResponseEntity<CommonResponse<Tags>> createTags(@RequestBody TagsDTO tags) {
        CommonResponse<Tags> response = new CommonResponse<>();
        try {
            Tags tags1 = tagsService.save(tags);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(tags1);
            response.setMessage("Tags created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create tags : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping
//    public ResponseEntity<CommonResponse<List<Tags>>> listAllTags() {
//        CommonResponse<List<Tags>> response = new CommonResponse<>();
//        try {
//            List<Tags> tags = tagsService.findAll();
//            response.setStatus("success");
//            response.setCode(HttpStatus.OK.value());
//            response.setData(tags);
//            response.setMessage("Tags list retrieved successfully.");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.setStatus("error");
//            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setData(null);
//            response.setMessage("Failed to retrieve tags list: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping
    public ResponseEntity<CommonResponse<Page<Tags>>> findAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        CommonResponse<Page<Tags>> response = new CommonResponse<>();
        Pageable pageable = PageRequest.of(page, size);
        try {
            Page<Tags> tags = tagsService.findAllWithPagination(pageable);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tags);
            response.setMessage("Tags list with pagination retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve tags list with pagination: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Tags>> findById(@PathVariable("id") Long id) {
        CommonResponse<Tags> res = new CommonResponse<>();
        Tags tag = tagsService.findById(id);
        if (tag == null) {
            res.setStatus("error");
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setData(null);
            res.setMessage("Tag with ID " + id + " Not Found");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        res.setStatus("success");
        res.setCode(HttpStatus.OK.value());
        res.setData(tag);
        res.setMessage("Tag with ID " + id + " found successfully.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<Tags>> updateTags(@PathVariable("id") Long id, @RequestBody TagsDTO berita) {
        CommonResponse<Tags> response = new CommonResponse<>();
        try {
            Tags currentTags = tagsService.findById(id);

            if (currentTags == null) {
                response.setStatus("error");
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setData(null);
                response.setMessage("Tags with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Update berita here...

            Tags tags = tagsService.update(id, berita);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(tags);
            response.setMessage("Tags updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update tags : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteTags(@PathVariable("id") Long id) {
        CommonResponse<String> response = new CommonResponse<>();
        try {
            tagsService.delete(id);
            response.setStatus("success");
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setData("Tags deleted successfully.");
            response.setMessage("Tags with id " + id + " deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to delete tags : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
