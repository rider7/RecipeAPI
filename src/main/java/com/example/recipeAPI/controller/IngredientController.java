package com.example.recipeAPI.controller;

import com.example.recipeAPI.exception.ResourceNotFoundException;
import com.example.recipeAPI.model.Ingredient;
import com.example.recipeAPI.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController //Used to indicate that the return value of a method should be used as the response body of the request. Annotation allows us to simplify creation of RESTful web services. Differs from @Controller annotation since no @Responsebody is required
@RequestMapping("/api") //Annotation for mapping web requests onto methods in request-handling classes with flexible method signatures. /api declares the url that apis start with
public class IngredientController { //Controller used to process incoming requests, invoke business logic, update the model and returns the view.

    @Autowired //Spring will inject IngredientRepository when IngredientController is created. No need for getters and setters.
            IngredientRepository ingredientRepository;

    // Get ALL ingredients
    @GetMapping("/ingredients") //Annotation for mapping HTTP GET requests onto specific handler methods. /ingredients is url path
    public List<Ingredient> getAllIngredients(){ //Calls theJpaRepository method findAll() to retrieve notes from the db and returns as a list
        return ingredientRepository.findAll();
    }

    // Create a new ingredient
    @PostMapping("/ingredients")
    public Ingredient createIngredient(@Valid @RequestBody Ingredient ingredient){ //Maps HTTP POST requests. annotation makes sure that the request body is valid. Ingredient model shows @NoteBlank for title and content, if it ingredient object has neither it will return bad request error
        return ingredientRepository.save(ingredient); //Returns Ingredient entity
    }

    // Get a Single Ingredient
    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredientById(@PathVariable(value = "id") Long ingredientId) { //PathVariable binds with method parameter id
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient", "id", ingredientId)); //Used to get the value of the optional instance if present, if not throws the custom exception from resourceNotFoundException
    }

    // Update a ingredient
    @PutMapping("/ingredients/{id}") //Maps HTTP PUT requests.
    public Ingredient updateIngredient(@PathVariable(value = "id") Long ingredientId,
                           @Valid @RequestBody Ingredient ingredientDetails) {

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient", "id", ingredientId));

        ingredient.setIngredient_name(ingredientDetails.getIngredient_name());
        ingredient.setIngredient_id(ingredientDetails.getIngredient_id());

        Ingredient updatedIngredient = ingredientRepository.save(ingredient); //Assigns the value of the invoked save() method on the ingredient instance
        return updatedIngredient;
    }

    // Delete a Ingredient
    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable(value = "id") Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient", "id", ingredientId));

        ingredientRepository.delete(ingredient);

        return ResponseEntity.ok().build();
    }
}
