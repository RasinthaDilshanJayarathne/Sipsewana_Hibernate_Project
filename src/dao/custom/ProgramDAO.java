package dao.custom;

import entity.Program;

import java.sql.SQLException;

public interface ProgramDAO extends CrudDAO<Program,String> {
    boolean ifCourseExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
}
