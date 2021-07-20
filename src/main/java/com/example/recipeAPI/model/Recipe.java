package com.example.recipeAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity //Used to mark the class as a persistent Java class.
@Table(name = "recipe") //Used to provide the details of the table that this entity will be mapped to
@EntityListeners(AuditingEntityListener.class) // Specifies the callback listener classes to be used for an entity or mapped superclass.
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, //Used for Serializing and Deserializing Java objects to and from JSON
        allowGetters = true)
public class Recipe implements Serializable { //Implements used so Note class can access Serializable interface methods. Note: Class can implement multiple interfaces at a time.
    @Id //Used to define the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Used to define the primary key generation strategy. In the above case, we have declared the primary key to be an Auto Increment field
    private Long recipe_id;
    @ManyToMany //Used to show the many-to-many relationship
    @JoinTable(
            name = "recipe_join_table",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id" ))
    Set<Ingredient> ingredientSet; //Collection used to contain elements of both classes

    @NotBlank //Used to validate that the annotated field is not null or empty
    private String recipe_name;

    @NotBlank //Used to validate that the annotated field is not null or empty
    private String recipe_desc;

    @Column(nullable = false, updatable = false) //Used to define the properties of the column that will be mapped to the annotated field
    @Temporal(TemporalType.TIMESTAMP) //Used with java.util.Date and java.util.Calendar classes. It converts the date and time values from Java Object to compatible database type and vice versa.
    @CreatedDate //Used to track created date
    private Date createdAt;

    @Column(nullable = false) //Used to define the properties of the column that will be mapped to the annotated field
    @Temporal(TemporalType.TIMESTAMP) //Used with java.util.Date and java.util.Calendar classes. It converts the date and time values from Java Object to compatible database type and vice versa.
    @LastModifiedDate //Used to track modified date
    private Date updatedAt;

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_desc() {
        return recipe_desc;
    }

    public void setRecipe_desc(String recipe_desc) {
        this.recipe_desc = recipe_desc;
    }
}
