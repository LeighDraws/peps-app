package com.project.peps.tag.mapper;

import com.project.peps.tag.dto.TagRequest;
import com.project.peps.tag.dto.TagResponse;
import com.project.peps.tag.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    Tag toEntity(TagRequest tagRequest);

    TagResponse toResponse(Tag tag);

    void updateEntityFromRequest(TagRequest request, @MappingTarget Tag entity);

    List<TagResponse> toResponseList(List<Tag> tags);
}
