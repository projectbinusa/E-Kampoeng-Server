package com.e_kampoeng.service.e_kas_service;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.e_kas.PengeluaranModel;
import com.e_kampoeng.repository.PengeluaranRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.request.PengeluaranDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PengeluaranService {

    @Autowired
    private PengeluaranRepository pengeluaranRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Autowired
    private SaldoService saldoService;

    private Long getWilayahRTIdByEmail(String email) {
        WargaModel warga = wargaRepository.findByEmail(email);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        return warga.getWilayahRT().getId();
    }

    public void addPengeluaran(PengeluaranDTO pengeluaranDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        PengeluaranModel pengeluaranModel = new PengeluaranModel();
        pengeluaranModel.setKegiatan(pengeluaranDto.getKegiatan());
        pengeluaranModel.setAnggaranKeluar(pengeluaranDto.getAnggaranKeluar());
        pengeluaranModel.setWilayahRT(wargaRepository.findByEmail(email).getWilayahRT());
        pengeluaranRepository.save(pengeluaranModel);

        // Mengurangkan jumlah pengeluaran dari saldo
        saldoService.kurangiSaldo(pengeluaranDto.getAnggaranKeluar());
    }

    public Page<PengeluaranModel> getAllPengeluaran(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        return pengeluaranRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    public PengeluaranModel getDetailPengeluaran(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        return pengeluaranRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElse(null);
    }

    public PengeluaranModel updatePengeluaran(Long id, PengeluaranDTO pengeluaranDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        PengeluaranModel pengeluaranModel = pengeluaranRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElse(null);
        if (pengeluaranModel != null) {
            // Mengurangkan jumlah pengeluaran sebelumnya dari saldo
            saldoService.tambahSaldo(pengeluaranModel.getAnggaranKeluar());

            // Memperbarui data pengeluaran
            pengeluaranModel.setKegiatan(pengeluaranDto.getKegiatan());
            pengeluaranModel.setAnggaranKeluar(pengeluaranDto.getAnggaranKeluar());

            // Mengurangkan jumlah pengeluaran yang baru dari saldo
            saldoService.kurangiSaldo(pengeluaranDto.getAnggaranKeluar());

            // Menyimpan data pengeluaran yang diperbarui
            return pengeluaranRepository.save(pengeluaranModel);
        }
        return null;
    }

    public void deletePengeluaran(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        PengeluaranModel pengeluaranModel = pengeluaranRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElse(null);
        if (pengeluaranModel != null) {
            // Mengurangkan jumlah pengeluaran dari saldo sebelum menghapus
            saldoService.tambahSaldo(pengeluaranModel.getAnggaranKeluar());

            // Hapus data pengeluaran
            pengeluaranRepository.deleteById(id);
        }
    }
}
