package com.example.course.dao;


import com.example.course.models.Trainer;

import java.util.List;



public interface TrainerDao {

	public List<Trainer> getAllTrainers();

    int getTrainerById(int id);

    public List<Trainer> getTrainerByCourseID(int id);
	
	 void addTrainer(Trainer trainer);
	void addTrainerAfterCourse(int tid,int cid);
	public void deleteTrainer(int tid, int cid);
}
