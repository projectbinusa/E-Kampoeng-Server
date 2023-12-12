package com.e_kampoeng.controller;


import com.e_kampoeng.dto.Warga;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.service.WargaService;
import com.e_kampoeng.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warga")
@CrossOrigin(origins = "http://localhost:3000")
public class WargaController {

    public static final Logger logger = LoggerFactory.getLogger(WargaController.class);

    @Autowired
    private WargaService dataWarga;

    // ---------------------------------Create a data siswa-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public CommonResponse<WargaModel> createProduct(@RequestBody Warga wargaData) throws SQLException, ClassNotFoundException {
        logger.info("Creating Data : {}", wargaData);

        return ResponseHelper.ok(dataWarga.save(wargaData));
    }

    // ---------------------------------Get All data siswa-------------------------------------------
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public CommonResponse<List<WargaModel>> listAll() throws SQLException, ClassNotFoundException {

        List<WargaModel> dataWargas = dataWarga.findAll();

        return ResponseHelper.ok(dataWargas);
    }

    // ---------------------------------Get Single data siswa-------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getData(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching data a with id {}", id);

        Optional<WargaModel> wargaData = dataWarga.findById(id);

        if (wargaData == null) {
            logger.error("data with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("data with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(wargaData, HttpStatus.OK);
    }

    // ---------------------------------Update data siswa-------------------------------------------
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateData(@PathVariable("id") long id, @RequestBody Warga wargaData) throws SQLException, ClassNotFoundException {
        logger.info("Updating data with id {}", id);

        Optional<WargaModel> currentData = dataWarga.findById(id);

        if (currentData == null) {
            logger.error("Unable to update. data with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. data with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        currentData.orElseThrow().setNama(wargaData.getNama());
        currentData.orElseThrow().setTanggal_lahir(wargaData.getTanggal_lahir());
        currentData.orElseThrow().setTempat_lahir(wargaData.getTempat_lahir());
        currentData.orElseThrow().setAgama(wargaData.getAgama());
        currentData.orElseThrow().setPendidikan(wargaData.getPendidikan());
        currentData.orElseThrow().setJenis_kelamin(wargaData.getJenis_kelamin());
        currentData.orElseThrow().setGolongan_darah(wargaData.getGolongan_darah());
        currentData.orElseThrow().setPekerjaan(wargaData.getPekerjaan());
        currentData.orElseThrow().setStatus_dalam_keluarga(wargaData.getStatus_dalam_keluarga());
        currentData.orElseThrow().setNo_kk(wargaData.getNo_kk());
        currentData.orElseThrow().setNik(wargaData.getNik());
        currentData.orElseThrow().setStatus_kependudukan(wargaData.getStatus_kependudukan());
        currentData.orElseThrow().setStatus_perkawinan(wargaData.getStatus_perkawinan());
        currentData.orElseThrow().setJenis_asuransi(wargaData.getJenis_asuransi());
        currentData.orElseThrow().setJenis_kb(wargaData.getJenis_kb());
        currentData.orElseThrow().setSumber_air(wargaData.getSumber_air());


        dataWarga.update(currentData.get().getId());
        return new ResponseEntity<>(currentData, HttpStatus.OK);
    }

    // ---------------------------------Delete data siswa-------------------------------------------
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteData(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching & Deleting data with id {}", id);

        dataWarga.delete(id);
        return new ResponseEntity<WargaModel>(HttpStatus.NO_CONTENT);
    }
}