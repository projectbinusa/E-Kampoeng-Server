package com.e_kampoeng.impl;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.model.WilayahRWModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.repository.WilayahRWRepository;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.response.WargaResponseDTO;
import com.e_kampoeng.response.WilayahRTResponseDTO;
import com.e_kampoeng.response.WilayahRWResponseDTO;
import com.e_kampoeng.service.WargaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WargaImpl implements WargaService {

    @Autowired
    private WargaRepository wargaRepository;

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private WilayahRWRepository wilayahRWRepository;

    @Override
    public Page<WargaResponseDTO> getAllWarga(Pageable pageable) {
        Page<WargaModel> wargaModels = wargaRepository.findAll(pageable);
        return wargaModels.map(this::convertModelToDTO);
    }

    @Override
    public WargaResponseDTO getWargaById(Long id) {
        return convertModelToDTO(wargaRepository.findById(id).orElse(null));
    }

    @Override
    public WargaResponseDTO createWarga(WargaRequestDTO requestDTO) {
        WargaModel wargaModel = new WargaModel();
        BeanUtils.copyProperties(requestDTO, wargaModel);
        if (requestDTO.getWilayah_rt_id() != null) {
            WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(requestDTO.getWilayah_rt_id())
                    .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + requestDTO.getWilayah_rt_id()));

            wargaModel.setWilayah_rt(wilayahRTModel);

            // Ambil WilayahRWModel yang terkait dengan WilayahRTModel
            WilayahRWModel wilayahRWModel = wilayahRTModel.getWilRW();
            if (wilayahRWModel == null) {
                throw new RuntimeException("Wilayah RW not found for Wilayah RT with id: " + wilayahRTModel.getId());
            }

            // Set wilayah_rw pada WargaModel
            wargaModel.setWilayah_rw(wilayahRWModel);
        }
        WargaModel savedModel = wargaRepository.save(wargaModel);
        return convertModelToDTO(savedModel);
    }



    @Override
    public WargaResponseDTO updateWarga(Long id, WargaRequestDTO requestDTO) {
        WargaModel wargaModel = wargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warga not found with id: " + id));

        // Menggunakan BeanUtils untuk menyalin properti dari requestDTO ke wargaModel
        BeanUtils.copyProperties(requestDTO, wargaModel);

        // Jika wilayah_rt_id berubah
        if (!Objects.equals(requestDTO.getWilayah_rt_id(), wargaModel.getWilayah_rt())) {
            WilayahRTModel wilayahRTModel = wilayahRTRepository.findById(requestDTO.getWilayah_rt_id())
                    .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + requestDTO.getWilayah_rt_id()));

            // Set wilayah_rt baru
            wargaModel.setWilayah_rt(wilayahRTModel);

            // Ambil WilayahRWModel yang terkait dengan WilayahRTModel
            WilayahRWModel wilayahRWModel = wilayahRTModel.getWilRW();
            if (wilayahRWModel == null) {
                throw new RuntimeException("Wilayah RW not found for Wilayah RT with id: " + wilayahRTModel.getId());
            }

            // Set wilayah_rw pada WargaModel
            wargaModel.setWilayah_rw(wilayahRWModel);
        }

        WargaModel updatedModel = wargaRepository.save(wargaModel);
        return convertModelToDTO(updatedModel);
    }

    @Override
    public void deleteWarga(Long id) {
        wargaRepository.deleteById(id);
    }

    private WargaResponseDTO convertModelToDTO(WargaModel wargaModel) {
        WargaResponseDTO responseDTO = new WargaResponseDTO();
        BeanUtils.copyProperties(wargaModel, responseDTO);
        if (wargaModel.getWilayah_rt() != null) {
            WilayahRTModel wilayahRTModel = wargaModel.getWilayah_rt();
            WilayahRTResponseDTO wilayahRTResponseDTO = new WilayahRTResponseDTO();
            // set atribut yang diperlukan dari WilayahRTModel ke WilayahRTResponseDTO
            wilayahRTResponseDTO.setId(wilayahRTModel.getId());
            wilayahRTResponseDTO.setNomor_rt(wilayahRTModel.getNomor_rt());

            // Konversi dan atur atribut wilayah_rw dalam WilayahRTResponseDTO
            if (wilayahRTModel.getWilRW() != null) {
                WilayahRWModel wilayahRWModel = wilayahRTModel.getWilRW();
                WilayahRWResponseDTO wilayahRWResponseDTO = new WilayahRWResponseDTO();
                wilayahRWResponseDTO.setId(wilayahRWModel.getId());
                wilayahRWResponseDTO.setNomor_rw(wilayahRWModel.getNomor_rw());
                wilayahRWResponseDTO.setNama_dusun(wilayahRWModel.getNama_dusun());
                wilayahRTResponseDTO.setWilayah_rw(wilayahRWResponseDTO);
            }

            // set atribut wilayah_rt dalam WargaResponseDTO dengan WilayahRTResponseDTO yang baru dibuat
            responseDTO.setWilayah_rt(wilayahRTResponseDTO);
        }
        return responseDTO;
    }


}
