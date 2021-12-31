package bo.impl;

import bo.custom.ProgramBO;
import dao.custom.ProgramDAO;
import dao.impl.DAOFactory;
import dto.ProgramDTO;
import entity.Program;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public ArrayList<ProgramDTO> getAllProgram() throws SQLException, ClassNotFoundException {
        ArrayList<ProgramDTO> allCourses = new ArrayList<>();
        ArrayList<Program> all = programDAO.getAll();
        for (Program program : all) {
            allCourses.add(new ProgramDTO(program.getpId(),program.getpName(),program.getDuration(),program.getFee()));
        }
        return allCourses;
    }

    @Override
    public boolean addProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.add(new Program(programDTO.getpId(),programDTO.getpName(),programDTO.getDuration(),programDTO.getFee()));
    }

    @Override
    public boolean updateProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.update(new Program(programDTO.getpId(),programDTO.getpName(),programDTO.getDuration(),programDTO.getFee()));
    }

    @Override
    public boolean ifProgramExist(String id) throws SQLException, ClassNotFoundException {
        return programDAO.ifCourseExist(id);
    }

    @Override
    public boolean deleteProgram(String id) throws SQLException, ClassNotFoundException {
        return programDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return programDAO.generateNewID();
    }
}
