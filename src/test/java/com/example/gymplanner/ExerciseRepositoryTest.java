package com.example.gymplanner;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.gymplanner.domain.Exercise;
import com.example.gymplanner.domain.ExerciseRepository;
import com.example.gymplanner.domain.MuscleGroup;

import com.example.gymplanner.domain.Type;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExerciseRepositoryTest {
	@Autowired
	private ExerciseRepository repository;

	// Testing find functionality
	@Test
	public void findByNameReturnsExercise() {
		List<Exercise> exercises = repository.findByName("Stretch");
		assertThat(exercises.get(0).getId()).isEqualTo(170);
	}

	// Testing create functionality
	@Test
	public void createNewExercise() {
		Type warmup = new Type();
		MuscleGroup back = new MuscleGroup();
		Exercise exercise = new Exercise("Jumps", "50x2", 0.00, warmup, back);
		repository.save(exercise);
		assertThat(exercise.getId()).isNotNull();
	}

	// Testing delete functionality
	@Test
	public void deleteExercise() {
		repository.deleteById((long) 169);
		assertThat(repository.findByName("Bodyweight lunges")).isEmpty();

	}

}
