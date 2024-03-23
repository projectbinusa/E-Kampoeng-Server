package com.e_kampoeng.service;

import com.e_kampoeng.dto.TagsDTO;
import com.e_kampoeng.model.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TagsService {
    Tags save(TagsDTO tagsDTO);
//    List<Tags> findAll();
    Page<Tags> findAllWithPagination(Pageable pageable);
    Tags findById(Long id);
    Tags update(Long id, TagsDTO tagsDTO);
    Map<String, Boolean> delete(Long id);
}
