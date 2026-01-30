package com.project.peps.recipe.repository;

import com.project.peps.recipe.dto.RecipeFilterDTO;
import com.project.peps.recipe.model.Category;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipehasingredient.model.RecipeHasIngredient;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class RecipeSpecification {

    // --------------------------------------------------------
    // Brique 1 : Le filtre global (Qui va assembler toutes les briques)
    // --------------------------------------------------------
    public static Specification<Recipe> filterBy(RecipeFilterDTO filter) {
        return Specification
                .where(hasCountry(filter.getCountryId()))
                .and(hasCategory(filter.getCategory()))
                .and(hasAllIngredients(filter.getIncludedIngredientIds())) // "ET" logique
                .and(doesNotHaveIngredients(filter.getExcludedIngredientIds()))
                .and(distinct()); // Pour éviter les doublons
    }

    // --------------------------------------------------------
    // Brique 2 : Pays (Simple)
    // --------------------------------------------------------
    private static Specification<Recipe> hasCountry(Long countryId) {
        return (root, query, cb) -> {
            if (countryId == null) return null;
            return cb.equal(root.get("country").get("id"), countryId);
        };
    }

    // --------------------------------------------------------
    // Brique 3 : Catégorie (Simple)
    // --------------------------------------------------------
    private static Specification<Recipe> hasCategory(Category category) {
        return (root, query, cb) -> {
            if (category == null) return null;
            return cb.equal(root.get("category"), category);
        };
    }

    // --------------------------------------------------------
    // Brique 4 : Ingrédients Inclus (Complexe : IL FAUT TOUS LES AVOIR)
    // --------------------------------------------------------
    private static Specification<Recipe> hasAllIngredients(List<Long> ingredientIds) {
        return (root, query, cb) -> {
            if (CollectionUtils.isEmpty(ingredientIds)) return null;

            // On crée une sous-requête pour trouver les IDs des recettes valides
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<RecipeHasIngredient> subRoot = subquery.from(RecipeHasIngredient.class);

            subquery.select(subRoot.get("recipe").get("id"))
                    .where(subRoot.get("ingredient").get("id").in(ingredientIds))
                    .groupBy(subRoot.get("recipe").get("id"))
                    // Astuce : On compte si la recette contient AUTANT d'ingrédients que la liste demandée
                    .having(cb.equal(cb.count(subRoot.get("recipe").get("id")), (long) ingredientIds.size()));

            return root.get("id").in(subquery);
        };
    }

    // --------------------------------------------------------
    // Brique 5 : Ingrédients Exclus (Complexe : NE DOIT PAS CONTENIR)
    // --------------------------------------------------------
    private static Specification<Recipe> doesNotHaveIngredients(List<Long> ingredientIds) {
        return (root, query, cb) -> {
            if (CollectionUtils.isEmpty(ingredientIds)) return null;

            // Sous-requête : Trouve les recettes qui ONT ces ingrédients interdits
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<RecipeHasIngredient> subRoot = subquery.from(RecipeHasIngredient.class);

            subquery.select(subRoot.get("recipe").get("id"))
                    .where(subRoot.get("ingredient").get("id").in(ingredientIds));

            // Filtre final : L'ID de la recette NE DOIT PAS ÊTRE dans cette liste
            return cb.not(root.get("id").in(subquery));
        };
    }
    
    // --------------------------------------------------------
    // Brique 6 : Distinct (Nettoyage)
    // --------------------------------------------------------
    private static Specification<Recipe> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }
}