package pl.polsl.tomasz.walorski;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.polsl.tomasz.walorski.database.DataBaseCRUD;
import pl.polsl.tomasz.walorski.controller.Controller;

/**
 *
 * @author Tomasz Walorski
 * @version 1.1
 */
public class Main {
    /**
     * Main method, which starting application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LabPU");
        DataBaseCRUD db = new DataBaseCRUD(entityManagerFactory.createEntityManager());
        Controller controller = new Controller(db);
        controller.menu();
        db.close();
        entityManagerFactory.close();
    }
}
