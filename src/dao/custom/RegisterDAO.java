package dao.custom;

import entity.Register;

import java.sql.SQLException;

public interface RegisterDAO extends CrudDAO<Register,String> {
    boolean ifRegExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;

}
