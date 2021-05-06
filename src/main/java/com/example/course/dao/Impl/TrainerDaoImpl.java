package com.example.course.dao.Impl;

import java.util.List;

import com.example.course.LoggerConfig;
import com.example.course.Queries.Queries;
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
		return jdbcTemplate.query(Queries.GET_ALL_TRAINERS, new TrainerRowMapper());
	}

	@Override
	public int getTrainerById(int id) {
		Trainer t = null;
		String query = "SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID where trainers.trainerID= ? limit 1";

		LoggerConfig.LOGGER.info("Fetched Trainer."+id);

		t = jdbcTemplate.queryForObject(Queries.GET_TRAINER_BY_ID, new TrainerRowMapper(),id);
		if(t==null)
		{
			return -1;
		}
		return id;
	}

	@Override
	public List<Trainer> getTrainerByCourseID(int id) {
		String query = "SELECT * FROM courses JOIN " + 
						"(SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID) AS trainer " +
						"ON courses.courseID = trainer.courseID WHERE courses.courseID = ?"; 
				
		return jdbcTemplate.query(Queries.GET_TRAINER_BY_COURSEID, new TrainerRowMapper(), id);
	}

	@Override
	public void addTrainer(Trainer trainer) {
		String query = "INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";

		try {
			jdbcTemplate.update(Queries.CREATE_TRAINER, trainer.getTrainerID(),trainer.getCourseID());
			LoggerConfig.LOGGER.info("New Trainer Added -> " + trainer.getTrainerID());
		}
		catch(Exception e) {
			LoggerConfig.LOGGER.error("Error Adding New Trainer -> " + trainer.getTrainerID());
			e.getMessage();
		}
	}

	@Override
	public void addTrainerAfterCourse(int tid, int cid) {
		String query = "INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";
		try {
			jdbcTemplate.update(Queries.CREATE_TRAINER_AFTER_COURSE, tid,cid);
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
		jdbcTemplate.update(Queries.DELETE_TRAINER, tid, cid);
		
		LoggerConfig.LOGGER.info("Deleted Trainer -> " + tid);
	}

}
