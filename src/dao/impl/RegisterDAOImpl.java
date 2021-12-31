package dao.impl;

import dao.custom.RegisterDAO;
import entity.Register;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public boolean add(Register register) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Register register) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Register search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Register> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean ifRegExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
