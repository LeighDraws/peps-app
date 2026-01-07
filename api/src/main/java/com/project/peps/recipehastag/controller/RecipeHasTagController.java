package com.project.peps.recipehastag.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.peps.recipehastag.dto.RecipeHasTagRequest;
import com.project.peps.recipehastag.dto.RecipeHasTagResponse;
import com.project.peps.recipehastag.mapper.RecipeHasTagMapper;
import com.project.peps.recipehastag.model.RecipeHasTag;
import com.project.peps.recipehastag.service.RecipeHasTagService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recipe-tags")
@CrossOrigin(origins = "*")
public class RecipeHasTagController {

    private final RecipeHasTagService recipeHasTagService;
    private final RecipeHasTagMapper recipeHasTagMapper;

    public RecipeHasTagController(RecipeHasTagService recipeHasTagService,
                                  RecipeHasTagMapper recipeHasTagMapper) {
        this.recipeHasTagService = recipeHasTagService;
        this.recipeHasTagMapper = recipeHasTagMapper;
    }

    @PostMapping
    public ResponseEntity<RecipeHasTagResponse> addTagToRecipe(@Valid @RequestBody RecipeHasTagRequest request) {
        RecipeHasTag savedEntity = recipeHasTagService.addTagToRecipe(request);
        return new ResponseEntity<>(recipeHasTagMapper.toResponse(savedEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTagFromRecipe(@PathVariable Long id) {
        recipeHasTagService.removeTagFromRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<RecipeHasTagResponse>> getTagsByRecipe(@PathVariable Long recipeId) {
        List<RecipeHasTag> list = recipeHasTagService.getTagsByRecipeId(recipeId);
        return ResponseEntity.ok(recipeHasTagMapper.toResponseList(list));
    }
}
