package bo.custom;

import dto.ProgramDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgramBO extends SuperBO {
    ArrayList<ProgramDTO> getAllProgram() throws SQLException, ClassNotFoundException;

    boolean addProgram(ProgramDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean updateProgram(ProgramDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean ifProgramExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteProgram(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
