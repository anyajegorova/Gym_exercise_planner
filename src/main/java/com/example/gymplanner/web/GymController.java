package com.example.gymplanner.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gymplanner.domain.Exercise;
import com.example.gymplanner.domain.ExerciseRepository;

@Controller
public class GymController {
	@Autowired
	private ExerciseRepository repository;
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}

	// Show all exercises
	@RequestMapping(value = "/exercises")
	public String exercises(Model model) {
		model.addAttribute("exercises", repository.findAll());
		return "exercises";
	}

	// Add new gym exercises
	@RequestMapping(value = "/add")
	public String addExercise(Model model) {
		model.addAttribute("exercise", new Exercise());
		return "addexercise";
	}

	// Save new exercise
	@RequestMapping(value = "/save")
	public String saveExercise(Exercise exercise) {
		repository.save(exercise);
		return "redirect:exercises";
	}
	
	// 

}
