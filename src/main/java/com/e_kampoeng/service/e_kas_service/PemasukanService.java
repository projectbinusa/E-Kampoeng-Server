package com.e_kampoeng.service.e_kas_service;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.e_kas.PemasukanModel;
import com.e_kampoeng.repository.PemasukanRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.request.PemasukanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PemasukanService {

    @Autowired
    private PemasukanRepository pemasukanRepository;

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

    public void addPemasukan(PemasukanDTO pemasukanDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        PemasukanModel pemasukanModel = new PemasukanModel();
        pemasukanModel.setNama(pemasukanDto.getNama());
        pemasukanModel.setJumlahPemasukan(pemasukanDto.getJumlahPemasukan());
        pemasukanModel.setWilayahRT(wargaRepository.findByEmail(email).getWilayahRT());
        pemasukanRepository.save(pemasukanModel);

        // Menambahkan jumlah pemasukan ke saldo
        saldoService.tambahSaldo(pemasukanDto.getJumlahPemasukan());
    }

    public Page<PemasukanModel> getAllPemasukan(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        Pageable pageable = PageRequest.of(page, size);
        return pemasukanRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    public PemasukanModel getDetailPemasukan(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        return pemasukanRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElse(null);
    }

    public PemasukanModel updatePemasukan(Long id, PemasukanDTO pemasukanDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        PemasukanModel pemasukanModel = pemasukanRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElse(null);
        if (pemasukanModel != null) {
            // Mengurangkan jumlah pemasukan sebelumnya dari saldo
            saldoService.kurangiSaldo(pemasukanModel.getJumlahPemasukan());

            // Memperbarui data pemasukan
            pemasukanModel.setNama(pemasukanDto.getNama());
            pemasukanModel.setJumlahPemasukan(pemasukanDto.getJumlahPemasukan());

            // Menambahkan jumlah pemasukan yang baru ke saldo
            saldoService.tambahSaldo(pemasukanDto.getJumlahPemasukan());

            // Menyimpan data pemasukan yang diperbarui
            return pemasukanRepository.save(pemasukanModel);
        }
        return null;
    }

    public void deletePemasukan(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        PemasukanModel pemasukanModel = pemasukanRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElse(null);
        if (pemasukanModel != null) {
            // Mengurangkan jumlah pemasukan dari saldo sebelum menghapus
            saldoService.kurangiSaldo(pemasukanModel.getJumlahPemasukan());

            // Hapus data pemasukan
            pemasukanRepository.deleteById(id);
        }
    }
}
