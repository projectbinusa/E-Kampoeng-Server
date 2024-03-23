package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.RTRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.service.RTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class RTImpl implements RTService {
    @Autowired
    private RTRepository rtRepository;

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public RTModel tambahKepalaRT(Long wilayahRTId, Long wargaId) {
        // Validasi apakah wilayahRT sudah memiliki kepala RT
        if (rtRepository.existsByWilayahRTId(wilayahRTId)) {
            throw new RuntimeException("Wilayah RT sudah memiliki kepala RT");
        }

        // Validasi apakah warga berasal dari wilayahRT yang ingin ditambahkan sebagai kepala RT
        WargaModel warga = wargaRepository.findById(wargaId).orElse(null);
        if (warga == null || !Objects.equals(warga.getWilayahRT().getId(), wilayahRTId)) {
            throw new RuntimeException("Warga bukan berasal dari Wilayah RT yang ditentukan");
        }

        // Buat objek RTModel dan set nilainya
        RTModel rtWilayah = new RTModel();
        rtWilayah.setWilayahRT(wilayahRTRepository.findById(wilayahRTId).orElse(null)); // Mengambil WilayahRT dari repositori berdasarkan ID
        rtWilayah.setWarga(warga);

        return rtRepository.save(rtWilayah);
    }


    @Override
    public RTModel updateRT(Long rtId, Long wargaId, Long nomorRT) {
        // Retrieve existing RTModel from the database using rtId
        RTModel existingRT = rtRepository.findById(rtId)
                .orElseThrow(() -> new IllegalArgumentException("RT dengan ID " + rtId + " tidak ditemukan."));

        // Retrieve WargaModel from the database using wargaId
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak ditemukan."));

        // Retrieve WilayahRTModel from WargaModel
        WilayahRTModel wilayahRT = warga.getWilayahRT();
        if (wilayahRT == null) {
            throw new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak terdaftar di wilayah RT manapun.");
        }

        // Check if nomorRT matches the wilayahRT
        if (!wilayahRT.getNomorRt().equals(nomorRT)) {
            throw new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak berasal dari wilayah RT yang dimaksud.");
        }

        // Check if nomorRT is already assigned to another RT
//        if (existingRTWithSameNomorRT.isPresent() && !existingRTWithSameNomorRT.get().getId().equals(rtId)) {
        if (rtRepository.existsByWilayahRTId(nomorRT)) {
            throw new IllegalArgumentException("Nomor RT sudah digunakan.");
        }

        // Update existing RTModel
        existingRT.setWarga(warga);
        existingRT.setWilayahRT(wilayahRT);
        return rtRepository.save(existingRT);
    }

    @Override
    public Page<RTModel> getAllRT(Pageable pageable) {
        return rtRepository.findAll(pageable);
    }

    @Override
    public RTModel getRTById(Long id) {
        RTModel rt = rtRepository.findById(id).orElse(null);
        if (rt == null) {
            throw new NotFoundException("ID RT Not Found");
        }
        return rt;
    }

    @Override
    public Map<String, Boolean> deleteRT(Long id) {
        try {
            rtRepository.deleteById(id);
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
