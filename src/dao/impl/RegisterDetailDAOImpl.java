package dao.impl;

import dao.custom.RegisterDetailDAO;
import entity.RegisterDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDetailDAOImpl implements RegisterDetailDAO {
    @Override
    public boolean add(RegisterDetail registerDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RegisterDetail registerDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RegisterDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<RegisterDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
