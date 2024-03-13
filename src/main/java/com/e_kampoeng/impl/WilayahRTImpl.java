package com.e_kampoeng.impl;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.request.WilayahRTRequestDTO;
import com.e_kampoeng.response.WargaByRTResponseDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.response.WilayahRWResponseDTO;
import com.e_kampoeng.service.WilayahRTService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WilayahRTImpl implements WilayahRTService {

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    @Autowired
    private WargaRepository wargaRepository;

    @Override
    public List<WilayahRTResponseDTO> getAllWilayahRT() {
        List<WilayahRTModel> wilayahRTModels = wilayahRTRepository.findAll();
        return wilayahRTModels.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public WilayahRTResponseDTO getWilayahRTById(Long id) {
        WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + id));
        return mapToDto(wilayahRTModel);
    }


    private WilayahRTResponseDTO mapToDto(WilayahRTModel rt) {
        WilayahRTResponseDTO dto = new WilayahRTResponseDTO();
        dto.setId(rt.getId());
        dto.setNomor_rt(rt.getNomor_rt());
        if (rt.getWilRW() != null) {
            WilayahRWModel wilRW = rt.getWilRW();
            WilayahRWResponseDTO wilayahRWResponseDTO = new WilayahRWResponseDTO();
            wilayahRWResponseDTO.setId(wilRW.getId());
            wilayahRWResponseDTO.setNomor_rw(wilRW.getNomor_rw());
            wilayahRWResponseDTO.setNama_dusun(wilRW.getNama_dusun());
            dto.setWilayah_rw(wilayahRWResponseDTO);
        }
        return dto;
    }


    @Override
    public WilayahRTResponseDTO createWilayahRT(WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRTModel = new WilayahRTModel();
        BeanUtils.copyProperties(requestDTO, wilayahRTModel);
        WilayahRWModel wilRW = wilayahRWRepository.findById(requestDTO.getWilayah_rw_id())
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + requestDTO.getWilayah_rw_id()));
        wilayahRTModel.setWilRW(wilRW);
        WilayahRTModel savedModel = wilayahRTRepository.save(wilayahRTModel);
        WilayahRTResponseDTO responseDTO = new WilayahRTResponseDTO();
        BeanUtils.copyProperties(savedModel, responseDTO);
        responseDTO.setWilayah_rw(mapToDto(wilRW)); // Mengonversi entitas ke DTO
        return responseDTO;
    }

    @Override
    public WilayahRTResponseDTO updateWilayahRT(Long id, WilayahRTRequestDTO requestDTO) {
        WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + id));
        BeanUtils.copyProperties(requestDTO, wilayahRTModel);
        WilayahRWModel wilRW = wilayahRWRepository.findById(requestDTO.getWilayah_rw_id())
                .orElseThrow(() -> new RuntimeException("Wilayah RW not found with id: " + requestDTO.getWilayah_rw_id()));
        wilayahRTModel.setWilRW(wilRW);
        WilayahRTModel updatedModel = wilayahRTRepository.save(wilayahRTModel);
        WilayahRTResponseDTO responseDTO = new WilayahRTResponseDTO();
        BeanUtils.copyProperties(updatedModel, responseDTO);
        responseDTO.setWilayah_rw(mapToDto(wilRW)); // Mengonversi entitas ke DTO
        return responseDTO;
    }

  private WilayahRWResponseDTO mapToDto(WilayahRWModel rw) {
        WilayahRWResponseDTO dto = new WilayahRWResponseDTO();
        dto.setId(rw.getId());
        dto.setNomor_rw(rw.getNomor_rw());
        dto.setNama_dusun(rw.getNama_dusun());
        return dto;
    }

    @Override
    public void deleteWilayahRT(Long id) {
        wilayahRTRepository.deleteById(id);
    }

    @Override
    public List<WargaByRTResponseDTO> getWargaByRT(Long idWilayahRT) {
        List<WargaModel> wargaModels = wargaRepository.findByWilayahRTId(idWilayahRT);
        return wargaModels.stream()
                .map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    private WargaByRTResponseDTO convertModelToDTO(WargaModel wargaModel) {
        WargaByRTResponseDTO responseDTO = new WargaByRTResponseDTO();

        // Mengisi data pada DTO dengan data dari model
        responseDTO.setId(wargaModel.getId());
        responseDTO.setNama(wargaModel.getNama());
        responseDTO.setTempat_lahir(wargaModel.getTempat_lahir());
        responseDTO.setTanggal_lahir(wargaModel.getTanggal_lahir());
        responseDTO.setJenis_kelamin(wargaModel.getJenis_kelamin());
        responseDTO.setAgama(wargaModel.getAgama());
        responseDTO.setNik(wargaModel.getNik());
        responseDTO.setNo_kk(wargaModel.getNo_kk());
        responseDTO.setStatus_dalam_keluarga(wargaModel.getStatus_dalam_keluarga());
        responseDTO.setStatus_kependudukan(wargaModel.getStatus_kependudukan());
        responseDTO.setNo_anak(wargaModel.getNo_anak());
        responseDTO.setPanjang_lahir(wargaModel.getPanjang_lahir());
        responseDTO.setBerat_lahir(wargaModel.getBerat_lahir());
        responseDTO.setNo_passport(wargaModel.getNo_passport());
        responseDTO.setNama_ayah(wargaModel.getNama_ayah());
        responseDTO.setNama_ibu(wargaModel.getNama_ibu());
        responseDTO.setNo_telp(wargaModel.getNo_telp());
        responseDTO.setEmail(wargaModel.getEmail());
        responseDTO.setAlamat(wargaModel.getAlamat());
        responseDTO.setAlamat_sebelumnya(wargaModel.getAlamat_sebelumnya());
        responseDTO.setTanggal_perkawinan(wargaModel.getTanggal_perkawinan());
        responseDTO.setNo_bpjs(wargaModel.getNo_bpjs());
        responseDTO.setPendidikan_tempuh(wargaModel.getPendidikan_tempuh());
        responseDTO.setPendidikan_terakhir(wargaModel.getPendidikan_terakhir());
        responseDTO.setStatus_perkawinan(wargaModel.getStatus_perkawinan());
        responseDTO.setGolongan_darah(wargaModel.getGolongan_darah());
        responseDTO.setJenis_asuransi(wargaModel.getJenis_asuransi());
        responseDTO.setJenis_kb(wargaModel.getJenis_kb());
        responseDTO.setKesesuaian_tempat(wargaModel.getKesesuaian_tempat());
        responseDTO.setSumber_air(wargaModel.getSumber_air());

        return responseDTO;
    }

}
