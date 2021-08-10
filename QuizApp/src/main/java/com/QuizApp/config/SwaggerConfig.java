package com.QuizApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

/**
 * Configuration class for Swagger Rest api documentation
 *
 */
@Configuration
public class SwaggerConfig {

	public static final String QUIZ_APP_CONTROLLER = "Quiz Application";

	@Bean
	public OpenAPI customOpenAPI() {
		final Info info = new Info().title("Quiz Application API").description("Quiz Application")
				.version("1.0.0");

		return new OpenAPI().components(new Components()).addTagsItem(createTag(QUIZ_APP_CONTROLLER, ""))
				// Other tags here...
				.info(info);
	}

	private Tag createTag(String name, String description) {
		final Tag tag = new Tag();
		tag.setName(name);
		tag.setDescription(description);
		return tag;
	}

}