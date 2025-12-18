package com.project.peps.tag.service;

import java.util.List;

import com.project.peps.tag.dto.TagRequest;
import com.project.peps.tag.model.Tag;

public interface TagService {

    List<Tag> findAll();

    Tag findById(Long id);

    Tag save(Tag tag);

    Tag update(Long id, TagRequest tagRequest);

    void deleteById(Long id);
}