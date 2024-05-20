package com.e_kampoeng.service.e_kas_service;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.e_kas.SaldoModel;
import com.e_kampoeng.repository.SaldoRepository;
import com.e_kampoeng.repository.WargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {

    @Autowired
    private SaldoRepository saldoRepository;

    @Autowired
    private WargaRepository wargaRepository;

    private Long getWilayahRTIdByEmail(String email) {
        WargaModel warga = wargaRepository.findByEmail(email);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        return warga.getWilayahRT().getId();
    }

    public SaldoModel getSaldo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        return saldoRepository.findByWilayahRTId(wilayahRTId).orElse(null);
    }

    public void tambahSaldo(Double jumlah) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        SaldoModel saldoModel = saldoRepository.findByWilayahRTId(wilayahRTId).orElse(null);
        if (saldoModel != null) {
            saldoModel.setJumlahSaldo(saldoModel.getJumlahSaldo() + jumlah);
            saldoRepository.save(saldoModel);
        } else {
            // Jika saldo untuk wilayahRT belum ada, buat yang baru
            saldoModel = new SaldoModel();
            saldoModel.setJumlahSaldo(jumlah);
            saldoModel.setWilayahRT(wargaRepository.findByEmail(email).getWilayahRT());
            saldoRepository.save(saldoModel);
        }
    }

    public void kurangiSaldo(Double jumlah) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        SaldoModel saldoModel = saldoRepository.findByWilayahRTId(wilayahRTId).orElse(null);
        if (saldoModel != null) {
            saldoModel.setJumlahSaldo(saldoModel.getJumlahSaldo() - jumlah);
            saldoRepository.save(saldoModel);
        } else {
            throw new NotFoundException("Saldo not found for the user's WilayahRT");
        }
    }
}
