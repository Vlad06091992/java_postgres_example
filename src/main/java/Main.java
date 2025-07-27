import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession();

            session.beginTransaction(); // начинаем транзакцию

            Employee vasya = new Employee();
            vasya.setActive(true);
            vasya.setName("vasya");
            vasya.setOffice("офис1");
            vasya.setPosition("уборщик");
            vasya.setSalary(40000.0);

            session.save(vasya);
            String id = vasya.getId();


            Employee newEmployee = session.get(Employee.class,id);

            newEmployee.setName("Валера");
            session.save(newEmployee);




            session.delete(newEmployee);
            session.getTransaction().commit(); // фиксируем транзакцию

            session.close(); // закрываем сессию
            sessionFactory.close(); // закрываем фабрику
            Logger.getLogger("test").info("Log4j is working");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
