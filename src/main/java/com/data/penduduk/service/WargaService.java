package com.data.penduduk.service;

import com.data.penduduk.model.Kk;
import com.data.penduduk.model.Warga;
import com.data.penduduk.repository.KkRepository;
import com.data.penduduk.repository.WargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.util.List;

@Service
public class WargaService {

    @Autowired
    WargaRepository wargaRepository;

    @Autowired
    KkRepository kkRepository;

    public List<Warga> getAllWarga() {
        return wargaRepository.findAll();
    }

    public List<Warga> getWargaByKk(Long id) {
        Kk kk = kkRepository.findById(id).orElse(null);
        return wargaRepository.findWargaByKk(kk);
    }

    public Warga getWargaById(Long id) {
        return wargaRepository.findById(id).orElse(null);
    }

    public Warga createWarga(Warga warga, Long id) {
        Kk kk = kkRepository.findById(id).orElse(null);
        warga.setKk(kk);
        return wargaRepository.save(warga);
    }

    public Warga editWarga(Long id, String nama, String tempat_lahir, String tgl_lahir, String gender, String agama, String status) {
        Warga warga = wargaRepository.findById(id).orElse(null);
        warga.setNama(nama);
        warga.setTempat_lahir(tempat_lahir);
        warga.setTgl_lahir(tgl_lahir);
        warga.setGender(gender);
        warga.setAgama(agama);
        warga.setStatus(status);
        return wargaRepository.save(warga);
    }

    public void deleteWarga(Long id) {
        wargaRepository.deleteById(id);
    }

}
