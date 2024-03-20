package com.e_kampoeng.service;

import com.e_kampoeng.model.RWWilayah;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.RWWilayahRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RWWilayahService {
    @Autowired
    private RWWilayahRepository rwWilayahRepository;

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    @Autowired
    private WargaRepository wargaRepository;

    public RWWilayah tambahKepalaRW(Long wilayahRWId, Long wargaId) {
        // Validasi apakah wilayahRW sudah memiliki kepala RW
        if (rwWilayahRepository.existsByWilayahRWId(wilayahRWId)) {
            throw new RuntimeException("Wilayah RW sudah memiliki kepala RW");
        }

        // Validasi apakah warga berasal dari wilayahRW yang ingin ditambahkan sebagai kepala RW
        WargaModel warga = wargaRepository.findById(wargaId).orElse(null);
        if (warga == null || !Objects.equals(warga.getWilayahRT().getWilayahRW().getId(), wilayahRWId)) {
            throw new RuntimeException("Warga bukan berasal dari Wilayah RW yang ditentukan");
        }

        // Buat objek RWWilayah dan set nilainya
        RWWilayah rwWilayah = new RWWilayah();
        rwWilayah.setWilayahRW(wilayahRWRepository.findById(wilayahRWId).orElse(null));
        rwWilayah.setWarga(warga);

        return rwWilayahRepository.save(rwWilayah);
    }
}
