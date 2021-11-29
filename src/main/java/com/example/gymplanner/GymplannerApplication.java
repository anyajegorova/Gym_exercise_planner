package com.example.gymplanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.gymplanner.domain.Exercise;
import com.example.gymplanner.domain.ExerciseRepository;
import com.example.gymplanner.domain.MuscleGroup;
import com.example.gymplanner.domain.MuscleGroupRepository;
import com.example.gymplanner.domain.Type;
import com.example.gymplanner.domain.TypeRepository;
import com.example.gymplanner.domain.User;
import com.example.gymplanner.domain.UserRepository;

@SpringBootApplication
public class GymplannerApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GymplannerApplication.class);
	}

	private static final Logger log = LoggerFactory.getLogger(GymplannerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GymplannerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ExerciseRepository repository, TypeRepository trepository,
			MuscleGroupRepository mrepository, UserRepository urepository) {
		return (args) -> {

			log.info("fetch all exercises");
			for (Exercise exercise : repository.findAll()) {
				log.info(exercise.toString());
			}

		};
	}
}
