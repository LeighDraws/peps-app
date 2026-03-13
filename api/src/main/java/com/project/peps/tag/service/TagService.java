package com.project.peps.tag.service;

import com.project.peps.tag.model.Tag;
import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag findById(Long id);

    Tag save(Tag tag);

    void deleteById(Long id);
}