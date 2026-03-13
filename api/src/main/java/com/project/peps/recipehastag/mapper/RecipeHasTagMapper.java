package com.project.peps.recipehastag.mapper;

import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipehastag.dto.RecipeHasTagResponse;
import com.project.peps.recipehastag.model.RecipeHasTag;
import com.project.peps.tag.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeHasTagMapper {

    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "tag.id", target = "tagId")
    @Mapping(source = "tag.name", target = "tagName")
    RecipeHasTagResponse toResponse(RecipeHasTag entity);

    List<RecipeHasTagResponse> toResponseList(List<RecipeHasTag> list);
}
