package com.e_kampoeng.controller;


import com.e_kampoeng.dto.WargaDTO;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.WargaImpl;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/warga")
@CrossOrigin(origins = "http://localhost:3000")
public class WargaController {

    public static final Logger logger = LoggerFactory.getLogger(WargaController.class);

    @Autowired
    private WargaImpl wargaImpl;

    @Autowired
    private WargaRepository wargaDao;

    @GetMapping // mengambil semua data Warga dengan pagination
    public CommonResponse<Page<WargaModel>> getAllWithPagination(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(wargaImpl.getAll(pageable));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // mengambil data Warga berdasarkan id
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        logger.info("Fetching data a with id {}", id);

        WargaModel wargaData = wargaImpl.getById(id);

        if (wargaData == null) {
            logger.error("data with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("data with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(wargaData, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json") // menambahkan data Warga
    public CommonResponse<WargaModel> create(@RequestBody WargaDTO warga) throws SQLException, ClassNotFoundException {
        logger.info("Creating Data : {}", warga);

        return ResponseHelper.ok(wargaImpl.create(warga));
    }

    @PutMapping("/{id}") // mengupdate data Warga berdasarkan id
    public CommonResponse<WargaModel> update(Long id, @RequestBody WargaModel wm) {
        return ResponseHelper.ok(wargaImpl.update(id, wm));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // menghapus data Warga berdasarkan id
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching & Deleting data with id {}", id);

        wargaImpl.delete(id);
        return new ResponseEntity<WargaModel>(HttpStatus.NO_CONTENT);
    }
}