package com.project.peps.step.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.project.peps.recipe.model.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "recipe")
@Table(name = "steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
    private Long id;

    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

    @Column(name = "instruction", columnDefinition = "TEXT", nullable = false)
    private String instruction;

    @Column(name = "image_url")
    private String imageUrl;

    // Fetch LAZY = il ne charge la recette que lorsque c'est nécessaire càd si on
    // execute step.getRecipe().getName() par exemple (normalement pas besoin sur
    // les steps)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
