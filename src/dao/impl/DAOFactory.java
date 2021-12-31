package dao.impl;

import dao.custom.SuperDAO;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case REGISTER:
                return new RegisterDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT,PROGRAM,REGISTER,QUERYDAO
    }
}
