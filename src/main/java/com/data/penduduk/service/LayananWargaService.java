package com.data.penduduk.service;

import com.data.penduduk.model.LayananWarga;

import com.data.penduduk.model.Rt;
import com.data.penduduk.repository.LayananWargaRepository;
import com.data.penduduk.repository.RtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayananWargaService {
    @Autowired
    LayananWargaRepository layananWargaRepository;

    @Autowired
    RtRepository rtRepository;

    public List<LayananWarga> getAll() {
        return layananWargaRepository.findAll();
    }

    public LayananWarga createLayananWarga(LayananWarga layananWarga, Long id) {
        Rt rt = rtRepository.findById(id).orElse(null);
        layananWarga.setRt(rt);
        return layananWargaRepository.save(layananWarga);
    }

    public List<LayananWarga> getLayananByRt(Long id) {
        Rt rt = rtRepository.findById(id).orElse(null);
        return layananWargaRepository.findLayananByRt(rt);
    }

    public void deleteLayananWarga(Long id) {
        rtRepository.deleteById(id);
    }

}
