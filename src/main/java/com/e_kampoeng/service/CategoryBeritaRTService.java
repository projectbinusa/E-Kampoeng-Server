package com.e_kampoeng.service;

import com.e_kampoeng.dto.CategoryBeritaDTO;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.CategoryBerita;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.CategoryBeritaRepository;
import com.e_kampoeng.repository.WargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryBeritaRTService {

    @Autowired
    private CategoryBeritaRepository categoryBeritaRepository;

    @Autowired
    private WargaRepository wargaRepository;

    private Long getWilayahRTIdByEmail(String email) {
        WargaModel warga = wargaRepository.findByEmail(email);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        return warga.getWilayahRT().getId();
    }

    public Page<CategoryBerita> findAll(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return categoryBeritaRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    public CategoryBerita findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return categoryBeritaRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("Category Berita not found"));
    }

    public CategoryBerita save(CategoryBeritaDTO categoryBeritaDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        CategoryBerita categoryBerita = new CategoryBerita();
        categoryBerita.setCategory(categoryBeritaDTO.getCategory());
        categoryBerita.setWilayahRT(wargaRepository.findByEmail(email).getWilayahRT());
        return categoryBeritaRepository.save(categoryBerita);
    }

    @Transactional
    public void delete(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        categoryBeritaRepository.deleteByIdAndWilayahRTId(id, wilayahRTId);
    }

    public CategoryBerita update(Long id, CategoryBeritaDTO categoryBeritaDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        CategoryBerita categoryBerita = categoryBeritaRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("Category Berita not found or you do not have permission to edit it."));

        categoryBerita.setCategory(categoryBeritaDTO.getCategory());
        return categoryBeritaRepository.save(categoryBerita);
    }

    public CategoryBerita getById(Long id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        CategoryBerita categoryBerita = categoryBeritaRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("Category Berita not found"));
        return categoryBerita;
    }
}
