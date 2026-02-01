package com.project.peps.tag.mapper;

import com.project.peps.tag.dto.TagRequest;
import com.project.peps.tag.dto.TagResponse;
import com.project.peps.tag.model.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toEntity(TagRequest tagRequest);

    TagResponse toResponse(Tag tag);

    List<TagResponse> toResponseList(List<Tag> tags);
}
