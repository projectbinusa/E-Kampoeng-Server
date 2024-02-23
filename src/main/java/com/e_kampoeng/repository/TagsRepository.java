package com.e_kampoeng.repository;

import com.e_kampoeng.model.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends CrudRepository<Tags, Integer> {
    Tags findById(long id);
}
