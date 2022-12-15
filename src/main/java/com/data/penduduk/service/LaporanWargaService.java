package com.data.penduduk.service;

import com.data.penduduk.model.LaporanWarga;
import com.data.penduduk.repository.LaporanWargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class LaporanWargaService {

    @Autowired
    LaporanWargaRepository laporanWargaRepository;

    public List<LaporanWarga> getAllLaporan() {
        return laporanWargaRepository.findAll();
    }

    public LaporanWarga getLaporanById(Long id) {
        return laporanWargaRepository.findById(id).orElse(null);
    }

    public LaporanWarga createLaporan(LaporanWarga laporanWarga) {
        return laporanWargaRepository.save(laporanWarga);
    }

    public LaporanWarga editLaporan(Long id, String nama_pelapor, String judul_laporan, String laporan,String tanggapan, Date tgl_tanggapan, Date tgl_laporan) {
        LaporanWarga laporanWarga = laporanWargaRepository.findById(id).orElse(null);
        laporanWarga.setNama_pelapor(nama_pelapor);
        laporanWarga.setJudul_laporan(judul_laporan);
        laporanWarga.setLaporan(laporan);
        laporanWarga.setTgl_laporan(tgl_laporan);
        laporanWarga.setTanggapan(tanggapan);
        laporanWarga.setTgl_tanggapan(tgl_tanggapan);
        return laporanWargaRepository.save(laporanWarga);
    }

    public void deleteLaporan(Long id) {
        laporanWargaRepository.deleteById(id);
    }
}
