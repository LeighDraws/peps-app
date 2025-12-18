package com.project.peps.tag.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.peps.tag.dto.TagRequest;
import com.project.peps.tag.dto.TagResponse;
import com.project.peps.tag.model.Tag;

@Component
public class TagMapper {

    public Tag toEntity(TagRequest tagRequest) {
        if (tagRequest == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }

    public TagResponse toResponse(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagResponse tagResponse = new TagResponse();
        tagResponse.setId(tag.getId());
        tagResponse.setName(tag.getName());
        return tagResponse;
    }

    public List<TagResponse> toResponseList(List<Tag> tags) {
        if (tags == null) {
            return null;
        }
        return tags.stream()
                .map(this::toResponse)
                .toList();
    }
}