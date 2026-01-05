package com.project.peps.step.controller;

import com.project.peps.shared.exception.ResourceNotFoundException;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.repository.RecipeRepository;
import com.project.peps.step.dto.StepReorderRequest;
import com.project.peps.step.dto.StepRequest;
import com.project.peps.step.dto.StepResponse;
import com.project.peps.step.mapper.StepMapper;
import com.project.peps.step.model.Step;
import com.project.peps.step.service.StepService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/steps")
@AllArgsConstructor
public class StepController {

    private final StepService stepService;
    private final StepMapper stepMapper;
    private final RecipeRepository recipeRepository; // Inject RecipeRepository

    @GetMapping
    public ResponseEntity<List<StepResponse>> getAllSteps() {
        List<StepResponse> steps = stepService.findAll().stream()
                .map(stepMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(steps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepResponse> getStepById(@PathVariable Long id) {
        StepResponse step = stepService.findById(id)
                .map(stepMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Step not found with id: " + id));
        return ResponseEntity.ok(step);
    }

    @PostMapping
    public ResponseEntity<StepResponse> createStep(@Valid @RequestBody StepRequest stepRequest) {
        Recipe recipe = recipeRepository.findById(stepRequest.getRecipeId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + stepRequest.getRecipeId()));
        Step step = stepService.save(stepMapper.toEntity(stepRequest, recipe));
        return new ResponseEntity<>(stepMapper.toDto(step), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StepResponse> updateStep(@PathVariable Long id, @Valid @RequestBody StepRequest stepRequest) {
        Step existingStep = stepService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Step not found with id: " + id));

        Recipe recipe = recipeRepository.findById(stepRequest.getRecipeId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + stepRequest.getRecipeId()));

        stepMapper.updateStepFromDto(stepRequest, existingStep, recipe);
        Step updatedStep = stepService.save(existingStep);
        return ResponseEntity.ok(stepMapper.toDto(updatedStep));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStepById(@PathVariable Long id) {
        stepService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-recipe/{recipeId}")
    public ResponseEntity<List<StepResponse>> getStepsByRecipeId(@PathVariable Long recipeId) {
        List<StepResponse> steps = stepService.findByRecipeId(recipeId).stream()
                .map(stepMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(steps);
    }

    @PutMapping("/reorder")
    public ResponseEntity<List<StepResponse>> reorderSteps(@Valid @RequestBody List<StepReorderRequest> reorderRequests) {
        List<Step> updatedSteps = stepService.reorderSteps(reorderRequests);
        List<StepResponse> stepResponses = updatedSteps.stream()
                .map(stepMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stepResponses);
    }
}
