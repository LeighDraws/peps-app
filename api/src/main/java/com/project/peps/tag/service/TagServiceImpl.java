package com.project.peps.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.shared.exception.ResourceNotFoundException;
import com.project.peps.tag.dto.TagRequest;
import com.project.peps.tag.model.Tag;
import com.project.peps.tag.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Long id, TagRequest tagRequest) {
        Tag existingTag = findById(id);
        existingTag.setName(tagRequest.getName());
        return tagRepository.save(existingTag);
    }

    @Override
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }
}
