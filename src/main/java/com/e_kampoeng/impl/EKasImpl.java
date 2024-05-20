//package com.e_kampoeng.impl;
//
//import com.e_kampoeng.model.EKasModel;
//import com.e_kampoeng.repository.EKasRepository;
//import com.e_kampoeng.request.PemasukanDTO;
//import com.e_kampoeng.request.PengeluaranDTO;
//import com.e_kampoeng.service.EKasService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EKasImpl implements EKasService {
//
//    @Autowired
//    private EKasRepository eKasRepository;
//
//    @Override
//    public Long getSaldo() {
//        // Mengambil semua data EKasModel dari repository
//        List<EKasModel> eKasModels = eKasRepository.findAll();
//
//        // Menghitung saldo kotor dan saldo bersih berdasarkan semua data EKasModel
//        Long totalPemasukan = eKasModels.stream().mapToLong(EKasModel::getPemasukan).sum();
//        Long totalPengeluaran = eKasModels.stream().mapToLong(EKasModel::getPengeluaran).sum();
//        Long saldoKotor = totalPemasukan - totalPengeluaran;
//
//        return saldoKotor;
//    }
//
//    @Override
//    public EKasModel addPemasukan(PemasukanDTO pemasukanDTO) {
//        EKasModel eKasModel = new EKasModel();
//        eKasModel.setPemasukan(pemasukanDTO.getPemasukan());
//        eKasModel.setKet_pemasukan(pemasukanDTO.getKetPemasukan());
//        eKasModel.setWaktu(pemasukanDTO.getWaktu());
//        eKasModel.setSaldo(pemasukanDTO.getPemasukan()); // Menambahkan saldo dari pemasukan
//        return eKasRepository.save(eKasModel);
//    }
//
//    @Override
//    public EKasModel updatePemasukan(Long id, PemasukanDTO pemasukanDTO) {
//        Optional<EKasModel> optionalEKas = eKasRepository.findById(id);
//        if (optionalEKas.isPresent()) {
//            EKasModel eKasModel = optionalEKas.get();
//            eKasModel.setPemasukan(pemasukanDTO.getPemasukan());
//            eKasModel.setKet_pemasukan(pemasukanDTO.getKetPemasukan());
//            eKasModel.setWaktu(pemasukanDTO.getWaktu());
//            eKasModel.setSaldo(pemasukanDTO.getPemasukan()); // Menambahkan saldo dari pemasukan
//            return eKasRepository.save(eKasModel);
//        }
//        // Handle jika id tidak ditemukan
//        return null;
//    }
//
//    @Override
//    public void deletePemasukan(Long id) {
//        eKasRepository.deleteById(id);
//    }
//
//    @Override
//    public EKasModel addPengeluaran(PengeluaranDTO pengeluaranDTO) {
//        EKasModel eKasModel = new EKasModel();
//        eKasModel.setPengeluaran(pengeluaranDTO.getPengeluaran());
//        eKasModel.setKet_pengeluaran(pengeluaranDTO.getKetPengeluaran());
//        eKasModel.setWaktu(pengeluaranDTO.getWaktu());
//        eKasModel.setSaldo(-pengeluaranDTO.getPengeluaran()); // Mengurangkan saldo dari pengeluaran
//        return eKasRepository.save(eKasModel);
//    }
//
//    @Override
//    public EKasModel updatePengeluaran(Long id, PengeluaranDTO pengeluaranDTO) {
//        Optional<EKasModel> optionalEKas = eKasRepository.findById(id);
//        if (optionalEKas.isPresent()) {
//            EKasModel eKasModel = optionalEKas.get();
//            eKasModel.setPengeluaran(pengeluaranDTO.getPengeluaran());
//            eKasModel.setKet_pengeluaran(pengeluaranDTO.getKetPengeluaran());
//            eKasModel.setWaktu(pengeluaranDTO.getWaktu());
//            eKasModel.setSaldo(-pengeluaranDTO.getPengeluaran()); // Mengurangkan saldo dari pengeluaran
//            return eKasRepository.save(eKasModel);
//        }
//        // Handle jika id tidak ditemukan
//        return null;
//    }
//
//    @Override
//    public void deletePengeluaran(Long id) {
//        eKasRepository.deleteById(id);
//    }
//}
