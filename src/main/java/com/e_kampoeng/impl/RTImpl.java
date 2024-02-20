package com.e_kampoeng.impl;

import com.e_kampoeng.model.RTModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.RTRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.service.RTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RTImpl implements RTService {

    @Autowired
    private RTRepository rtRepository;
    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public RTModel assignRT(Long wargaId, Long nomorRT) {
        // Retrieve WargaModel from the database using wargaId
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak ditemukan."));

        // Retrieve WilayahRTModel from WargaModel
        WilayahRTModel wilayahRT = warga.getWilayah_rt();

        // Check if nomorRT matches the wilayahRT
        if (wilayahRT.getNomor_rt().equals(nomorRT)) {
            // Check if nomorRT is already assigned to another RT
            Optional<RTModel> existingRT = rtRepository.findByNomorRT(nomorRT);
            if (existingRT.isPresent()) {
                throw new IllegalArgumentException("Nomor RT sudah digunakan.");
            }

            RTModel rtModel = new RTModel();
            rtModel.setNomorRT(nomorRT);
            rtModel.setWarga(warga);
            rtModel.setWilayahRT(wilayahRT);
            return rtRepository.save(rtModel);
        } else {
            throw new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak berasal dari wilayah RT yang dimaksud.");
        }
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
        WilayahRTModel wilayahRT = warga.getWilayah_rt();
        if (wilayahRT == null) {
            throw new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak terdaftar di wilayah RT manapun.");
        }

        // Check if nomorRT matches the wilayahRT
        if (!wilayahRT.getNomor_rt().equals(nomorRT)) {
            throw new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak berasal dari wilayah RT yang dimaksud.");
        }

        // Check if nomorRT is already assigned to another RT
        Optional<RTModel> existingRTWithSameNomorRT = rtRepository.findByNomorRT(nomorRT);
        if (existingRTWithSameNomorRT.isPresent() && !existingRTWithSameNomorRT.get().getId().equals(rtId)) {
            throw new IllegalArgumentException("Nomor RT sudah digunakan.");
        }

        // Update existing RTModel
        existingRT.setNomorRT(nomorRT);
        existingRT.setWarga(warga);
        existingRT.setWilayahRT(wilayahRT);
        return rtRepository.save(existingRT);
    }

    @Override
    public List<RTModel> getAllRT() {
        return rtRepository.findAll();
    }

    @Override
    public Optional<RTModel> getRTById(Long id) {
        return rtRepository.findById(id);
    }

    @Override
    public void deleteRT(Long id) {
        rtRepository.deleteById(id);
    }

}
