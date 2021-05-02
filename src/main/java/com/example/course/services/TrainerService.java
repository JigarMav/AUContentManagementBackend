package com.example.course.services;

import com.example.course.models.Trainer;

import java.util.List;



public interface TrainerService {
	
	public List<Trainer> getAllTrainers();
	
	public List<Trainer> getTrainerByCourseID(int id);
	
	public void addTrainer(int tid,int cid);
	
	public void deleteTrainer(int tid,int cid);
}
