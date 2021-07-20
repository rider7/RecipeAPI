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
@Table(name = "ingredient") //Used to provide the details of the table that this entity will be mapped to
@EntityListeners(AuditingEntityListener.class) // Specifies the callback listener classes to be used for an entity or mapped superclass.
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, //Used for Serializing and Deserializing Java objects to and from JSON
        allowGetters = true)
public class Ingredient implements Serializable { //Implements used so Note class can access Serializable interface methods. Note: Class can implement multiple interfaces at a time.
    @Id //Used to define the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Used to define the primary key generation strategy. In the above case, we have declared the primary key to be an Auto Increment field
    private Long ingredient_id;

    @ManyToMany(mappedBy = "ingredientSet") //Used to show the many-to-many relationship
    Set<Recipe> recipeSet; //Collection used to contain elements of both classes

    @NotBlank //Used to validate that the annotated field is not null or empty
    private String ingredient_name;

    @NotBlank //Used to validate that the annotated field is not null or empty
    private int amount;

    @Column(nullable = false, updatable = false) //Used to define the properties of the column that will be mapped to the annotated field
    @Temporal(TemporalType.TIMESTAMP) //Used with java.util.Date and java.util.Calendar classes. It converts the date and time values from Java Object to compatible database type and vice versa.
    @CreatedDate //Used to track created date
    private Date createdAt;

    @Column(nullable = false) //Used to define the properties of the column that will be mapped to the annotated field
    @Temporal(TemporalType.TIMESTAMP) //Used with java.util.Date and java.util.Calendar classes. It converts the date and time values from Java Object to compatible database type and vice versa.
    @LastModifiedDate //Used to track modified date
    private Date updatedAt;

    public Long getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Long ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
