package com.example.course.dao.Impl;

import java.util.List;

import com.example.course.LoggerConfig;
import com.example.course.dao.TrainerDao;
import com.example.course.models.Trainer;
import com.example.course.rowmapper.TrainerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public class TrainerDaoImpl implements TrainerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Trainer> getAllTrainers() {
		//String query = "SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID";
		String query = "SELECT * FROM courses JOIN (SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID) AS trainer ON courses.courseID = trainer.courseID ";
		
		LoggerConfig.LOGGER.info("Fetched All Trainers.");
		return jdbcTemplate.query(query, new TrainerRowMapper());
	}

	@Override
	public List<Trainer> getTrainerByCourseID(int id) {
		String query = "SELECT * FROM courses JOIN " + 
						"(SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID) AS trainer " +
						"ON courses.courseID = trainer.courseID WHERE courses.courseID = ?"; 
				
		return jdbcTemplate.query(query, new TrainerRowMapper(), id);
	}

	@Override
	public void addTrainer(int tid, int cid) {
		String query = "INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";
		try {
			jdbcTemplate.update(query, tid,cid);
			LoggerConfig.LOGGER.info("New Trainer Added -> " + tid);
		}
		catch(Exception e) {
			LoggerConfig.LOGGER.error("Error Adding New Trainer -> " + tid);
			e.getMessage();
		}
	}

	@Override
	public void deleteTrainer(int tid, int cid) {
		String query = "DELETE FROM trainers WHERE trainerID = ? AND courseID = ?";
		jdbcTemplate.update(query, tid, cid);
		
		LoggerConfig.LOGGER.info("Deleted Trainer -> " + tid);
	}

}
