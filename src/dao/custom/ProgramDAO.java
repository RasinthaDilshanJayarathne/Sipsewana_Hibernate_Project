package dao.custom;

import entity.Program;

import java.sql.SQLException;
import java.util.List;

public interface ProgramDAO extends CrudDAO<Program,String> {
    boolean ifCourseExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    List<String> getCourses() throws SQLException, ClassNotFoundException;
    Program getCourseDetails(String pName) throws SQLException, ClassNotFoundException;
}
