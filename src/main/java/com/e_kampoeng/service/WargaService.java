package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.request.WargaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WargaService {
    @Autowired
    private WargaRepository wargaRepository;

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    public List<WargaModel> getAllWarga() {
        return wargaRepository.findAll();
    }

    public WargaModel createWarga(WargaRequestDTO requestDTO) {
        WargaModel warga = new WargaModel();
        warga.setNama(requestDTO.getNama());
        warga.setTempat_lahir(requestDTO.getTempat_lahir());
        warga.setTanggal_lahir(requestDTO.getTanggal_lahir());
        warga.setJenis_kelamin(requestDTO.getJenis_kelamin());
        warga.setAgama(requestDTO.getAgama());
        warga.setNik(requestDTO.getNik());
        warga.setNo_kk(requestDTO.getNo_kk());
        warga.setStatus_dalam_keluarga(requestDTO.getStatus_dalam_keluarga());
        warga.setStatus_kependudukan(requestDTO.getStatus_kependudukan());
        warga.setNo_anak(requestDTO.getNo_anak());
        warga.setPanjang_lahir(requestDTO.getPanjang_lahir());
        warga.setBerat_lahir(requestDTO.getBerat_lahir());
        warga.setNo_passport(requestDTO.getNo_passport());
        warga.setNama_ayah(requestDTO.getNama_ayah());
        warga.setNama_ibu(requestDTO.getNama_ibu());
        warga.setNo_telp(requestDTO.getNo_telp());
        warga.setEmail(requestDTO.getEmail());
        warga.setAlamat(requestDTO.getAlamat());
        warga.setTanggal_perkawinan(requestDTO.getTanggal_perkawinan());
        warga.setAlamat_sebelumnya(requestDTO.getAlamat_sebelumnya());
        warga.setNo_bpjs(requestDTO.getNo_bpjs());
        warga.setPendidikan_tempuh(requestDTO.getPendidikan_tempuh());
        warga.setPendidikan_terakhir(requestDTO.getPendidikan_terakhir());
        warga.setStatus_perkawinan(requestDTO.getStatus_perkawinan());
        warga.setGolongan_darah(requestDTO.getGolongan_darah());
        warga.setJenis_asuransi(requestDTO.getJenis_asuransi());
        warga.setJenis_kb(requestDTO.getJenis_kb());
        warga.setKesesuaian_tempat(requestDTO.getKesesuaian_tempat());
        warga.setSumber_air(requestDTO.getSumber_air());

        // Mengambil data Wilayah_RT dari repositori
        WilayahRTModel wilayahRT = wilayahRTRepository.findById(requestDTO.getWilayahRTId()).orElse(null);
        warga.setWilayahRT(wilayahRT);

        return wargaRepository.save(warga);
    }
    public Page<WargaModel> getWargaByRW(Long rwId, Pageable pageable) {
        return wargaRepository.findByWilayahRT_WilayahRW_Id(rwId, pageable);
    }

    public Page<WargaModel> getWargaByRT(Long rtId, Pageable pageable) {
        return wargaRepository.findByWilayahRT_Id(rtId, pageable);
    }

    public WargaModel updateWarga(Long wargaId, WargaRequestDTO requestDTO) {
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new RuntimeException("Warga not found with id: " + wargaId));

        // Set new values from the request DTO
        warga.setNama(requestDTO.getNama());
        warga.setTempat_lahir(requestDTO.getTempat_lahir());
        warga.setTanggal_lahir(requestDTO.getTanggal_lahir());
        warga.setJenis_kelamin(requestDTO.getJenis_kelamin());
        warga.setAgama(requestDTO.getAgama());
        warga.setNik(requestDTO.getNik());
        warga.setNo_kk(requestDTO.getNo_kk());
        warga.setStatus_dalam_keluarga(requestDTO.getStatus_dalam_keluarga());
        warga.setStatus_kependudukan(requestDTO.getStatus_kependudukan());
        warga.setNo_anak(requestDTO.getNo_anak());
        warga.setPanjang_lahir(requestDTO.getPanjang_lahir());
        warga.setBerat_lahir(requestDTO.getBerat_lahir());
        warga.setNo_passport(requestDTO.getNo_passport());
        warga.setNama_ayah(requestDTO.getNama_ayah());
        warga.setNama_ibu(requestDTO.getNama_ibu());
        warga.setNo_telp(requestDTO.getNo_telp());
        warga.setEmail(requestDTO.getEmail());
        warga.setAlamat(requestDTO.getAlamat());
        warga.setTanggal_perkawinan(requestDTO.getTanggal_perkawinan());
        warga.setAlamat_sebelumnya(requestDTO.getAlamat_sebelumnya());
        warga.setNo_bpjs(requestDTO.getNo_bpjs());
        warga.setPendidikan_tempuh(requestDTO.getPendidikan_tempuh());
        warga.setPendidikan_terakhir(requestDTO.getPendidikan_terakhir());
        warga.setStatus_perkawinan(requestDTO.getStatus_perkawinan());
        warga.setGolongan_darah(requestDTO.getGolongan_darah());
        warga.setJenis_asuransi(requestDTO.getJenis_asuransi());
        warga.setJenis_kb(requestDTO.getJenis_kb());
        warga.setKesesuaian_tempat(requestDTO.getKesesuaian_tempat());
        warga.setSumber_air(requestDTO.getSumber_air());

        // Update WilayahRT only if the ID in the request DTO is different
        if (!Objects.equals(warga.getWilayahRT().getId(), requestDTO.getWilayahRTId())) {
            WilayahRTModel wilayahRT = wilayahRTRepository.findById(requestDTO.getWilayahRTId())
                    .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + requestDTO.getWilayahRTId()));
            warga.setWilayahRT(wilayahRT);
        }

        return wargaRepository.save(warga);
    }

    public ResponseEntity<?> deleteWarga(Long wargaId) {
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new RuntimeException("Warga not found with id: " + wargaId));

        // Delete the warga
        wargaRepository.delete(warga);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Warga with id " + wargaId + " has been deleted successfully");
    }
}
