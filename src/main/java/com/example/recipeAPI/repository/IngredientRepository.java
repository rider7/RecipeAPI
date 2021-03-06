package com.example.recipeAPI.repository;

import com.example.recipeAPI.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Used to annotate a repository for storing, updating, and extracting data (aka a DAO)
//Interface used for abstraction and multiple inheritance
//Extends keyword allows NoteRepository to use the attributes and methods from JpaRepository like save(), findOne(), findAll(), count(), delete()
public interface IngredientRepository extends JpaRepository<Ingredient, Long> { //List with class and primary key


}
