package com.example.gymplanner.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	public String saveExercise(Exercise exercise, @RequestParam("fileImage") MultipartFile multipartFile)
			throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		exercise.setPicture(fileName);

		Exercise savedExercise = repository.save(exercise);

		String uploadDir = "./exercise-picture/" + savedExercise.getId();
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Cold not save uploaded file:" + fileName);
		}
		return "redirect:exercises";
	}

	// Delete Exercise
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteExercise(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../exercises";
	}

	// Edit exercise
	@GetMapping("/edit/{id}")
	public String editExercise(@PathVariable(name = "id") Long id, Model model) {
		Optional<Exercise> exercise = repository.findById(id);

		model.addAttribute("exercise", exercise);
		return "editexercise";

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
