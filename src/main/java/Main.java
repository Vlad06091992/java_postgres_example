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

            Task eat = new Task();
            eat.setName("поесть");

            Task report = new Task();
            report.setName("подготовить отчет");

            Task call = new Task();
            call.setName("созвониться с заказчиком");

            Task training = new Task();
            training.setName("пройти обучение по безопасности");

            session.save(eat);
            session.save(report);
            session.save(call);
            session.save(training);


            Office office = new Office();
            office.setAddress("адрес офиса 1");
            office.setName("главный офис");
            session.save(office);

            Employee vasya = new Employee();
            vasya.setActive(true);
            vasya.setTask(training);
            vasya.setTask(call);
            vasya.setTask(eat);
            vasya.setName("vasya");
            vasya.setOffice(office);
            vasya.setPosition("уборщик");
            vasya.setSalary(40000.0);


            Employee vanya = new Employee();
            vanya.setActive(true);
            vanya.setName("vanya");
            vanya.setTask(report);
            vanya.setOffice(office);
            vanya.setPosition("охранник");
            vanya.setSalary(60000.0);


            Employee kolya = new Employee();
            kolya.setActive(true);
            kolya.setName("kolya");
            kolya.setTask(training);
            kolya.setTask(call);
            kolya.setTask(eat);
            kolya.setOffice(office);
            kolya.setPosition("водитель");
            kolya.setSalary(90000.0);

            session.save(vasya);
            session.save(vanya);
            session.save(kolya);
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
