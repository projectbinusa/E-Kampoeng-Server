package com.data.penduduk.controller;

import com.data.penduduk.helper.ExcelKkHelper;
import com.data.penduduk.service.ExcelKkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ExcelKkController {

    @Autowired
    ExcelKkService excelKkService;

    @PostMapping("/upload-kk/rt-{id}")
    public ResponseEntity<?> uploadFile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelKkHelper.hasExcelFormat(file)) {
            try {
                excelKkService.save(file, id);
                message = "successfully upload!" + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                System.out.println(e);
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
