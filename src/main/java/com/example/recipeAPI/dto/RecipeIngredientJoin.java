package com.example.recipeAPI.dto;

import com.example.recipeAPI.model.Ingredient;
import com.example.recipeAPI.model.Recipe;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeIngredientJoin{
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }
}
