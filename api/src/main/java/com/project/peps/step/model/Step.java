package com.project.peps.step.model;

import com.project.peps.recipe.model.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    // Fetch LAZY = il ne charge la recette que lorsque c'est nécessaire càd si on execute step.getRecipe().getName() par exemple (normalement pas besoin sur les steps)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    // Redéfinition de la méthode toString pour éviter les problèmes de récursion infinie
    @Override
    public String toString() {
        return "Step{" +
               "id=" + id +
               ", stepNumber=" + stepNumber +
               ", instruction='" + instruction + "'" +
               ", imageUrl='" + imageUrl + "'" +
               // Pas de recipe ici pour éviter la récursion infinie (stack overflow) + problèmes de performance
               '}';
    }
}
