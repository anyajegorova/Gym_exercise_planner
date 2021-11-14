package com.example.gymplanner.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.gymplanner.domain.SignUpForm;
import com.example.gymplanner.domain.User;
import com.example.gymplanner.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repository;

	@RequestMapping(value = "signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			String pwd = signupForm.getPassword();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hashPwd = bc.encode(pwd);

			User newUser = new User();
			newUser.setPasswordHash(hashPwd);
			newUser.setUsername(signupForm.getUsername());
			newUser.setRole("USER");

			if (repository.findByUsername(signupForm.getUsername()) == null) {
				repository.save(newUser);

			} else {
				bindingResult.rejectValue("username", "err.username", "This username already exists");
				return "signup";
			}
		} else {
			return "signup";
		}

		return "redirect:/login";
	}
}
