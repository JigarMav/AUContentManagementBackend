package com.example.course.dao;


import java.util.List;

import com.example.course.models.TrainingMaterial;
import org.springframework.web.multipart.MultipartFile;



public interface TrainingMaterialDao {

	public List<TrainingMaterial> getAllMaterial();
	
	public List<TrainingMaterial> getMaterialActive();
	
	public List<TrainingMaterial> getMaterialByCourseID(int id);
	
	public void addMaterial(MultipartFile file, int courseId);
	
	public void deleteMaterial(int id);
}
