package com.example.recipeAPI.controller;

import com.example.recipeAPI.dto.RecipeIngredientJoin;
import com.example.recipeAPI.model.Recipe;
import com.example.recipeAPI.model.Ingredient;
import com.example.recipeAPI.repository.IngredientRepository;
import com.example.recipeAPI.repository.RecipeRepository;
import com.example.recipeAPI.controller.RecipeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class RecipeIngredientController {
   @Autowired
   RecipeRepository recipeRepository;
   @Autowired
   IngredientRepository ingredientRepository;

    @PostMapping("/test")
    public Recipe recipe(@RequestBody RecipeIngredientJoin request){
       return recipeRepository.save(request.getRecipe());
    }

    @GetMapping("/findAllTest")
    public List<Recipe> findAllOrders(){
        return recipeRepository.findAll();
    }

    @GetMapping("/getInfo")
    public List<RecipeIngredientJoin> getJoinInformation(){
        return recipeRepository.getJoinInformation();
    }
}
