package com.e_kampoeng.impl;

import com.e_kampoeng.model.RWModel;
import com.e_kampoeng.model.WargaModel;
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
    public void deleteRW(Long id) {
        rwRepository.deleteById(id);
    }
}
