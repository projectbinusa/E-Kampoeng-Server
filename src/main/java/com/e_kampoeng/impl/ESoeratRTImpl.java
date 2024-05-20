package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.repository.ESoeratDao;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.service.ESoeratRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ESoeratRTImpl implements ESoeratRTService {

    @Autowired
    ESoeratDao eSoeratRepository;

    @Autowired
    WargaRepository wargaRepository;

    private Long getWilayahRTIdByEmail(String email) {
        WargaModel warga = wargaRepository.findByEmail(email);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        return warga.getWilayahRT().getId();
    }

    @Override
    public Page<ESoeratModel> getAllSoerat(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return eSoeratRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    @Override
    public ESoeratModel getIdSoerat(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return eSoeratRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("Soerat not found"));
    }

    @Override
    public ESoeratModel addSoerat(ESoeratModel eSoeratModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        eSoeratModel.setCreatorEmail(email);
        // Set other attributes if available
        eSoeratModel.setWilayahRT(wargaRepository.findByEmail(email).getWilayahRT());
        return eSoeratRepository.save(eSoeratModel);
    }

    @Override
    public ESoeratModel editSoerat(Long id, ESoeratModel eSoeratModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        ESoeratModel existingSoerat = eSoeratRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("Soerat not found or you do not have permission to edit it."));
        // Set proper attributes from the DTO to the existingSoerat
        existingSoerat.setJenis_surat(eSoeratModel.getJenis_surat());
        // Set other attributes if available
        return eSoeratRepository.save(existingSoerat);
    }

    @Override
    public Optional<ESoeratModel> getSoeratByIdAndCreator(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return eSoeratRepository.findByIdAndWilayahRTId(id, wilayahRTId);
    }

    @Override
    public Map<String, Boolean> deleteSoerat(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        eSoeratRepository.deleteByIdAndWilayahRTId(id, wilayahRTId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

}
