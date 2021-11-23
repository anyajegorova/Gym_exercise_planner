package com.example.gymplanner.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gymplanner.domain.Exercise;
import com.example.gymplanner.domain.ExerciseRepository;
import com.example.gymplanner.domain.MuscleGroupRepository;
import com.example.gymplanner.domain.TypeRepository;

@Controller
public class GymController {
	@Autowired
	private ExerciseRepository repository;
	@Autowired
	private MuscleGroupRepository mrepository;
	@Autowired
	private TypeRepository trepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Show all exercises
	@RequestMapping("/exercises")
	public String exercises(Model model) {
		model.addAttribute("exercises", repository.findAll());
		return "exercises";
	}

	// Add new gym exercises
	@RequestMapping(value = "/add")
	public String addExercise(Model model) {
		model.addAttribute("exercise", new Exercise());
		model.addAttribute("muscleGroups", mrepository.findAll());
		model.addAttribute("types", trepository.findAll());
		return "addexercise";
	}

	// Save new exercise
	@RequestMapping(value = "/save")
	public String saveExercise(Exercise exercise) {
		repository.save(exercise);
		return "redirect:exercises";
	}

	// Delete Exercise
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteExercise(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../exercises";
	}

	// REST getting all exercises
	@RequestMapping(value = "/allexercises", method = RequestMethod.GET)
	public @ResponseBody List<Exercise> exerciseRest() {
		return (List<Exercise>) repository.findAll();
	}

	// REST getting exercise by id
	@RequestMapping(value = "/exercise/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Exercise> findExerciseRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

}
