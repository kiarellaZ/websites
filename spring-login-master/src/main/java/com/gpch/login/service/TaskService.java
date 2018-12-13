package com.gpch.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.login.model.Task;
import com.gpch.login.model.User;
import com.gpch.login.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public List<Task> findUserTask(User user) {
		
		return taskRepository.findByUser(user);
	}
	
	public List<Task> allTasks() {
		
		return taskRepository.findAll();
	}

	
	
}
