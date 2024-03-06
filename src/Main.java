import DAO.PersonDAO;
import DAO.PersonDAOImpl;
import config.PostgresSQLConfig;
import model.Person;

public class Main {
    public static void main(String[] args) {
        PostgresSQLConfig.initializeDatabase();
        PersonDAO personDAO = new PersonDAOImpl();
        Person newPerson = new Person("John Doe", 30);
        Person newPerson1 = new Person("Eric", 25);
        Person newPerson2 = new Person("Loko", 10);
        Person newPerson3 = new Person("Doe", 20);
        Person newPerson4 = new Person("johnny", 22);
        Person newPerson5 = new Person("beatrice", 36);
        Person newPerson6 = new Person("parker", 19);


        personDAO.addPerson(newPerson);
        personDAO.addPerson(newPerson1);
        personDAO.addPerson(newPerson2);
        personDAO.addPerson(newPerson3);
        personDAO.addPerson(newPerson4);
        personDAO.addPerson(newPerson5);
        personDAO.addPerson(newPerson6);

    }
}
