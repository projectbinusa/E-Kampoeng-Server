package com.data.penduduk.service;

import com.data.penduduk.model.Lelayu;
import com.data.penduduk.repository.LelayuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LelayuService {
    @Autowired
    LelayuRepository lelayuRepository;

    public List<Lelayu> getAllLelayu() {
        return lelayuRepository.findAll();
    }

    public Lelayu getLelayuById(Long id) {
        return lelayuRepository.findById(id).orElse(null);
    }

    public Lelayu createLelayu(Lelayu lelayu) {
        return lelayuRepository.save(lelayu);
    }

    public void deleteLelayu(Long id) {
        lelayuRepository.deleteById(id);
    }
}
