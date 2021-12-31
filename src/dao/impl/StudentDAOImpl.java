package dao.impl;

import dao.custom.StudentDAO;
import db.DbConnection;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.validation.FactoryConfigeration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student course = session.get(Student.class, s);
        session.delete(course);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, s);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Student> allStudents = new ArrayList();
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Student");
        allStudents = (ArrayList<Student>) query.list();
        transaction.commit();
        session.close();
        return allStudents;
    }

    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT sId FROM Student WHERE sId=:id");
        String id1 = (String) query.setParameter("id", id).uniqueResult();
        if (id1!=null){
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT sId FROM Student ORDER BY sId DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s!=null) {
            int newStudentId = Integer.parseInt(s.replace("S", "")) + 1;
            return String.format("S%03d", newStudentId);
        }
        return "S001";
    }

    public String getStudentName(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT sName FROM Student WHERE sId=:id");
        String id1 = (String) query.setParameter("id", id).uniqueResult();
        if (id1!=null){
            return id1;
        }
        transaction.commit();
        session.close();
        return null;
    }
    public static List<Student> searchStudent(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Student> student = session.createQuery("FROM Student WHERE sId LIKE '%" + s + "%' or sName LIKE '%" + s + "%'").list();
        transaction.commit();
        session.close();
        return student;
    }

}