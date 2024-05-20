package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.OrganisasiModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.OrganisasiRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.request.OrganisasiRequestDTO;
import com.e_kampoeng.service.OrganisasiRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
public class OrganisasiRTImpl implements OrganisasiRTService {

    @Autowired
    private OrganisasiRepository organisasiRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public Page<OrganisasiModel> getAllOrganisasiRTByCreator(String creatorEmail, Pageable pageable) {
        WargaModel warga = wargaRepository.findByEmail(creatorEmail);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        Long wilayahRTId = warga.getWilayahRT().getId();
        return organisasiRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    @Override
    public Optional<OrganisasiModel> getOrganisasiRTByIdAndCreator(Long id, String creatorEmail) {
        WargaModel warga = wargaRepository.findByEmail(creatorEmail);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        Long wilayahRTId = warga.getWilayahRT().getId();
        return organisasiRepository.findByIdAndWilayahRTId(id, wilayahRTId);
    }

    @Override
    public OrganisasiModel createOrganisasiRT(OrganisasiModel organisasiModel, String creatorEmail) {
        WargaModel warga = wargaRepository.findByEmail(creatorEmail);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        organisasiModel.setCreatorEmail(creatorEmail);
        organisasiModel.setWilayahRT(warga.getWilayahRT());
        return organisasiRepository.save(organisasiModel);
    }

    @Override
    public OrganisasiModel updateOrganisasiRT(Long id, OrganisasiRequestDTO organisasiRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();

        OrganisasiModel existingOrganisasi = organisasiRepository.findByIdAndCreatorEmail(id, creatorEmail)
                .orElseThrow(() -> new NotFoundException("Organisasi not found for the creator"));

        // Set proper attributes from the DTO to the existingOrganisasi
        existingOrganisasi.setNama_organisasi(organisasiRequestDTO.getNama_organisasi());
        // Set other attributes if available

        return organisasiRepository.save(existingOrganisasi);
    }


    @Override
    public void deleteOrganisasiRT(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();

        Optional<OrganisasiModel> existingOrganisasi = organisasiRepository.findByIdAndCreatorEmail(id, creatorEmail);
        if (existingOrganisasi.isPresent()) {
            organisasiRepository.deleteByIdAndCreatorEmail(id, creatorEmail);
        } else {
            throw new NotFoundException("Organisasi not found or you do not have permission to delete it.");
        }
    }
}
