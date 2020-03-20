package pl.polsl.tomasz.walorski.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Tomasz Walrski
 * Entity class, which store footballer's data. This class contain also named query for searching footballer's objects. 
 */
@Entity
@Table(name = "footballer")
@NamedQueries({
    @NamedQuery(name = "Footballer.findAll", query = "SELECT f FROM Footballer f"),
    @NamedQuery(name = "Footballer.findByFootballerId", query = "SELECT f FROM Footballer f WHERE f.footballerId = :footballerId"),
    @NamedQuery(name = "Footballer.findByFullname", query = "SELECT f FROM Footballer f WHERE f.fullname = :fullname"),
    @NamedQuery(name = "Footballer.findByClubNumber", query = "SELECT f FROM Footballer f WHERE f.clubNumber = :clubNumber"),
    @NamedQuery(name = "Footballer.findByBirthDate", query = "SELECT f FROM Footballer f WHERE f.birthDate = :birthDate"),
    @NamedQuery(name = "Footballer.findByPlayingPosition", query = "SELECT f FROM Footballer f WHERE f.playingPosition = :playingPosition")})
public class Footballer implements Serializable {
    /**
     * Field footballerId is primary key. 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "footballer_id")
    private Integer footballerId;
    /**
     * Field store footballer's name.
     */
    @Column(name = "fullname")
    private String fullname;
    /**
     * Field store footballer's club number.
     */
    @Column(name = "club_number")
    private int clubNumber;
    /**
     * Field store footballer's birth date.
     */
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    /**
     * Field store footballer's playing position
     */
    @Column(name = "playing_position")
    private String playingPosition;
    /**
     * Field store footballer's club.
     */
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    @ManyToOne
    private Club clubId;
    /**
     * Empty constructor.
     */
    public Footballer() {
    }
    /**
     * Constructor which contain parameter according to every footballer's field.
     * @param fullname Full name string to set.
     * @param clubNumber Club number integer value to set.
     * @param birthDate Birth date to set.
     * @param playingPosition Playing position string to set.
     * @param clubId Footballer's club to set.
     */
    public Footballer(String fullname, int clubNumber, Date birthDate, String playingPosition, Club clubId) {
        this.footballerId = 0;
        this.fullname = fullname;
        this.clubNumber = clubNumber;
        this.birthDate = birthDate;
        this.playingPosition = playingPosition;
        this.clubId = clubId;
    }
    /**
     * Primary key getter.
     * @return Method return primary key.
     */
    public Integer getFootballerId() {
        return footballerId;
    }
    /**
     * Primary key setter.
     * @param footballerId Primary key to set. 
     */
    public void setFootballerId(Integer footballerId) {
        this.footballerId = footballerId;
    }
    /**
     * Name getter.
     * @return Method return name.
     */
    public String getFullname() {
        return fullname;
    }
    /**
     * Name setter.
     * @param fullname Return name. 
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    /**
     * Club number getter.
     * @return Method return club number.
     */
    public int getClubNumber() {
        return clubNumber;
    }
    /**
     * Club number setter
     * @param clubNumber Club number to set.
     */
    public void setClubNumber(int clubNumber) {
        this.clubNumber = clubNumber;
    }
    /**
     * Birth date getter.
     * @return Method return birth date.
     */
    public Date getBirthDate() {
        return birthDate;
    }
    /**
     * Birth date setter.
     * @param birthDate Birth date to set.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    /**
     * Playing position getter.
     * @return Method return playing position.
     */
    public String getPlayingPosition() {
        return playingPosition;
    }
    /**
     * Playing position setter.
     * @param playingPosition Playing position to set.
     */
    public void setPlayingPosition(String playingPosition) {
        this.playingPosition = playingPosition;
    }
    /**
     * Footballer's club getter.
     * @return Method return footballer's club.
     */
    public Club getClubId() {
        return clubId;
    }
    /**
     * Footballer's club setter.
     * @param clubId Footballer's club to set.
     */
    public void setClubId(Club clubId) {
        this.clubId = clubId;
    }
    /**
     * Method overrides hashCode method.
     * @return Method returns hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (footballerId != null ? footballerId.hashCode() : 0);
        return hash;
    }
    /**
     * Method compares footballer's object with inputed one.
     * @param object Inputed object for comparison.
     * @return Method returns true if objects are the same.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Footballer)) {
            return false;
        }
        Footballer other = (Footballer) object;
        if ((this.footballerId == null && other.footballerId != null) || (this.footballerId != null && !this.footballerId.equals(other.footballerId))) {
            return false;
        }
        return true;
    }
    /**
     * Method puts whole object's content into string.
     * @return Method returns string with content.
     */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return "[ Id = " + footballerId + " ]" + fullname + " " + clubNumber + " " +
                format.format(birthDate) + " " + playingPosition + " " + clubId.getClubfullname() + "\n";
    }
}