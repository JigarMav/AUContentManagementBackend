package com.example.course.Queries;

public class Queries {
    Queries()
    {

    }
//    User Queries
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String GET_USERS_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE userID = ?";
    public static final String CREATE_USER = "INSERT INTO users (userName,email,userLocation) VALUES (?,?,?)";
    public static final String DELETE_USER = "DELETE FROM users WHERE userID = ?";


//    TRAINER QUERIES
    public static final String GET_ALL_TRAINERS ="SELECT * FROM courses JOIN (SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID) AS trainer ON courses.courseID = trainer.courseID ";
    public static final String GET_TRAINER_BY_ID ="SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID where trainers.trainerID= ? limit 1";
    public static final String GET_TRAINER_BY_COURSEID  = "SELECT * FROM courses JOIN " +
            "(SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID) AS trainer " +
            "ON courses.courseID = trainer.courseID WHERE courses.courseID = ?";

    public static final String CREATE_TRAINER ="INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";
    public static final String CREATE_TRAINER_AFTER_COURSE ="INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";
    public static final String DELETE_TRAINER =  "DELETE FROM trainers WHERE trainerID = ? AND courseID = ?";

//    COURSE QUERIES
    public static final String GET_ALL_COURSES ="SELECT * FROM courses";
    public static final String GET_COURSES_BY_TRAINER  = "SELECT courses.courseID,creatorID,courseDesc,courseLocation, courses.courseName ,coursePrerequisites ,courseSkills, last_modified" +
            "   FROM courses" +
            "   left join trainers" +
            "   on  trainers.courseID= courses.courseID" +
            "   where trainerID=?";
    public static final String GET_COURSES_BY_CREATOR = "SELECT * FROM courses WHERE creatorID=?";
    public static final String GET_COURSES_BY_SUBSCRIPTION = "select courses.courseID,creatorID, courseDesc,courseLocation, courses.courseName ,coursePrerequisites ,courseSkills, last_modified from courses\n" +
            "left join subscription s on courses.courseID = s.courseID\n" +
            "where userID=?";
    public static final String GET_COURSES_BY_NAME ="SELECT * FROM courses WHERE courseName = ? limit 1";
    public static final String CREATE_COURSE = "INSERT INTO courses (creatorID,courseName, courseDesc, courseSkills,coursePrerequisites,courseLocation,last_modified)" +
            " VALUES (?,?,?,?,?,?,NOW())";
    public static final String UPDATE_COURSE = "UPDATE courses SET courseName = ?, courseDesc = ?, courseSkills = ?,coursePrerequisites = ?,courseLocation = ?,last_modified=NOW()" +
            " WHERE courseID = ?";
    public static final String DELETE_COURSE ="DELETE FROM courses WHERE courseID = ?";

//    SUBSCRIPTION QUERIES
    public static final String GET_ALL_SUB ="SELECT * FROM subscription";
    public static final String GET_ALL_EMAIL_FOR_COURSE = "SELECT * FROM subscription WHERE courseID=?";
    public static final String CREATE_SUBSCRIPTION = "INSERT INTO subscription(userID, courseID,email) VALUES (?,?,?)";
    public static final String DELETE_SUB ="DELETE FROM subscription WHERE userID = ? AND courseID = ?";

//    TRAINING MATERIAL QUERIES
    public static final String GET_ALL_TM ="SELECT * FROM training_materials";
    public static final String GET_TM_ACTIVE ="SELECT * FROM training_materials WHERE active_flag = ? AND status = ?";
    public static final String GET_ACTIVE_TM_BY_COURSEID  ="SELECT * FROM training_materials WHERE courseId = ? AND active_flag = ?";
    public static final String GET_TM_BY_COURSEID ="SELECT * FROM training_materials WHERE courseId = ? ";
    public static final String GET_TM_BY_TRAINERID ="SELECT * FROM training_materials WHERE trainerId = ? AND active_flag = ?";
    public static final String CREATE_TM = "INSERT INTO training_materials(courseId,trainerId,trainerName, fileName,fileType,file,active_flag,status,created_on,last_modified) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE_TM = "UPDATE training_materials SET active_flag = ?, status = ?,last_modified=NOW() WHERE materialID = ?";
}
