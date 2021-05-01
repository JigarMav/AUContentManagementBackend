package com.example.course.dao;


import java.util.List;

import com.example.course.models.TrainingMaterial;
import org.springframework.web.multipart.MultipartFile;



public interface TrainingMaterialDao {

	List<TrainingMaterial> getAllMaterial();
	
	List<TrainingMaterial> getMaterialActive();
	
	List<TrainingMaterial> getMaterialByCourseID(int id);
	public List<TrainingMaterial> getActiveMaterialByCourseID(int id);
	List<TrainingMaterial> getMaterialByTrainerID(int id) ;

	void addMaterial(MultipartFile file, int courseId,int trainerId,String trainerName) ;
	
	void deleteMaterial(int id);
}
