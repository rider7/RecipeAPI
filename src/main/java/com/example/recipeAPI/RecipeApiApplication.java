package com.example.recipeAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Entry point of application
//Annotation used for to enable auto-configuration, component scan and allow import of additional configuration classes
@SpringBootApplication
@EnableJpaAuditing //Enables auditing via annotation configuration (needed for use of @CreatedDate and @LastModifiedDate annotations)

public class RecipeApiApplication {
	//Call to launch the spring application
	public static void main(String[] args) {
		SpringApplication.run(RecipeApiApplication.class, args);
	}

}
