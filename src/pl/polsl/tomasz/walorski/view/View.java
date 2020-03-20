package pl.polsl.tomasz.walorski.view;

import static java.lang.System.out;
import pl.polsl.tomasz.walorski.model.Club;
import pl.polsl.tomasz.walorski.model.Footballer;

/**
 *
 * @author Tomasz Walorski
 * Methods of this class concerned with display application's output for user 
 */
public class View {
    /**
     * Method show main menu's view.
     */
    public void mainMenuView(){
        out.flush();
        out.println("CRUD APPLICATION MENU\n");
        out.println("Choose table:");
        out.println("1. Clubs");
        out.println("2. Footballers");
        out.println("\n3. Exit");
    }
    /**
     * Method show club's operation menu view.
     */
    public void clubsMenuView(){
        out.flush();
        out.println("CLUB TABLE\n");
        out.println("Choose operation:");
        out.println("1. Create club");
        out.println("2. Read club");
        out.println("3. Update club");
        out.println("4. Delete club");
        out.println("\n5. Exit");
    }
    /**
     * Method show footballer's operation menu view.
     */
    public void footballerMenuView(){
        out.flush();
        out.println("FOOTBALLER TABLE\n");
        out.println("Choose operation:");
        out.println("1. Create footballer");
        out.println("2. Read footballer");
        out.println("3. Update footballer");
        out.println("4. Delete footballer");
        out.println("\n5. Exit");
    }
    /**
     * Method display club's searching menu view.
     * @param operation Name of operation, which will be performed on founded object.
     */
    public void clubsQueryMenuView(String operation){
        out.flush();
        out.println("CHOOSE CLUB TO " + operation + "\n");
        out.println("Find club by:");
        out.println("1. ID");
        out.println("2. Full name");
        out.println("3. Short name");
        out.println("4. Founded date");
        out.println("5. City");
        out.println("\n6. Show every rows of table");
    }
    /**
     * Method display footballer's searching menu view.
     * @param operation Name of operation, which will be performed on founded object.
     */
    public void footballerQueryMenuView(String operation){
        out.flush();
        out.println("CHOOSE FOOTBALLER TO " + operation + "\n");
        out.println("Find footballer by:");
        out.println("1. ID");
        out.println("2. Name");
        out.println("3. Club number");
        out.println("4. Birth date");
        out.println("5. Position");
        out.println("6. Club");
        out.println("\n7. Show every rows of table");
    }
    /**
     * Viewer used for setter's method.
     * @param fieldName Name of firld to set.
     */
    public void setFieldView(String fieldName){
        out.println("Enter " + fieldName + ":");
    }
    /**
     * Method display warning, if data has wrong type.
     * @param message Content of warning.
     */
    public void incorrectTypeWarning(String message){
        out.println("Wrong input. " + message + " Set data again.");
    }
    /**
     * Method display club object.
     * @param club Chosen objcet to display.
     */
    public void showClub(Club club){
        out.println(club.toString());
    }
    /**
     * Method display footballer object.
     * @param footballer Chosen objcet to display.
     */    
    public void showFootballer(Footballer footballer){
        out.println(footballer.toString());
    }
    /**
     * Method display message for user.
     * @param message Content of message.
     */
    public void printMessage(String message){
        out.print(message);
    }
    /**
     * Method display menu of choosing club's field to update.
     */
    public void chooseClubFieldMenu(){
        out.flush();
        out.println("CHOOSE CLUB'S FIELD TO UPDATE \n");
        out.println("1. Full name");
        out.println("2. Short name");
        out.println("3. Founded date");
        out.println("4. City");
        out.println("\n5. Exit");
    }
    /**
     * Method display menu of choosing footballer's field to update.
     */
    public void chooseFootballerFieldMenu(){
        out.flush();
        out.println("CHOOSE FOOTBALLER'S FIELD TO UPDATE \n");
        out.println("1. Name");
        out.println("2. Club number");
        out.println("3. Birth date");
        out.println("4. Position");
        out.println("5. Club");
        out.println("\n6. Exit");
    }
}
