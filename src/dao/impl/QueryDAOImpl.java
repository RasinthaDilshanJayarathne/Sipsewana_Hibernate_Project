package dao.impl;

import dao.custom.QueryDAO;
import dto.CustomDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.validation.FactoryConfigeration;

import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> getAll() {
        ArrayList<CustomDTO> allDetails = new ArrayList();
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT r.RegId,s.sId,s.sName,c.pId,c.pName,r.RegDate FROM Register r INNER JOIN Student s ON r.student=s.sId INNER JOIN Program c ON r.program=c.pId");
        ArrayList<Object[]> details = (ArrayList<Object[]>) query.list();
        transaction.commit();
        session.close();
        for (Object[] temp:details) {
            allDetails.add(new CustomDTO(
                    (String) temp[0],
                    (String) temp[1],
                    (String) temp[2],
                    (String) temp[3],
                    (String) temp[4],
                    (LocalDate) temp[5]
            ));
        }
        return allDetails;
    }
}
