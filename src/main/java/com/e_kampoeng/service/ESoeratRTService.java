package com.e_kampoeng.service;

import com.e_kampoeng.model.ESoeratModel;
import com.e_kampoeng.request.ESoeratApprovalRequestDTO;
import com.e_kampoeng.request.ESoeratRequestDTO;
import com.e_kampoeng.request.ESoeratUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ESoeratRTService {

    Page<ESoeratModel> getAllSuratByWilayahRT(Pageable pageable);

    ESoeratModel approveSurat(Long id, ESoeratApprovalRequestDTO approvalRequestDTO);

    ESoeratModel ajukanSurat(ESoeratRequestDTO suratRequestDTO);

    ESoeratModel editPengajuanSurat(Long id, ESoeratRequestDTO suratRequestDTO);

    void batalkanPengajuanSurat(Long id);

    ESoeratModel getSuratByIdForWarga(Long id, String creatorEmail);

    Page<ESoeratModel> getAllSuratByCreator(Pageable pageable);

    Page<ESoeratModel> getApprovedSuratByWilayahRT(Pageable pageable);
    Page<ESoeratModel> getUnapprovedSuratByWilayahRT(Pageable pageable);
    Page<ESoeratModel> getRejectedSuratByWilayahRT(Pageable pageable);
}
