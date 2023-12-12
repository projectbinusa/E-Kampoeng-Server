package com.e_kampoeng.service;

import com.e_kampoeng.dao.RWDao;
import com.e_kampoeng.dto.DataGuruDTO;
import com.e_kampoeng.model.RWModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtDataGuruDetailsService {

    //    ACTION
    @Autowired
    private RWDao dataGuruDao;
    private long id;

    //    Constructor
    public JwtDataGuruDetailsService() {
    }

    public RWModel save(DataGuruDTO dataGuru) {
        RWModel newDataGuru = new RWModel();
        newDataGuru.setNama(dataGuru.getNama());
        newDataGuru.setTempat(dataGuru.getTempat());
        newDataGuru.setTanggal(dataGuru.getTanggal());
        newDataGuru.setAlamat(dataGuru.getAlamat());

        return dataGuruDao.save(newDataGuru);
    }

    //    for find by id
    public Optional<RWModel> findById(Long id) {
        return Optional.ofNullable(dataGuruDao.findById(id));
    }

    //    for find all
    public List<RWModel> findAll(){
        List<RWModel> dataGurus = new ArrayList<>();
        dataGuruDao.findAll().forEach(dataGurus::add);
        return dataGurus;
    }

    //    for delete
    public void delete(long id) {
        RWModel dataGuru = dataGuruDao.findById(id);
        dataGuruDao.delete(dataGuru);
    }

    //    for update
    public RWModel update(Long id) {
        RWModel dataGuru = dataGuruDao.findById(id);
        return dataGuruDao.save(dataGuru);
    }
}
