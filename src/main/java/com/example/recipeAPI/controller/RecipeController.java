package com.example.recipeAPI.controller;

import com.example.recipeAPI.exception.ResourceNotFoundException;
import com.example.recipeAPI.model.Recipe;
import com.example.recipeAPI.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController // Used to indicate that the return value of a method should be used as the response body of the request.
// Annotation allows us to simplify creation of RESTful web services. Differs from @Controller annotation since no @Responsebody is required
@RequestMapping("/api") // Annotation for mapping web requests onto methods in request-handling classes with flexible method signatures.
// /api declares the url that apis start with
public class RecipeController { //Controller used to process incoming requests, invoke business logic, update the model and returns the view

    @Autowired //Spring will inject RecipeRepository when RecipeController is created. No need for getters and setters.
    RecipeRepository recipeRepository;

    // Get All Recipes
    @GetMapping("/recipes") //Annotation for mapping HTTP GET requests onto specific handler methods. /recipes is url path
    public List<Recipe> getAllRecipes(){ //Calls theJpaRepository method findAll() to retrieve notes from the db and returns as a list
        return recipeRepository.findAll();
    }

    // Create a new Recipe
    @PostMapping("/recipes")
    public Recipe createRecipe(@Valid @RequestBody Recipe recipe){ //Maps HTTP POST requests. annotation makes sure that the request body is valid. Recipe model shows @RecipeBlank for name and desc, if it recipe object has neither it will return bad request error
        return recipeRepository.save(recipe); //Returns Recipe entity
    }

    // Get a Single Recipe
    @GetMapping("/recipes/{id}")
    public Recipe getRecipeById(@PathVariable(value = "id") Long recipeId) { //PathVariable binds with method parameter id
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", recipeId)); //Used to get the value of the optional instance if present, if not throws the custom exception from resourceNotFoundException
    }

    // Update a Recipe
    @PutMapping("/recipes/{id}") //Maps HTTP PUT requests.
    public Recipe updateRecipe(@PathVariable(value = "id") Long recipeId,
                           @Valid @RequestBody Recipe recipeDetails) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        recipe.setRecipe_name(recipeDetails.getRecipe_name());
        recipe.setRecipe_desc(recipeDetails.getRecipe_desc());

        Recipe updatedRecipe = recipeRepository.save(recipe); //Assigns the value of the invoked save() method on the recipe instance
        return updatedRecipe;
    }

    // Delete a Recipe
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable(value = "id") Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        recipeRepository.delete(recipe);

        return ResponseEntity.ok().build();
    }




}
