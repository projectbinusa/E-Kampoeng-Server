package com.data.penduduk.service;

import com.data.penduduk.model.UndanganWarga;
import com.data.penduduk.repository.UndanganWargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteUndangan(Long id) {
        undanganWargaRepository.deleteById(id);
    }

}
