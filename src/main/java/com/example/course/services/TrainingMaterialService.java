package com.example.course.services;

import java.util.List;

import com.example.course.models.TrainingMaterial;
import org.springframework.web.multipart.MultipartFile;


public interface TrainingMaterialService {

	public List<TrainingMaterial> getAllMaterial();
	
	public List<TrainingMaterial> getMaterialActive();

	 List<TrainingMaterial> getActiveMaterialByCourseID(int id);
	public List<TrainingMaterial> getMaterialByCourseID(int id);
	public List<TrainingMaterial> getMaterialByTrainerID(int id);

	public void addMaterial(MultipartFile file, int courseId,int trainerId, String trainerName);
	
	public void deleteMaterial(int id);
}
