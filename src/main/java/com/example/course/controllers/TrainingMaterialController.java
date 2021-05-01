package com.example.course.controllers;


import java.util.List;

import com.example.course.models.TrainingMaterial;
import com.example.course.services.Impl.TrainingMaterialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("api/material")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class TrainingMaterialController {

	@Autowired
	TrainingMaterialServiceImpl materialService;
	
	@GetMapping(value= "/")
	public List<TrainingMaterial> getAllMaterial() {
		return materialService.getAllMaterial();
	}
	
	@GetMapping(value= "/active")
	public List<TrainingMaterial> getMaterialActive() {
		return materialService.getMaterialActive();
	}
	
	@GetMapping(value= "/{id}")
	public List<TrainingMaterial> getMaterialByCourseID(@PathVariable("id") int id) {
		return materialService.getMaterialByCourseID(id);
	}
	@GetMapping(value= "/trainer/{id}")
	public List<TrainingMaterial> getMaterialByTrainerID(@PathVariable("id") int id) {
		return materialService.getMaterialByTrainerID(id);
	}
	
	@PostMapping(value= "/add")
	@ResponseBody
	public void addMaterial(@RequestParam("file") MultipartFile file,
							@RequestParam("courseId") int courseId,
							@RequestParam("trainerId") int trainerId) {
		System.out.println(file.getName());
		System.out.println(courseId);
		System.out.println(trainerId);
//		int cid = Integer.parseInt(courseId);
//		int tid = Integer.parseInt(trainerId);
		materialService.addMaterial(file, courseId,trainerId);
	}
	
	@DeleteMapping(value= "/delete/{mid}")
	public void deleteMaterial(@PathVariable("mid") int mid) {
		materialService.deleteMaterial(mid);
	}
}
