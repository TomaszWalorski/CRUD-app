package pl.polsl.tomasz.walorski.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import pl.polsl.tomasz.walorski.database.DataBaseCRUD;
import pl.polsl.tomasz.walorski.model.Club;
import pl.polsl.tomasz.walorski.model.Footballer;
import pl.polsl.tomasz.walorski.emun.types.MenuState;
import pl.polsl.tomasz.walorski.emun.types.Operation;
import pl.polsl.tomasz.walorski.view.View;

/**
 *
 * @author Tomasz Walorski
 * Controller class supports user interface. This class operates on entity class ans view's methods, and also takes user input.
 */
public class Controller {
    /**
     * DataBaseCRUD field for making every operation on datbase.
     */
    private DataBaseCRUD database;
    /**
     * Viewer field to display information for user.
     */
    private View viewer = new View();
    /**
     * Scanner field to take user input.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * TypeController field used for checking user input.
     */
    private TypeController typeController = new TypeController();
    /**
     * State field to determine what menu is currently open.
     */
    private MenuState state;
    /**
     * Operation field used to inform what is currently performed.
     */
    private Operation operation;
    /**
     * Constructor set mainmenu's state to MenuState field and initialise DataBaseCRUD field.
     * @param database DataBaseCRUD to set.
     */
    public Controller(DataBaseCRUD database) {
        this.database = database;
        this.state = MenuState.MAINMENU;
    }
    /**
     * Method perform main loop of application.
     */
    public void menu(){
        while(state != MenuState.EXIT)
        switch(state){
            case MAINMENU:
                mainMenu();
                break;
            case CLUBS:
                clubsMenu();
                break;
            case FOOTBALLERS:
                footballerMenu();
                break;
        }
    }
    /**
     * Method supports main menu.
     */
    private void mainMenu(){
        viewer.mainMenuView();
        String userInput = scanner.nextLine();
        switch(userInput){
            case "1":
                state = MenuState.CLUBS;
                break;
            case "2":
                state = MenuState.FOOTBALLERS;
                break;
            case "3":
                state = MenuState.EXIT;
                break;
        }
    }
    /**
     * Method support club's menu of CRUD operation.
     */
    private void clubsMenu(){
        viewer.clubsMenuView();
        String userInput = scanner.nextLine();
        switch(userInput){
            case "1":
                database.create(new Club(userSetterString("full name"), userSetterString("short name"), 
                userSetterDate("founded Date"), userSetterString("city")));
                break;
            case "2":
                operation = Operation.READ;
                List<Club> clubs = clubsQueryMenu();
                if(clubs != null){
                    for(Club club : clubs){
                        viewer.showClub(club);
                    }
                }
                viewer.printMessage("Press any key to continue.");
                scanner.nextLine();
                break;
            case "3":
                operation = Operation.UPDATE;
                List<Club> clubsToUpdate = clubsQueryMenu();
                int indexToUpdate = chooseClub(clubsToUpdate);
                if(indexToUpdate == clubsToUpdate.size()){
                    for(Club club : clubsToUpdate){
                        database.update(chooseClubField(club));
                    }
                } else if(indexToUpdate > -1 && indexToUpdate < clubsToUpdate.size()){
                    database.update(chooseClubField(clubsToUpdate.get(indexToUpdate)));
                }
                break;
            case "4":
                operation = Operation.DETETE;
                List<Club> clubsToDelete = clubsQueryMenu();
                int indexToDelete = chooseClub(clubsToDelete);
                if(indexToDelete == clubsToDelete.size()){
                    for(Club club : clubsToDelete){
                        database.delete(club);
                    }
                } else if(indexToDelete > -1 && indexToDelete < clubsToDelete.size()){
                    database.delete(clubsToDelete.get(indexToDelete));
                }
                break;
            case "5":
                state = MenuState.MAINMENU;
                break;
            default:
                break;
        }
    }
    /**
     * Method supports footballer's menu for CRUD operations.
     */
    private void footballerMenu(){
        viewer.footballerMenuView();
        String userInput = scanner.nextLine();
        switch(userInput){
            case "1":
                if(database.readAllClubs().isEmpty()){
                    viewer.printMessage("Create some club, before creation of footballer.");
                } else
                database.create(new Footballer(userSetterString("full name"), userSetterInteger("club number"), 
                userSetterDate("birth date"), userSetterString("position"), userSetterClubId()));
                break;
            case "2":
                operation = Operation.READ;
                List<Footballer> footballersToRead = footballerQueryMenu();
                if(footballersToRead != null){
                    for(Footballer footballer : footballersToRead){
                        viewer.showFootballer(footballer);
                    }
                }                
                viewer.printMessage("Press any key to continue.");
                scanner.nextLine();
                break;
            case "3":
                operation = Operation.UPDATE;
                List<Footballer> footballersToUpdate = footballerQueryMenu();
                int indexToUpdate = -1;
                if(footballersToUpdate != null){ 
                    indexToUpdate = chooseFootballer(footballersToUpdate);
                    if(indexToUpdate == footballersToUpdate.size()){
                        for(Footballer footballer : footballersToUpdate){
                            database.update(chooseFootballerField(footballer));
                        }
                    } else if(indexToUpdate > -1 && indexToUpdate < footballersToUpdate.size()){
                        database.update(chooseFootballerField(footballersToUpdate.get(indexToUpdate)));
                    }
                } else {
                    viewer.printMessage("No footballers founded.\nPress any key to continue.");
                    scanner.nextLine();
                }
                break;
            case "4":
                operation = Operation.DETETE;
                List<Footballer> footballersToDelete = footballerQueryMenu();
                if(footballersToDelete != null){ 
                    int indexToDelete = chooseFootballer(footballersToDelete);
                    if(indexToDelete == footballersToDelete.size()){
                        for(Footballer footballer : footballersToDelete){
                            database.delete(footballer);
                        }
                    } else if(indexToDelete > -1 && indexToDelete < footballersToDelete.size()){
                        database.delete(footballersToDelete.get(indexToDelete));
                    }
                } else {
                    viewer.printMessage("No footballers founded.\nPress any key to continue.");
                    scanner.nextLine();
                }
                break;
            case "5":
                state = MenuState.MAINMENU;
                break;
            default:
                break;
        }
    }
    /**
     * Method used for finding proper clubs by named query.
     * @return Method return list of result of searching.
     */
    private List<Club> clubsQueryMenu(){
        viewer.clubsQueryMenuView(operation.toString());
        String userInput = scanner.nextLine();
        List<Club> clubs;
        switch(userInput){
            case "1":
                clubs = database.readClubById(userSetterInteger("id"));
                break;
            case "2":
                clubs = database.readClubByName(userSetterString("full name"));
                break;
            case "3":
                clubs = database.readClubByShortName(userSetterString("short name"));
                break;
            case "4":
                clubs = database.readClubByFoundedDate(userSetterDate("Fouded date"));
                break;
            case "5":
                clubs = database.readClubByCity(userSetterString("city"));
                break;
            default:
                clubs = database.readAllClubs();
        }
        return clubs;
    }
    /**
     * Method used for finding proper footballers by named query.
     * @return Method return list of result of searching.
     */
    private List<Footballer> footballerQueryMenu(){
        viewer.footballerQueryMenuView(operation.toString());
        String userInput = scanner.nextLine();
        List<Footballer> footballers = null;
        switch(userInput){
            case "1":
                footballers = database.readFootballerById(userSetterInteger("id"));
                break;
            case "2":
                footballers = database.readFootballerByName(userSetterString("Name"));
                break;
            case "3":
                footballers = database.readFootballerByClubNumber(userSetterInteger("club numer"));
                break;
            case "4":
                footballers = database.readFootballerByBirthDate(userSetterDate("birth date"));
                break;
            case "5":
                footballers = database.readFootballerByPosition(userSetterString("position"));
                break;
            case "6":
                List<Club> clubs = database.readClubByName(userSetterString("club name"));
                for(Club club : clubs){
                    if(club.getFootballerCollection() != null)
                    {
                        for(Footballer footballer : club.getFootballerCollection()){
                            footballers.add(footballer);
                        }
                    }
                }
                break;
            default:
                footballers = database.readAllFootballers();
        }   
        return footballers;
    }
    /**
     * Method supports setting string's field by user.
     * @param fieldName Name of field which get user input.
     * @return Obtained user input.
     */
    private String userSetterString(String fieldName){
        String userInput = "";
        boolean correctInput = false;
        while(!correctInput){
            viewer.setFieldView(fieldName);
            userInput = scanner.nextLine();
            if(typeController.isNotEmpty(userInput)){
                correctInput = true;
            } else {
                viewer.incorrectTypeWarning("Input string is empty.");
            }
        }
        return userInput;    
    }
    /**
     * Method supports setting integer's field by user.
     * @param fieldName Name of field which get user input.
     * @return Obtained user input.
     */
    private int userSetterInteger(String fieldName){
        String userInput;
        int output = 0;
        boolean correctInput = false;
        while(!correctInput){
            viewer.setFieldView(fieldName);
            userInput = scanner.nextLine();
            if(!typeController.isInteger(userInput)){
                viewer.incorrectTypeWarning("Input string is not integer.");
            } else {
                correctInput = true;
                output = Integer.parseInt(userInput);
            }
        }
        return output;    
    }
    /**
     * Method supports setting month's part of date's field by user.
     * @return Obtained user input.
     */    
    private int userSetterMonth(){
        String userInput;
        int output = 0;
        boolean correctInput = false;
        while(!correctInput){
            viewer.setFieldView("Month");
            userInput = scanner.nextLine();
            if(!typeController.isMonth(userInput)){
                viewer.incorrectTypeWarning("Input data is incorrect for month's fild.");
            } else {
                correctInput = true;
                output = Integer.parseInt(userInput);
            }
        }
        return output;    
    }
    /**
     * Method supports setting day's part of date's field by user.
     * @param month Month value used to check if day value is correct.
     * @param year Year value used to check if day value is correct.
     * @return Obtained user input.
     */         
    private int userSetterDay(int month, int year){
        String userInput;
        int output = 0;
        boolean correctInput = false;
        while(!correctInput){
            viewer.setFieldView("day");
            userInput = scanner.nextLine();
            if(!typeController.isDay(userInput, month, typeController.isLeapYear(year))){
                viewer.incorrectTypeWarning("Input data is incorrect for day's field.");
            } else {
                correctInput = true;
                output = Integer.parseInt(userInput);
            }
        }
        return output;    
    }
    /**
     * Method supports setting date's field by user.
     * @param fieldName Name of field which get user input.
     * @return Obtained user input.
     */  
    private Date userSetterDate(String fieldName){
        Date date = new Date();
        int year = 0, month = 0, day = 0;
        
        boolean correctInput = false;
        while(!correctInput){
            viewer.setFieldView(fieldName);
            year = userSetterInteger("Year");
            month = userSetterMonth();
            day = userSetterDay(month, year);
            date = new Date(year - 1900, month - 1, day);
            if(typeController.isDate(date)){
                correctInput = true;
            } else {
                viewer.incorrectTypeWarning("You can not set date from future.");
            }
        }
        return date;
    }
    /**
     * Method supports setting clubId's field by user.
     * @return Obtained user input.
     */
    private Club userSetterClubId(){
        Club output = null;
        boolean correctInput = false;
        while(!correctInput){
            int ClubId = userSetterInteger("club id");
            if(database.readClub(ClubId) == null){
                viewer.incorrectTypeWarning("There is not club with such id.");
            } else {
                correctInput = true;
                output = database.readClub(ClubId);
            }
        }
        return output;   
    }
    /**
     * Method support choosing club object from the list by user.
     * @param clubs List of clubs to choose.
     * @return Method retuns chosen club object.
     */
    private int chooseClub(List<Club> clubs){
        int index = 0, iterator = 1;
        
        viewer.printMessage("Choose club to " + operation + "\n");
        for(Club club : clubs){
            viewer.printMessage(iterator + ". ");
            viewer.showClub(club);
            iterator++;
        }
        viewer.printMessage(iterator + ". Choose all clubs. \n0. Exit \n");
        return userSetterInteger("your choice") - 1;
    }
    /**
     * Method support choosing footballer object from the list by user.
     * @param footballers List of footballers to choose.
     * @return Method retuns chosen footballer object.
     */    
    private int chooseFootballer(List<Footballer> footballers){
        int iterator = 1;
        viewer.printMessage("Choose footballer to " + operation + "\n");
        for(Footballer footballer : footballers){
            viewer.printMessage(iterator + ". ");
            viewer.showFootballer(footballer);
            iterator++;
        }
        viewer.printMessage(iterator + ". Choose all footballers. \n0. Exit \n");
        return userSetterInteger("your choice") - 1;
    }
    /**
     * Method support choosing club's filed to update.
     * @param club Name of field to update.
     * @return Method returns club's object after update.
     */    
    private Club chooseClubField(Club club){
        String userInput = "";
        boolean loopBreaker = false;
        while(!loopBreaker){
            viewer.showClub(club);
            viewer.chooseClubFieldMenu();
            userInput = scanner.nextLine();
            switch(userInput){
                case "1":
                    club.setClubfullname(userSetterString("club full name"));
                    break;
                case "2":
                    club.setClubshortname(userSetterString("club short name"));
                    break;
                case "3":
                    club.setFounded(userSetterDate("club founded date"));
                    break;
                case "4":
                    club.setCity(userSetterString("city"));
                    break;
                case "5":
                    loopBreaker = true;
                    break;
            }
        }
        return club;
    }
    /**
     * Method support choosing footballer's filed to update.
     * @param footballer Name of field to update.
     * @return Method returns footballer's object after update.
     */   
    private Footballer chooseFootballerField(Footballer footballer){
        String userInput = "";
        boolean loopBreaker = false;
        while(!loopBreaker){
            viewer.showFootballer(footballer);
            viewer.chooseFootballerFieldMenu();
            userInput = scanner.nextLine();
            switch(userInput){
                case "1":
                    footballer.setFullname(userSetterString("name"));
                    break;
                case "2":
                    footballer.setClubNumber(userSetterInteger("club number"));
                    break;
                case "3":
                    footballer.setBirthDate(userSetterDate("birth date"));
                    break;
                case "4":
                    footballer.setPlayingPosition(userSetterString("playing position"));
                    break;
                case "5":
                    footballer.setClubId(userSetterClubId());
                    break;
                case "6":
                    loopBreaker = true;
                    break;
            }        
        }
        return footballer;
    }
}