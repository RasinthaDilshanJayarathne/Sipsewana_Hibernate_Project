package bo.custom;

import dto.RegisterDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterDetailBO extends SuperBO{
    ArrayList<RegisterDetailDTO> getAllDetails() throws SQLException, ClassNotFoundException;

    boolean ifExist(String id) throws SQLException, ClassNotFoundException;
}
