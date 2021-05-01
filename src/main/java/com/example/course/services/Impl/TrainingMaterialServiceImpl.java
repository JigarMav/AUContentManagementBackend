package com.example.course.services.Impl;

import java.util.List;

import com.example.course.dao.Impl.TrainingMaterialDaoImpl;
import com.example.course.models.TrainingMaterial;
import com.example.course.services.TrainingMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class TrainingMaterialServiceImpl implements TrainingMaterialService {

	@Override
	public List<TrainingMaterial> getMaterialByTrainerID(int id) {
		return materialdao.getMaterialByTrainerID(id);
	}

	@Autowired
	TrainingMaterialDaoImpl materialdao;
	
	@Override
	public List<TrainingMaterial> getAllMaterial() {
		return materialdao.getAllMaterial();
	}

	@Override
	public List<TrainingMaterial> getMaterialByCourseID(int id) {
		return materialdao.getMaterialByCourseID(id);
	}

	@Override
	public List<TrainingMaterial> getActiveMaterialByCourseID(int id){
		return materialdao.getActiveMaterialByCourseID(id);
	}
	@Override
	public void addMaterial(MultipartFile file, int courseId,int trainerId,String trainerName) {
		materialdao.addMaterial(file, courseId,trainerId,trainerName);
		
	}

	@Override
	public void deleteMaterial(int id) {
		materialdao.deleteMaterial(id);
		
	}

	@Override
	public List<TrainingMaterial> getMaterialActive() {
		return materialdao.getMaterialActive();
	}

}
