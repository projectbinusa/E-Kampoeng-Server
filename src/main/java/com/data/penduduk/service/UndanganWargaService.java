package com.data.penduduk.service;

import com.data.penduduk.model.UndanganWarga;
import com.data.penduduk.repository.UndanganWargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UndanganWargaService {
    @Autowired
    UndanganWargaRepository undanganWargaRepository;

    public List<UndanganWarga> getAllUndangan() {
        return undanganWargaRepository.findAll();
    }

    public UndanganWarga getUndanganById(Long id) {
        return undanganWargaRepository.findById(id).orElse(null);
    }

    public UndanganWarga createUndangan(UndanganWarga undanganWarga) {
        return undanganWargaRepository.save(undanganWarga);
    }

    public UndanganWarga editUndangan(Long id, String perihal, String hari, Date tanggal, String waktu, String tempat, String acara, String ditujukan) {
        UndanganWarga undanganWarga = undanganWargaRepository.findById(id).orElse(null);
        undanganWarga.setPerihal(perihal);
        undanganWarga.setHari(hari);
        undanganWarga.setTanggal(tanggal);
        undanganWarga.setWaktu(waktu);
        undanganWarga.setTempat(tempat);
        undanganWarga.setAcara(acara);
        undanganWarga.setDitujukan(ditujukan);
        return undanganWargaRepository.save(undanganWarga);
    }

    public void deleteUndangan(Long id) {
        undanganWargaRepository.deleteById(id);
    }

}
