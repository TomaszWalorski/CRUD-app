package pl.polsl.tomasz.walorski.database;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import pl.polsl.tomasz.walorski.model.Club;
import pl.polsl.tomasz.walorski.model.Footballer;

/**
 * @author Tomasz Walorski
 * Class is used for performing create/read/update/delete operations on data base.
 */
public class DataBaseCRUD {
    /**
     * EntityManager's field commit necessary methods to perform CRUD operations.
     */
    private final EntityManager entityManager;
    
    public DataBaseCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    /**
     * Method perform operation of saving new club's or footballer's row in database.
     * @param object Object sent to database.
     */
    public void create(Object object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
    /**
     * Method search club object by its id.
     * @param id Primaty key of object.
     * @return Method return proper club's object, according to its id.
     */
    public Club readClub(int id){
        return entityManager.find(Club.class, id);
    }
    /**
     * Method find club object by its id. Method use NamedQuery for searching.
     * @param id Primary key used to find proper object.
     * @return Method return list with club's object, which contain proper .
     */
    public List<Club> readClubById(int id){
        return entityManager.createNamedQuery("Club.findByClubId").setParameter("clubId", id).getResultList();
    }
    /**
     * Method find all club's object.
     * @return Method return list of all club's objects.
     */
    public List<Club> readAllClubs(){
        return entityManager.createNamedQuery("Club.findAll").getResultList();
    }
    /**
     * Method find club object by its full name. Method use NamedQuery for searching.
     * @param name Club full name used to find proper objects.
     * @return Method return list of club's objects, which contain proper full name.
     */
    public List<Club> readClubByName(String name){
        return entityManager.createNamedQuery("Club.findByClubfullname").setParameter("clubfullname", name).getResultList();
    }
    /**
     * Method find club object by its short name. Method use NamedQuery for searching.
     * @param name Club short name used to find proper objects.
     * @return Method return list of club's objects, which contain proper short name.
     */
    public List<Club> readClubByShortName(String name){
        return entityManager.createNamedQuery("Club.findByClubshortname").setParameter("clubshortname", name).getResultList();
    }
    /**
     * Method find club object by its date. Method use NamedQuery for searching.
     * @param date Founded date used to find proper object.
     * @return Method return list of club's objects, which contain proper founded date.
     */
    public List<Club> readClubByFoundedDate(Date date){
        return entityManager.createNamedQuery("Club.findByFounded").setParameter("founded", date).getResultList();
    }
    /**
     * Method find club object by its city's name. Method use NamedQuery for searching.
     * @param city Club's city's name used to find proper object.
     * @return Method return list of club's objects, which contain proper city's name.
     */
    public List<Club> readClubByCity(String city){
        return entityManager.createNamedQuery("Club.findByCity").setParameter("city", city).getResultList();
    }
    /**
     * Method search footballer object by its id.
     * @param id Primaty key of object.
     * @return Method return proper footballer's object, according to its id.
     */
    public Footballer readFootballer(int id){
        return entityManager.find(Footballer.class, id);
    }
    /**
     * Method find footballer object by its id. Method use NamedQuery for searching.
     * @param id Primary key used to find proper object.
     * @return Method return list with footballer's object, which contain proper .
     */
    public List<Footballer> readFootballerById(int id){
        return entityManager.createNamedQuery("Footballer.findByFootballerId").setParameter("footballerId", id).getResultList();
    }
    /**
     * Method find all footballer's object.
     * @return Method return list of all footballer's objects.
     */
    public List<Footballer> readAllFootballers(){
        return entityManager.createNamedQuery("Footballer.findAll").getResultList();
    }
    /**
     * Method find footballer's object by its name. Method use NamedQuery for searching.
     * @param name Footballer name used to find proper objects.
     * @return Method return list of footballer's objects, which contain proper name.
     */    
    public List<Footballer> readFootballerByName(String name){
        return entityManager.createNamedQuery("Footballer.findByFullname").setParameter("fullname", name).getResultList();
    }
    /**
     * Method find footballer's object by its club number. Method use NamedQuery for searching.
     * @param number Footballer club number used to find proper objects.
     * @return Method return list of footballer's objects, which contain proper club number.
     */    
    public List<Footballer> readFootballerByClubNumber(int number){
        return entityManager.createNamedQuery("Footballer.findByClubNumber").setParameter("clubNumber", number).getResultList();
    }
    /**
     * Method find footballer's object by its birth date. Method use NamedQuery for searching.
     * @param date Footballer birth date used to find proper objects.
     * @return Method return list of footballer's objects, which contain proper birth date.
     */ 
    public List<Footballer> readFootballerByBirthDate(Date date){
        return entityManager.createNamedQuery("Footballer.findByBirthDate").setParameter("birthDate", date).getResultList();
    }
    /**
     * Method find footballer's object by its position. Method use NamedQuery for searching.
     * @param position Footballer position used to find proper objects.
     * @return Method return list of footballer's objects, which contain proper position.
     */     
    public List<Footballer> readFootballerByPosition(String position){
        return entityManager.createNamedQuery("Footballer.findByPlayingPosition").setParameter("fullname", position).getResultList();
    }
    /**
     * Method update existing object.
     * @param object Existing object entered to update.
     */
    public void update(Object object){
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
    /**
     * Method delete object from database.
     * @param object Chosen object to remove.
     */
    public void delete(Object object){
        entityManager.getTransaction().begin();
        if(!entityManager.contains(object)){
            object = entityManager.merge(object);
        }
        entityManager.remove(object);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
    /**
     * Method close EntityManager's field.
     */
    public void close(){
         entityManager.close();
    }
}
