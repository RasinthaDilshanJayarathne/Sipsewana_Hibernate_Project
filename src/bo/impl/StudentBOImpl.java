package bo.impl;

import bo.custom.StudentBO;
import dao.custom.StudentDAO;
import dao.impl.DAOFactory;
import dto.StudentDTO;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        ArrayList<Student> all = studentDAO.getAll();
        for (Student student : all) {
            allStudents.add(new StudentDTO(student.getsId(),student.getsName(),student.getNic(),student.getAddress(),
                    student.getContact()));
        }
        return allStudents;
    }

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.add(new Student(studentDTO.getsId(),studentDTO.getsName(),studentDTO.getNic(),studentDTO.getAddress(),
                studentDTO.getContact()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(studentDTO.getsId(),studentDTO.getsName(),studentDTO.getNic(),studentDTO.getAddress(),
                studentDTO.getContact()));
    }

    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.ifStudentExist(id);
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNewID();
    }
}
