package com.data.penduduk.service;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Rt;
import com.data.penduduk.repository.KkRepository;
import com.data.penduduk.repository.RtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.sql.Date;
import java.util.List;

@Service
public class KkService {

    @Autowired
    KkRepository  kkRepository;

    @Autowired
    RtRepository rtRepository;

    public List<Kk> getAllKk() {
        return kkRepository.findAll();
    }

    public List<Kk> getKkByRt(Long id) {
        Rt rt = rtRepository.findById(id).orElse(null);
        return kkRepository.findKkByRt(rt);
    }

    public Kk getKkById(Long id) {
        return kkRepository.findById(id).orElse(null);
    }


    public Kk createKk(Kk kk, Long id) {
        Rt rt = rtRepository.findById(id).orElse(null);
        kk.setRt(rt);
        return kkRepository.save(kk);
    }


    public Kk editKk(Long id, String nama, String tempat_lahir, Date tgl_lahir, String gender, String agama, String status) {
        Kk kk = kkRepository.findById(id).orElse(null);
        kk.setNama(nama);
        kk.setTempat_lahir(tempat_lahir);
        kk.setTgl_lahir(tgl_lahir);
        kk.setGender(gender);
        kk.setAgama(agama);
        kk.setStatus(status);
        return kkRepository.save(kk);
    }

    public void deleteKk(Long id) {
        kkRepository.deleteById(id);
    }


}
