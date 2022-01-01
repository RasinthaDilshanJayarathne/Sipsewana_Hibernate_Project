package dao.custom;

import entity.Student;

import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student,String>{
    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    String getStudentName(String id) throws SQLException, ClassNotFoundException;
}
