package com.project.peps.tag.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.peps.tag.dto.TagRequest;
import com.project.peps.tag.dto.TagResponse;
import com.project.peps.tag.mapper.TagMapper;
import com.project.peps.tag.model.Tag;
import com.project.peps.tag.service.TagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.findAll();
        return ResponseEntity.ok(tagMapper.toResponseList(tags));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable(value = "id") Long id) {
        Tag tag = tagService.findById(id);
        return ResponseEntity.ok(tagMapper.toResponse(tag));
    }

    @PostMapping
    public ResponseEntity<TagResponse> createTag(@RequestBody TagRequest tagRequest) {
        Tag tag = tagMapper.toEntity(tagRequest);
        Tag createdTag = tagService.save(tag);
        return new ResponseEntity<>(tagMapper.toResponse(createdTag), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTag(@PathVariable Long id, @RequestBody TagRequest tagRequest) {
        Tag updatedTag = tagService.update(id, tagRequest);
        return ResponseEntity.ok(tagMapper.toResponse(updatedTag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable(value = "id") Long id) {
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
