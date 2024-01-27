package com.e_kampoeng.service;

import com.e_kampoeng.dao.WargaDao;
import com.e_kampoeng.dto.Warga;
import com.e_kampoeng.model.WargaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WargaService {

    //    ACTION
    @Autowired
    private WargaDao dataWargaDao;
    private long id;

    //    constructor
    public WargaService() {
    }

    public WargaModel save(Warga dataWarga) {
        WargaModel data = new WargaModel();
        data.setNama(dataWarga.getNama());
        data.setTanggal_lahir(dataWarga.getTanggal_lahir());
        data.setTempat_lahir(dataWarga.getTempat_lahir());
        data.setAgama(dataWarga.getAgama());
        data.setJenis_kelamin(dataWarga.getJenis_kelamin());
        data.setPendidikan(dataWarga.getPendidikan());
        data.setGolongan_darah(dataWarga.getGolongan_darah());
        data.setKesesuaian_tempat(dataWarga.getKesesuaian_tempat());
        data.setNo_kk(dataWarga.getNo_kk());
        data.setNik(dataWarga.getNik());
        data.setPekerjaan(dataWarga.getPekerjaan());
        data.setStatus_dalam_keluarga(dataWarga.getStatus_dalam_keluarga());
        data.setStatus_perkawinan(dataWarga.getStatus_perkawinan());
        data.setStatus_kependudukan(dataWarga.getStatus_kependudukan());
        data.setJenis_asuransi(dataWarga.getJenis_asuransi());
        data.setJenis_kb(dataWarga.getJenis_kb());
        data.setSumber_air(dataWarga.getSumber_air());

        return dataWargaDao.save(data);
    }

    //      for find by id
    public Optional<WargaModel> findById(long id) {
        return Optional.ofNullable(dataWargaDao.findById(id));
    }

    //    for find all
    public List<WargaModel> findAll(){
        List<WargaModel> wargasModel = new ArrayList<>();
        dataWargaDao.findAll().forEach(wargasModel::add);
        return wargasModel;
    }

    //    for delete
    public void delete(long id) {
        WargaModel wargaModel = dataWargaDao.findById(id);
        dataWargaDao.delete(wargaModel);
    }

    //    for update
    public WargaModel update(long id) {
        WargaModel wargaModel = dataWargaDao.findById(id);
        return dataWargaDao.save(wargaModel);
    }
}




