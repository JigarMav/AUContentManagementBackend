package com.example.course.services;

import com.example.course.models.Trainer;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



public interface TrainerService {
	
	public List<Trainer> getAllTrainers();
	
	public List<Trainer> getTrainerByCourseID(int id);
	
	public void addTrainer(Trainer trainer);
	void addTrainerAfterCourse(int tid,int cid);
	
	public void deleteTrainer(int tid,int cid);
}
