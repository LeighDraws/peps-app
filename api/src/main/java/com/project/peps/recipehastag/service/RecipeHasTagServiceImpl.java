package com.project.peps.recipehastag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.service.RecipeService;
import com.project.peps.tag.model.Tag;
import com.project.peps.tag.repository.TagRepository;
import com.project.peps.recipehastag.dto.RecipeHasTagRequest;
import com.project.peps.recipehastag.model.RecipeHasTag;
import com.project.peps.recipehastag.repository.RecipeHasTagRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;

@Service
public class RecipeHasTagServiceImpl implements RecipeHasTagService {

    private final RecipeHasTagRepository recipeHasTagRepository;
    private final RecipeService recipeService;
    private final TagRepository tagRepository;

    public RecipeHasTagServiceImpl(RecipeHasTagRepository recipeHasTagRepository,
                                   RecipeService recipeService,
                                   TagRepository tagRepository) {
        this.recipeHasTagRepository = recipeHasTagRepository;
        this.recipeService = recipeService;
        this.tagRepository = tagRepository;
    }

    @Override
    public RecipeHasTag addTagToRecipe(RecipeHasTagRequest request) {
        if (recipeHasTagRepository.existsByRecipeIdAndTagId(request.getRecipeId(), request.getTagId())) {
            throw new IllegalArgumentException("Tag is already associated with this recipe");
        }

        Recipe recipe = recipeService.findRecipeById(request.getRecipeId());
        Tag tag = tagRepository.findById(request.getTagId())
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", request.getTagId()));

        RecipeHasTag recipeHasTag = RecipeHasTag.builder()
                .recipe(recipe)
                .tag(tag)
                .build();

        return recipeHasTagRepository.save(recipeHasTag);
    }

    @Override
    public void removeTagFromRecipe(Long id) {
        if (!recipeHasTagRepository.existsById(id)) {
            throw new ResourceNotFoundException("RecipeHasTag", "id", id);
        }
        recipeHasTagRepository.deleteById(id);
    }

    @Override
    public List<RecipeHasTag> getTagsByRecipeId(Long recipeId) {
        return recipeHasTagRepository.findByRecipeId(recipeId);
    }
}
