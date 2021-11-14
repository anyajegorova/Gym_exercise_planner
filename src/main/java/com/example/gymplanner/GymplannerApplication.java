package com.example.gymplanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class GymplannerApplication {
	private static final Logger log = LoggerFactory.getLogger(GymplannerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GymplannerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ExerciseRepository repository, TypeRepository trepository, MuscleGroupRepository mrepository, UserRepository urepository) {
		return (args) -> {
			
			//Creating and saving exercise types
			Type type1 = new Type("Warmup");
			Type type2 = new Type("Strength");
			Type type3 = new Type("Cardio");
			Type type4 = new Type("Cooldown");
			
			trepository.save(type1);
			trepository.save(type2);
			trepository.save(type3);
			trepository.save(type4);	
			
			//Creating and saving muscle group types
			MuscleGroup muscle1 = new MuscleGroup("Chest");
			MuscleGroup muscle2 = new MuscleGroup("Back");
			MuscleGroup muscle3 = new MuscleGroup("Abs");
			MuscleGroup muscle4 = new MuscleGroup("Shoulders");
			MuscleGroup muscle5 = new MuscleGroup("Biceps");
			MuscleGroup muscle6 = new MuscleGroup("Legs");
			
			mrepository.save(muscle1);
			mrepository.save(muscle2);
			mrepository.save(muscle3);
			mrepository.save(muscle4);
			mrepository.save(muscle5);
			mrepository.save(muscle6);			
			
			//Creating and saving exercises
			Exercise exercise1 = new Exercise("Chest press", "10x3", 5.00, trepository.findByName("Strength").get(0), mrepository.findByName("Chest").get(0));
			Exercise exercise2 = new Exercise("Ab crunches", "15x3", 0.00, trepository.findByName("Warmup").get(0), mrepository.findByName("Abs").get(0));
			Exercise exercise3 = new Exercise("Leg extension", "12x3", 10.00, trepository.findByName("Strength").get(0), mrepository.findByName("Legs").get(0));
			Exercise exercise4 = new Exercise("Bodyweight lunges", "10x3", 15.00, trepository.findByName("Strength").get(0), mrepository.findByName("Legs").get(0));
			Exercise exercise5 = new Exercise("Stretch", "5x3", 0.00, trepository.findByName("Cooldown").get(0), mrepository.findByName("Legs").get(0));
			Exercise exercise6 = new Exercise("Seated row", "20x3", 7.00, trepository.findByName("Warmup").get(0), mrepository.findByName("Back").get(0));
			
			repository.save(exercise1);
			repository.save(exercise2);
			repository.save(exercise3);
			repository.save(exercise4);
			repository.save(exercise5);
			repository.save(exercise6);
			
			//Creating users
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all exercises");
			for (Exercise exercise : repository.findAll()) {
				log.info(exercise.toString());
			}
			
		};
	}
}
