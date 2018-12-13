package com.gpch.login.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gpch.login.model.Task;
import com.gpch.login.model.User;
import com.gpch.login.service.TaskService;
import com.gpch.login.service.UserService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/addTask")
	public String taskForm(String email, Model model, HttpSession session) {
		
		session.setAttribute("email", email);
		model.addAttribute("task", new Task());
		return "admin/taskForm";
	}
	
	@PostMapping("/addTask")
	public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
			return "admin/taskForm";
		}
		String email= (String) session.getAttribute("email");
		taskService.addTask(task, userService.findUserByEmail(email));
	
		return "redirect:/users";
	}
	
//	@GetMapping("/tasks")
//	public String allTasks(Model model) {
//		model.addAttribute("tasks", taskService.findUserTask(user));
//		return "admin/taskList";
//	}
	
	@GetMapping("/tasks")
	public String allTasks(Model model, Principal principal) {
		
		String email= principal.getName();
		User user = userService.findUserByEmail(email);
		
		model.addAttribute("tasks", taskService.findUserTask(user));
		return "admin/taskList";
	}
	
}
