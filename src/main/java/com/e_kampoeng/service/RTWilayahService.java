package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.RTWilayahRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.model.RTWilayah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RTWilayahService {
    @Autowired
    private RTWilayahRepository rtWilayahRepository;

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WargaRepository wargaRepository;

    public RTWilayah tambahKepalaRT(Long wilayahRTId, Long wargaId) {
        // Validasi apakah wilayahRT sudah memiliki kepala RT
        if (rtWilayahRepository.existsByWilayahRTId(wilayahRTId)) {
            throw new RuntimeException("Wilayah RT sudah memiliki kepala RT");
        }

        // Validasi apakah warga berasal dari wilayahRT yang ingin ditambahkan sebagai kepala RT
        WargaModel warga = wargaRepository.findById(wargaId).orElse(null);
        if (warga == null || !Objects.equals(warga.getWilayahRT().getId(), wilayahRTId)) {
            throw new RuntimeException("Warga bukan berasal dari Wilayah RT yang ditentukan");
        }

        // Buat objek RTWilayah dan set nilainya
        RTWilayah rtWilayah = new RTWilayah();
        rtWilayah.setWilayahRT(wilayahRTRepository.findById(wilayahRTId).orElse(null)); // Mengambil WilayahRT dari repositori berdasarkan ID
        rtWilayah.setWarga(warga);

        return rtWilayahRepository.save(rtWilayah);
    }

}
