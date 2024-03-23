package com.e_kampoeng.impl;

import com.e_kampoeng.dto.TagsDTO;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.Tags;
import com.e_kampoeng.repository.TagsRepository;
import com.e_kampoeng.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagsImpl implements TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    public TagsImpl() {
    }

    @Override
    public Tags save(TagsDTO tagss) {
        Tags tags = new Tags();
        tags.setTags(tagss.getTags());
        return tagsRepository.save(tags);
    }

    @Override
    public Tags findById(Long id) {
        Tags tag = tagsRepository.findById(id).orElse(null);
        if (tag == null) {
            throw new NotFoundException("ID Tag Not Found");
        }
        return tag;
    }

//    @Override
//    public List<Tags> findAll() {
//        List<Tags> berita = new ArrayList<>();
//        tagsRepository.findAll().forEach(berita::add);
//        return berita;
//    }

    @Override
    public Page<Tags> findAllWithPagination(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            tagsRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.FALSE);
            return res;
        }
    }

    @Override
    public Tags update(Long id, TagsDTO tagsDTO) {
        Tags tags = tagsRepository.findById(id).orElseThrow(() -> new NotFoundException("ID Tag Not Found"));
        tags.setTags(tagsDTO.getTags());
        return tagsRepository.save(tags);
    }
}
