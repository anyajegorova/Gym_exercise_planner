package com.example.gymplanner;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	// Exposing the user exercise pictures directory
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path exerciseUploadDir = Paths.get("./exercise-picture");
		String exerciseUploadPath = exerciseUploadDir.toFile().getAbsolutePath();
		//Registering resource handler for images
		registry.addResourceHandler("/exercise-picture/**").addResourceLocations("file:/" + exerciseUploadPath + "/");

	}

}
