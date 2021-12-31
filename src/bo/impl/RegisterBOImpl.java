package bo.impl;

import bo.custom.RegisterBO;
import dao.custom.ProgramDAO;
import dao.custom.QueryDAO;
import dao.custom.StudentDAO;
import dao.impl.DAOFactory;
import dto.CustomDTO;
import dto.RegisterDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterBOImpl implements RegisterBO {

    @Override
    public boolean registerDetails(RegisterDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<CustomDTO> getAllDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean ifExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
