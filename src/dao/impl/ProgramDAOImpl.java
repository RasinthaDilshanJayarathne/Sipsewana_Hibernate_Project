package dao.impl;

import dao.custom.ProgramDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.validation.FactoryConfigeration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean add(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(program);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program program = session.get(Program.class, s);
        session.delete(program);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(program);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Program search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program program = session.get(Program.class, s);
        transaction.commit();
        session.close();
        return program;
    }

    @Override
    public ArrayList<Program> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Program> allCourses = new ArrayList();
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Program ");
        allCourses = (ArrayList<Program>) query.list();
        transaction.commit();
        session.close();
        return allCourses;
    }

    @Override
    public boolean ifCourseExist(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT pId FROM Program WHERE pId=:id");
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
        Query query = session.createSQLQuery("SELECT pId FROM Program ORDER BY pId DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s!=null) {
            int newCourseId = Integer.parseInt(s.replace("C", "")) + 1;
            return String.format("C%03d", newCourseId);
        }
        return "C001";
    }

    public List<String> getCourses() throws SQLException, ClassNotFoundException {
        ArrayList<String> allCourses = new ArrayList();
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT pName FROM Program");
        allCourses = (ArrayList<String>) query.list();
        transaction.commit();
        session.close();
        return allCourses;
    }

    public Program getCourseDetails(String pName) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Program WHERE pName=:pName");
        Program course = (Program) query.setParameter("pName", pName).uniqueResult();
        transaction.commit();
        session.close();
        return course;
    }
}
