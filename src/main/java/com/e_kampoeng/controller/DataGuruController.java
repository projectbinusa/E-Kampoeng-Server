//package com.e_kampoeng.controller;
//
//
//import com.e_kampoeng.dto.DataGuruDTO;
//import com.e_kampoeng.model.RWModel;
//import com.e_kampoeng.service.JwtDataGuruDetailsService;
//import com.e_kampoeng.util.CustomErrorType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/guru")
//@CrossOrigin(origins = "http://localhost:3000")
//public class DataGuruController {
//
//    //    SAVE METHOD
//
//    public static final Logger logger = LoggerFactory.getLogger(DataGuruController.class);
//
//    @Autowired
//    private JwtDataGuruDetailsService dataGuruDetailsService;
//
//    // ---------------------------------Create a data guru-------------------------------------------
//    @RequestMapping(value = "/data/add", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity<?> createData(@RequestBody DataGuruDTO dataGuru) throws SQLException, ClassNotFoundException {
//        logger.info("Creating Data : {}", dataGuru);
//
//        dataGuruDetailsService.save(dataGuru);
//
//        return new ResponseEntity<>(dataGuru, HttpStatus.CREATED);
//    }
//
//    // ---------------------------------Get All data guru-------------------------------------------
//    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
//    public ResponseEntity<List<RWModel>> listAllData() throws SQLException, ClassNotFoundException {
//
//        List<RWModel> dataGurus = dataGuruDetailsService.findAll();
//
//        return new ResponseEntity<>(dataGurus, HttpStatus.OK);
//    }
//
//    // ---------------------------------Get Single data guru-------------------------------------------
//    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> getData(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
//        logger.info("Fetching data guru with id {}", id);
//
//        Optional<RWModel> dataGuru = dataGuruDetailsService.findById(id);
//
//        if (dataGuru == null) {
//            logger.error("data guru with id {} not found.", id);
//            return new ResponseEntity<>(new CustomErrorType("data guru with id " + id + " not found"), HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(dataGuru, HttpStatus.OK);
//    }
//
//    // ---------------------------------Update data guru-------------------------------------------
//    @RequestMapping(value = "/data/update/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> updateData(@PathVariable("id") long id, @RequestBody DataGuruDTO dataGuru) throws SQLException, ClassNotFoundException {
//        logger.info("Updating data guru with id {}", id);
//
//        Optional<RWModel> currentData = dataGuruDetailsService.findById(id);
//
//        if (currentData == null) {
//            logger.error("Unable to update. data guru with id {} not found.", id);
//            return new ResponseEntity<>(new CustomErrorType("Unable to update. data guru with id " + id + " not found."), HttpStatus.NOT_FOUND);
//        }
//        currentData.orElseThrow().setNama(dataGuru.getNama());
//        currentData.orElseThrow().setTempat(dataGuru.getTempat());
//        currentData.orElseThrow().setTanggal(dataGuru.getTanggal());
//        currentData.orElseThrow().setAlamat(dataGuru.getAlamat());
//
//        dataGuruDetailsService.update(currentData.get().getId());
//        return new ResponseEntity<>(currentData, HttpStatus.OK);
//    }
//
//    // ---------------------------------Delete data guru-------------------------------------------
//    @RequestMapping(value = "/data/delete/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteData(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
//        logger.info("Fetching & Deleting data guru with id {}", id);
//
//        dataGuruDetailsService.delete(id);
//        return new ResponseEntity<RWModel>(HttpStatus.NO_CONTENT);
//    }
//}