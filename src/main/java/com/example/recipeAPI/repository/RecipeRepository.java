package com.example.recipeAPI.repository;

import com.example.recipeAPI.dto.RecipeIngredientJoin;
import com.example.recipeAPI.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository //Used to annotate a repository for storing, updating, and extracting data (aka a DAO)
//Interface used for abstraction and multiple inheritance
//Extends keyword allows NoteRepository to use the attributes and methods from JpaRepository like save(), findOne(), findAll(), count(), delete()
public interface RecipeRepository extends JpaRepository<Recipe, Long> { //List with class and primary key
    @Query(value="SELECT r FROM Recipe r JOIN r.ingredient i WHERE r.id = i.recipe ", nativeQuery = true)

    //@Query(value="SELECT new com.example.recipeAPI.dto.RecipeIngredientJoin(r.recipe_name , i.ingredient_name) FROM Recipe r JOIN c.ingredients i", nativeQuery = true)
    List<RecipeIngredientJoin> getJoinInformation();
}
