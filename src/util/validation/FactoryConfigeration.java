package util.validation;

import entity.Program;
import entity.Register;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfigeration {
    private  static FactoryConfigeration factoryConfiguration;

    private static SessionFactory sessionFactory;

    private FactoryConfigeration(){
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration.addAnnotatedClass(Program.class).addAnnotatedClass(Student.class).addAnnotatedClass(Register.class);
        sessionFactory = configuration.setProperties(properties).buildSessionFactory();
    }

    public  static FactoryConfigeration getInstance(){
        if (factoryConfiguration == null){
            factoryConfiguration = new FactoryConfigeration();
        }
        return factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
