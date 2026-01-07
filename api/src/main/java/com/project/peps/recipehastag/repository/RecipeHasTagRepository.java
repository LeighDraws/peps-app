package com.project.peps.recipehastag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.peps.recipehastag.model.RecipeHasTag;

@Repository
public interface RecipeHasTagRepository extends JpaRepository<RecipeHasTag, Long> {
    
    List<RecipeHasTag> findByRecipeId(Long recipeId);
    
    boolean existsByRecipeIdAndTagId(Long recipeId, Long tagId);
}
