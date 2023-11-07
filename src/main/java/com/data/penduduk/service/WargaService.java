package com.data.penduduk.service;

import com.data.penduduk.model.Warga;
import com.data.penduduk.repository.WargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WargaService {

    @Autowired
    private WargaRepository repoWarga;

    public List<Warga> getAllWarga() {
        return repoWarga.findAll();
    }
}
