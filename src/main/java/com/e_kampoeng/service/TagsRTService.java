package com.e_kampoeng.service;

import com.e_kampoeng.dto.TagsDTO;
import com.e_kampoeng.model.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TagsRTService {
    Tags save(TagsDTO tagsDTO);
    Tags findById(Long id);
    Page<Tags> findAllWithPagination(Pageable pageable);
    Map<String, Boolean> delete(Long id);
    Tags update(Long id, TagsDTO tagsDTO);
}
