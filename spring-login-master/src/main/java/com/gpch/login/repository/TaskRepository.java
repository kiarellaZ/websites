package com.gpch.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpch.login.model.Task;
import com.gpch.login.model.User;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUser(User user);

}
