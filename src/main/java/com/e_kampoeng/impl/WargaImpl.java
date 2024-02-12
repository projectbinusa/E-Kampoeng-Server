package com.e_kampoeng.impl;

import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.service.WargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WargaImpl implements WargaService {

    @Autowired
    private WargaRepository wadao;

    //    for find all
    @Override
    public Page<WargaModel> getAll(Pageable pageable) {
        return wadao.findAll(pageable);
    }

    // for find by id
    @Override
    public WargaModel getById(Long id) {
        return wadao.findById(id).orElse(null);
    }

    @Override
    public WargaModel create(WargaModel wargaModel) {
        WargaModel wm = new WargaModel();
        wm.setNama(wargaModel.getNama());
        wm.setTanggal_lahir(wargaModel.getTanggal_lahir());
        wm.setTempat_lahir(wargaModel.getTempat_lahir());
        wm.setAgama(wargaModel.getAgama());
        wm.setJenis_kelamin(wargaModel.getJenis_kelamin());
        wm.setPendidikan(wargaModel.getPendidikan());
        wm.setGolongan_darah(wargaModel.getGolongan_darah());
        wm.setKesesuaian_tempat(wargaModel.getKesesuaian_tempat());
        wm.setNo_kk(wargaModel.getNo_kk());
        wm.setNik(wargaModel.getNik());
        wm.setPekerjaan(wargaModel.getPekerjaan());
        wm.setStatus_dalam_keluarga(wargaModel.getStatus_dalam_keluarga());
        wm.setStatus_perkawinan(wargaModel.getStatus_perkawinan());
        wm.setStatus_kependudukan(wargaModel.getStatus_kependudukan());
        wm.setJenis_asuransi(wargaModel.getJenis_asuransi());
        wm.setJenis_kb(wargaModel.getJenis_kb());
        wm.setSumber_air(wargaModel.getSumber_air());

        return wadao.save(wm);
    }

//    @Override
//    public WargaDTO create(WargaDTO wargaDTO) {
//        WargaModel wm = new WargaModel();
//        wm.setNama(wargaDTO.getNama());
//        wm.setTanggal_lahir(wargaDTO.getTanggal_lahir());
//        wm.setTempat_lahir(wargaDTO.getTempat_lahir());
//        wm.setAgama(wargaDTO.getAgama());
//        wm.setJenis_kelamin(wargaDTO.getJenis_kelamin());
//        wm.setPendidikan(wargaDTO.getPendidikan());
//        wm.setGolongan_darah(wargaDTO.getGolongan_darah());
//        wm.setKesesuaian_tempat(wargaDTO.getKesesuaian_tempat());
//        wm.setNo_kk(wargaDTO.getNo_kk());
//        wm.setNik(wargaDTO.getNik());
//        wm.setPekerjaan(wargaDTO.getPekerjaan());
//        wm.setStatus_dalam_keluarga(wargaDTO.getStatus_dalam_keluarga());
//        wm.setStatus_perkawinan(wargaDTO.getStatus_perkawinan());
//        wm.setStatus_kependudukan(wargaDTO.getStatus_kependudukan());
//        wm.setJenis_asuransi(wargaDTO.getJenis_asuransi());
//        wm.setJenis_kb(wargaDTO.getJenis_kb());
//        wm.setSumber_air(wargaDTO.getSumber_air());
//
//        return wadao.save(wm);
//    }

    //    for update
    @Override
    public WargaModel update(Long id, WargaModel wm) {
        WargaModel wargaModel = wadao.findById(id).orElse(null);
        wargaModel.setNama(wm.getNama());
        wargaModel.setTanggal_lahir(wm.getTanggal_lahir());
        wargaModel.setTempat_lahir(wm.getTempat_lahir());
        wargaModel.setAgama(wm.getAgama());
        wargaModel.setJenis_kelamin(wm.getJenis_kelamin());
        wargaModel.setPendidikan(wm.getPendidikan());
        wargaModel.setGolongan_darah(wm.getGolongan_darah());
        wargaModel.setKesesuaian_tempat(wm.getKesesuaian_tempat());
        wargaModel.setNo_kk(wm.getNo_kk());
        wargaModel.setNik(wm.getNik());
        wargaModel.setPekerjaan(wm.getPekerjaan());
        wargaModel.setStatus_dalam_keluarga(wm.getStatus_dalam_keluarga());
        wargaModel.setStatus_perkawinan(wm.getStatus_perkawinan());
        wargaModel.setStatus_kependudukan(wm.getStatus_kependudukan());
        wargaModel.setJenis_asuransi(wm.getJenis_asuransi());
        wargaModel.setJenis_kb(wm.getJenis_kb());
        wargaModel.setSumber_air(wm.getSumber_air());
        return wadao.save(wargaModel);
    }

    //    for delete
//    public void delete(Long id) {
//        WargaModel wargaModel = wadao.findById(id).orElse(null);
//        wadao.delete(wargaModel);
//    }


    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            wadao.findById(id).orElse(null);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted ", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            throw new NotFoundException("Warga id not found");
        }
    }
}




