package com.project.peps.step.service;

import com.project.peps.step.model.Step;

import java.util.List;
import java.util.Optional;

public interface StepService {
    List<Step> findAll();
    Optional<Step> findById(Long id);
    Step save(Step step);
    void deleteById(Long id);
    List<Step> findByRecipeId(Long recipeId);
}
