package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.RWRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.service.RWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class RWImpl implements RWService {


    @Autowired
    private RWRepository rwRepository;

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public RWModel tambahKepalaRW(Long wilayahRWId, Long wargaId) {
        // Validasi apakah wilayahRW sudah memiliki kepala RW
        if (rwRepository.existsByWilayahRWId(wilayahRWId)) {
            throw new RuntimeException("Wilayah RW sudah memiliki kepala RW");
        }

        // Validasi apakah warga berasal dari wilayahRW yang ingin ditambahkan sebagai kepala RW
        WargaModel warga = wargaRepository.findById(wargaId).orElse(null);
        if (warga == null || !Objects.equals(warga.getWilayahRT().getWilayahRW().getId(), wilayahRWId)) {
            throw new RuntimeException("Warga bukan berasal dari Wilayah RW yang ditentukan");
        }

        // Buat objek RWWilayah dan set nilainya
        RWModel rwWilayah = new RWModel();
        rwWilayah.setWilayahRW(wilayahRWRepository.findById(wilayahRWId).orElse(null));
        rwWilayah.setWarga(warga);

        return rwRepository.save(rwWilayah);
    }

    @Override
    public Page<RWModel> getAllRW(Pageable pageable) {
        return rwRepository.findAll(pageable);
    }

    @Override
    public RWModel getRWById(Long id) {
        RWModel rw = rwRepository.findById(id).orElse(null);
        if (rw == null) {
            throw new NotFoundException("ID RW Not Found");
        }
        return rw;
    }

    @Override
    public RWModel updateRW(Long rwId, Long wargaId) {
        // Retrieve RWModel from the database using rwId
        RWModel rwModel = rwRepository.findById(rwId)
                .orElseThrow(() -> new IllegalArgumentException("RW dengan ID " + rwId + " tidak ditemukan."));

        // Retrieve WargaModel from the database using wargaId
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak ditemukan."));

        // Retrieve WilayahRWModel from WargaModel
        WilayahRWModel wilayahRW = warga.getWilayahRT().getWilayahRW();

        // Check if wilayahRW is not null
        if (wilayahRW != null) {
            // Update RWModel
            rwModel.setWarga(warga);
            rwModel.setWilayahRW(wilayahRW);
            return rwRepository.save(rwModel);
        } else {
            throw new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak berasal dari wilayah RW yang dimaksud.");
        }
    }

    @Override
    public Map<String, Boolean> deleteRW(Long id) {
        try {
            rwRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }
}
