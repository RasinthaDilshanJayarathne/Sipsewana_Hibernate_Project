package bo.impl;

import bo.custom.RegisterDetailBO;
import dao.custom.ProgramDAO;
import dao.custom.QueryDAO;
import dao.custom.StudentDAO;
import dao.impl.DAOFactory;
import dto.RegisterDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDetailBOImpl implements RegisterDetailBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public ArrayList<RegisterDetailDTO> getAllDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean ifExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
