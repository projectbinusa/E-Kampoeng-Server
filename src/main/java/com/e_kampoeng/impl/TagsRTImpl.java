package com.e_kampoeng.impl;

import com.e_kampoeng.dto.TagsDTO;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.Tags;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.TagsRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.service.TagsRTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TagsRTImpl implements TagsRTService {
    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private WargaRepository wargaRepository;

    private Long getWilayahRTIdByEmail(String email) {
        WargaModel warga = wargaRepository.findByEmail(email);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        return warga.getWilayahRT().getId();
    }

    @Override
    public Tags save(TagsDTO tagsDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        Tags tags = new Tags();
        tags.setTags(tagsDTO.getTags());
        tags.setWilayahRT(wargaRepository.findByEmail(email).getWilayahRT());

        return tagsRepository.save(tags);
    }

    @Override
    public Tags findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        Tags tag = tagsRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("ID Tag Not Found"));
        return tag;
    }

    @Override
    public Page<Tags> findAllWithPagination(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        return tagsRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        Tags tag = tagsRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("ID Tag Not Found"));

        tagsRepository.delete(tag);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public Tags update(Long id, TagsDTO tagsDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        Tags tags = tagsRepository.findByIdAndWilayahRTId(id, wilayahRTId)
                .orElseThrow(() -> new NotFoundException("ID Tag Not Found"));
        tags.setTags(tagsDTO.getTags());

        return tagsRepository.save(tags);
    }
}
