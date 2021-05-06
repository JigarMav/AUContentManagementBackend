package com.example.course.dao.Impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.*;
import javax.sql.rowset.serial.SerialException;

import com.example.course.LoggerConfig;
import com.example.course.Queries.Queries;
import com.example.course.dao.TrainingMaterialDao;
import com.example.course.models.TrainingMaterial;
import com.example.course.models.User;
import com.example.course.rowmapper.TrainingMaterialRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;




@Repository
public class TrainingMaterialDaoImpl implements TrainingMaterialDao {


	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//Fetches all old and new materials
	@Override
	public List<TrainingMaterial> getAllMaterial() {
		String query = "SELECT * FROM training_materials";
		
		LoggerConfig.LOGGER.info("Fetched All Training Material.");
		return jdbcTemplate.query(Queries.GET_ALL_TM, new TrainingMaterialRowMapper());
	}
	
	//Fetches only new material
	@Override
	public List<TrainingMaterial> getMaterialActive() {
		String query = "SELECT * FROM training_materials WHERE active_flag = ? AND status = ?";
		
		LoggerConfig.LOGGER.info("Fetched Active Training Material.");
		return jdbcTemplate.query(Queries.GET_TM_ACTIVE, new TrainingMaterialRowMapper(), "Y", "New");
	}

	//Fetches only new material for particular course
	@Override
	public List<TrainingMaterial> getActiveMaterialByCourseID(int id) {
		String query = "SELECT * FROM training_materials WHERE courseId = ? AND active_flag = ?";
		return jdbcTemplate.query(Queries.GET_ACTIVE_TM_BY_COURSEID, new TrainingMaterialRowMapper(), id, "Y");
	}

//gets all materials for courseid
	@Override
	public List<TrainingMaterial> getMaterialByCourseID(int id) {
		String query = "SELECT * FROM training_materials WHERE courseId = ? ";
		return jdbcTemplate.query(Queries.GET_TM_BY_COURSEID, new TrainingMaterialRowMapper(), id);
	}

	//Fetches only new material for particular course
	@Override
	public List<TrainingMaterial> getMaterialByTrainerID(int id) {
		String query = "SELECT * FROM training_materials WHERE trainerId = ? AND active_flag = ?";
		return jdbcTemplate.query(Queries.GET_TM_BY_TRAINERID, new TrainingMaterialRowMapper(), id, "Y");
	}

	@Override
	public void addMaterial(MultipartFile file, int courseId,int trainerId,String trainerName) {
//		System.out.println(file.getName() +"---"+ courseId);
		String prequery = "UPDATE training_materials SET active_flag = ?, status = ? WHERE courseId = ? AND trainerId=? AND active_flag = ?";
		jdbcTemplate.update(prequery, "N","Old",courseId,trainerId,"Y");
		
		String query = "INSERT INTO training_materials(courseId,trainerId,trainerName, fileName,fileType,file,active_flag,status,created_on,last_modified) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			byte[] bytes = file.getBytes();
			Blob blob;
			blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			Timestamp tt = new Timestamp(System.currentTimeMillis());
			jdbcTemplate.update(Queries.CREATE_TM, courseId,trainerId,trainerName,file.getOriginalFilename(),file.getContentType(), blob,"Y","New",tt,tt);
			
			LoggerConfig.LOGGER.info("Added New Material -> " + courseId+" by trainer -"+trainerId);
			
		} catch (Exception e) {
			LoggerConfig.LOGGER.error("Error Adding New Material");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMaterial(int id) {
		String query = "UPDATE training_materials SET active_flag = ?, status = ?,last_modified=NOW() WHERE materialID = ?";
		jdbcTemplate.update(Queries.DELETE_TM, "N","Deleted",id);
		
		LoggerConfig.LOGGER.info("Deleted Material -> " + id);
		
	}

}
