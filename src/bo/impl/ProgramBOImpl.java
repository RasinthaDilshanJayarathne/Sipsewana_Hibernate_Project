package bo.impl;

import bo.custom.ProgramBO;
import dao.custom.ProgramDAO;
import dao.impl.DAOFactory;
import dto.ProgramDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public ArrayList<ProgramDTO> getAllProgram() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addProgram(ProgramDTO studentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateProgram(ProgramDTO studentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean ifProgramExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteProgram(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
