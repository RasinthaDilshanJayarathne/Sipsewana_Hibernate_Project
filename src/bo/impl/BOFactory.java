package bo.impl;

import bo.custom.SuperBO;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAM:
                return new StudentBOImpl();
            case REGISTER_DETAIL:
                return new RegisterDetailBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        STUDENT, PROGRAM, REGISTER_DETAIL
    }
}
