package com.example.course.controllers;

import java.util.List;

import com.example.course.models.Trainer;
import com.example.course.services.Impl.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/trainer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class TrainerController {
	
	@Autowired
	TrainerServiceImpl trainerService;
	
	@GetMapping(value= "/")
	public List<Trainer> getAllTrainers() {
		return trainerService.getAllTrainers();
	}
	
	@GetMapping(value= "/{id}")
	public List<Trainer> getTrainerByCourseID(@PathVariable("id") int id) {
		return trainerService.getTrainerByCourseID(id);
	}

	@PostMapping(value= "/add")
	public void addTrainer(@RequestBody Trainer trainer) {
		  trainerService.addTrainer(trainer);
	}

	@PostMapping(value= "/add/afterCourse")
	@ResponseBody
	public void addTrainerAfterCourse(@RequestParam("tid") int tid, @RequestParam("cid") int cid) {
		trainerService.addTrainerAfterCourse(tid, cid);

	}

	
	@DeleteMapping(value= "/delete/{tid}/{cid}")
	public void deleteTrainer(@PathVariable("tid") int tid, @PathVariable("cid") int cid) {
		trainerService.deleteTrainer(tid,cid);
	}
}
