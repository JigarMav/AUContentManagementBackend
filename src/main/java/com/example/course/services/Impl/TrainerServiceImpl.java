package com.example.course.services.Impl;

import java.util.List;

import com.example.course.dao.Impl.TrainerDaoImpl;
import com.example.course.models.Trainer;
import com.example.course.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	TrainerDaoImpl trainerdao;
	
	@Override
	public List<Trainer> getAllTrainers() {
		return trainerdao.getAllTrainers();
	}

	@Override
	public List<Trainer> getTrainerByCourseID(int id) {
		return trainerdao.getTrainerByCourseID(id);
	}

	@Override
	public void addTrainer(Trainer trainer) {
		trainerdao.addTrainer(trainer);
		
	}

	@Override
	public void deleteTrainer(int tid, int cid) {
		trainerdao.deleteTrainer(tid, cid);
		
	}

}
