package com.project.peps.step.service;

import com.project.peps.shared.exception.ResourceNotFoundException;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.repository.RecipeRepository;
import com.project.peps.step.model.Step;
import com.project.peps.step.repository.StepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StepServiceImpl implements StepService {

    private final StepRepository stepRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public List<Step> findAll() {
        return stepRepository.findAll();
    }

    @Override
    public Optional<Step> findById(Long id) {
        return stepRepository.findById(id);
    }

    @Override
    public Step save(Step step) {
        // Ensure the associated recipe exists if step is new or recipe is being changed
        if (step.getRecipe() != null && step.getRecipe().getId() != null) {
            Long recipeId = step.getRecipe().getId();
            Recipe recipe = recipeRepository.findById(recipeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
            step.setRecipe(recipe); // Re-associate to ensure it's a managed entity
        }
        return stepRepository.save(step);
    }

    @Override
    public void deleteById(Long id) {
        if (!stepRepository.existsById(id)) {
            throw new ResourceNotFoundException("Step not found with id: " + id);
        }
        stepRepository.deleteById(id);
    }

    @Override
    public List<Step> findByRecipeId(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
        return stepRepository.findByRecipe_Id(recipeId);
    }
}
