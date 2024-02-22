package com.e_kampoeng.impl;

import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.RWRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.service.RWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RWImpl implements RWService {

    @Autowired
    private RWRepository rwRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public List<RWModel> getAllRW() {
        return rwRepository.findAll();
    }

    @Override
    public Optional<RWModel> getRWById(Long id) {
        return rwRepository.findById(id);
    }

    @Override
    public RWModel assignRW(Long wargaId, Long nomorRW) {
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak ditemukan."));

        RWModel rw = new RWModel();
        rw.setNomorRW(nomorRW);
        rw.setWarga(warga);
        return rwRepository.save(rw);
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
        WilayahRWModel wilayahRW = warga.getWilayah_rt().getWilRW();

        // Check if wilayahRW is not null
        if (wilayahRW != null) {
            // Update RWModel
            rwModel.setNomorRW(wilayahRW.getNomor_rw()); // Set nomorRW from wilayahRW
            rwModel.setWarga(warga);
            rwModel.setWilayahRW(wilayahRW);
            return rwRepository.save(rwModel);
        } else {
            throw new IllegalArgumentException("Warga dengan ID " + wargaId + " tidak berasal dari wilayah RW yang dimaksud.");
        }
    }

    @Override
    public void deleteRW(Long id) {
        rwRepository.deleteById(id);
    }
}
