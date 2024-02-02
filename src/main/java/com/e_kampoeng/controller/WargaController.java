package com.e_kampoeng.controller;


import com.e_kampoeng.dto.WargaDTO;
import com.e_kampoeng.repository.WargaDao;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.impl.WargaImpl;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private WargaDao wargaDao;




//    // ---------------------------------Create a data warga-------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public CommonResponse<WargaModel> createProduct(@RequestBody WargaModel warga) throws SQLException, ClassNotFoundException {
        logger.info("Creating Data : {}", warga);

        return ResponseHelper.ok(wargaImpl.create(warga));
    }
//
//    // ---------------------------------Get All data siswa-------------------------------------------
//    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
//    public CommonResponse<List<WargaModel>> listAll() throws SQLException, ClassNotFoundException {
//
//        List<WargaModel> wargaImpls = wargaImpl.getAll();
//
//        return ResponseHelper.ok(wargaImpls);
//    }

//    @GetMapping
//    public CommonResponse<Page<WargaModel>> getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return ResponseHelper.ok(wargaImpl.getAll(pageable));
//    }

//
//    // ---------------------------------Get Single data siswa-------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getData(@PathVariable("id") Long id) {
        logger.info("Fetching data a with id {}", id);

        WargaModel wargaData = wargaImpl.getById(id);

        if (wargaData == null) {
            logger.error("data with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("data with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(wargaData, HttpStatus.OK);
    }
//
//    // ---------------------------------Update data siswa-------------------------------------------
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> updateData(@PathVariable("id") long id, @RequestBody Warga wargaData) throws SQLException, ClassNotFoundException {
//        logger.info("Updating data with id {}", id);
//
//        WargaModel currentData = wargaImpl.getById(id);
//
//        if (currentData == null) {
//            logger.error("Unable to update. data with id {} not found.", id);
//            return new ResponseEntity<>(new CustomErrorType("Unable to update. data with id " + id + " not found."), HttpStatus.NOT_FOUND);
//        }
//        currentData.orElseThrow().setNama(wargaData.getNama());
//        currentData.orElseThrow().setTanggal_lahir(wargaData.getTanggal_lahir());
//        currentData.orElseThrow().setTempat_lahir(wargaData.getTempat_lahir());
//        currentData.orElseThrow().setAgama(wargaData.getAgama());
//        currentData.orElseThrow().setPendidikan(wargaData.getPendidikan());
//        currentData.orElseThrow().setJenis_kelamin(wargaData.getJenis_kelamin());
//        currentData.orElseThrow().setGolongan_darah(wargaData.getGolongan_darah());
//        currentData.orElseThrow().setPekerjaan(wargaData.getPekerjaan());
//        currentData.orElseThrow().setStatus_dalam_keluarga(wargaData.getStatus_dalam_keluarga());
//        currentData.orElseThrow().setNo_kk(wargaData.getNo_kk());
//        currentData.orElseThrow().setNik(wargaData.getNik());
//        currentData.orElseThrow().setStatus_kependudukan(wargaData.getStatus_kependudukan());
//        currentData.orElseThrow().setStatus_perkawinan(wargaData.getStatus_perkawinan());
//        currentData.orElseThrow().setJenis_asuransi(wargaData.getJenis_asuransi());
//        currentData.orElseThrow().setJenis_kb(wargaData.getJenis_kb());
//        currentData.orElseThrow().setSumber_air(wargaData.getSumber_air());
//
//
//        wargaImpl.update(currentData.get().getId());
//        return new ResponseEntity<>(currentData, HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public CommonResponse<WargaModel> update(Long id, @RequestBody WargaModel wm) {
        return ResponseHelper.ok(wargaImpl.update(id, wm));
    }

//    // ---------------------------------Delete data siswa-------------------------------------------
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteData(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching & Deleting data with id {}", id);

        wargaImpl.delete(id);
        return new ResponseEntity<WargaModel>(HttpStatus.NO_CONTENT);
    }
}