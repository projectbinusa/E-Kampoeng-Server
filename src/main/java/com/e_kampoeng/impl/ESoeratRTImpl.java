package com.e_kampoeng.impl;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.ESoeratDao;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.request.ESoeratApprovalRequestDTO;
import com.e_kampoeng.request.ESoeratRequestDTO;
import com.e_kampoeng.request.ESoeratUpdateRequestDTO;
import com.e_kampoeng.service.ESoeratRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ESoeratRTImpl implements ESoeratRTService {

    @Autowired
    private ESoeratDao soeratRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public Page<ESoeratModel> getAllSuratByWilayahRT(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        Long wilayahRTId = wargaRepository.findByEmail(creatorEmail).getWilayahRT().getId();
        return soeratRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    @Override
    public ESoeratModel approveSurat(Long id, ESoeratApprovalRequestDTO approvalRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        WargaModel warga = wargaRepository.findByEmail(creatorEmail);

        // Ensure that the logged-in user is an RT
        if (!warga.getRole().equals("rt")) {
            throw new NotFoundException("You are not authorized to approve this surat.");
        }

        // Retrieve the surat based on the ID
        ESoeratModel existingSurat = soeratRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Surat not found"));

        // Update status to "approved" and set approval message
        existingSurat.setStatus(approvalRequestDTO.getStatus());
        existingSurat.setWaktuDiSetujui(new Date());
        existingSurat.setMessage(approvalRequestDTO.getMessage());

        // Save the updated surat
        return soeratRepository.save(existingSurat);
    }

    @Override
    public ESoeratModel ajukanSurat(ESoeratRequestDTO suratRequestDTO) {
        // Periksa apakah autentikasi berhasil
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Dapatkan creator email
            String creatorEmail = authentication.getName();
            if (creatorEmail != null && !creatorEmail.isEmpty()) {
                // Dapatkan warga berdasarkan creator email
                WargaModel warga = wargaRepository.findByEmail(creatorEmail);
                if (warga != null) {
                    // Dapatkan wilayah RT dari warga yang sedang login
                    WilayahRTModel wilayahRT = warga.getWilayahRT();
                    if (wilayahRT != null) {
                        // Buat instansi surat baru
                        ESoeratModel surat = new ESoeratModel();
                        surat.setJenisSurat(suratRequestDTO.getJenisSurat());
                        surat.setWaktuPengajuan(new Date()); // Set waktu pengajuan otomatis
                        surat.setStatus("Menunggu Di Respon"); // Set status default
                        surat.setWarga(warga); // Set warga yang sedang login
                        surat.setWilayahRT(wilayahRT); // Set wilayah RT pengguna yang sedang login
                        surat.setCreatorEmail(creatorEmail); // Set creator email

                        // Simpan surat ke repository
                        return soeratRepository.save(surat);
                    } else {
                        throw new NotFoundException("Wilayah RT not found for the creator");
                    }
                } else {
                    throw new NotFoundException("Warga not found for the creator email");
                }
            } else {
                throw new NotFoundException("Creator email is null or empty");
            }
        } else {
            throw new NotFoundException("Authentication failed or user is not authenticated");
        }
    }



    @Override
    public ESoeratModel editPengajuanSurat(Long id, ESoeratRequestDTO suratRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        ESoeratModel existingSurat = soeratRepository.findByIdAndCreatorEmail(id, creatorEmail)
                .orElseThrow(() -> new NotFoundException("Surat not found for the creator"));

        existingSurat.setJenisSurat(suratRequestDTO.getJenisSurat());
        // Set other attributes if needed

        return soeratRepository.save(existingSurat);
    }

    @Override
    public void batalkanPengajuanSurat(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        ESoeratModel existingSurat = soeratRepository.findByIdAndCreatorEmail(id, creatorEmail)
                .orElseThrow(() -> new NotFoundException("Surat not found for the creator"));

        soeratRepository.delete(existingSurat);
    }

    @Override
    public ESoeratModel getSuratByIdForWarga(Long id, String creatorEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();

        // Ensure that the authenticated user is accessing their own data
        if (!authenticatedEmail.equals(creatorEmail)) {
            throw new NotFoundException("You are not authorized to access this data.");
        }

        return soeratRepository.findByIdAndCreatorEmail(id, creatorEmail)
                .orElseThrow(() -> new NotFoundException("Surat not found for the creator"));
    }

    @Override
    public Page<ESoeratModel> getAllSuratByCreator(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        return soeratRepository.findAllByCreatorEmail(creatorEmail, pageable);
    }

    @Override
    public Page<ESoeratModel> getApprovedSuratByWilayahRT(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        Long wilayahRTId = wargaRepository.findByEmail(creatorEmail).getWilayahRT().getId();
        return soeratRepository.findAllByWilayahRTIdAndStatus(wilayahRTId, "Di Setujui", pageable);
    }

    @Override
    public Page<ESoeratModel> getUnapprovedSuratByWilayahRT(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        Long wilayahRTId = wargaRepository.findByEmail(creatorEmail).getWilayahRT().getId();
        return soeratRepository.findAllByWilayahRTIdAndStatus(wilayahRTId, "Menunggu Di Respon", pageable);
    }

    @Override
    public Page<ESoeratModel> getRejectedSuratByWilayahRT(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = authentication.getName();
        Long wilayahRTId = wargaRepository.findByEmail(creatorEmail).getWilayahRT().getId();
        return soeratRepository.findAllByWilayahRTIdAndStatus(wilayahRTId, "Di Tolak", pageable);
    }
}
