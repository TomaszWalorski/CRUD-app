package pl.polsl.tomasz.walorski.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tomasz Walorski
 * Entity class, which store club's data. This class contain also named query for searching club's objects. 
 */
@Entity
@Table(name = "club")
@NamedQueries({
    @NamedQuery(name = "Club.findAll", query = "SELECT c FROM Club c"),
    @NamedQuery(name = "Club.findByClubId", query = "SELECT c FROM Club c WHERE c.clubId = :clubId"),
    @NamedQuery(name = "Club.findByClubfullname", query = "SELECT c FROM Club c WHERE c.clubfullname = :clubfullname"),
    @NamedQuery(name = "Club.findByClubshortname", query = "SELECT c FROM Club c WHERE c.clubshortname = :clubshortname"),
    @NamedQuery(name = "Club.findByFounded", query = "SELECT c FROM Club c WHERE c.founded = :founded"),
    @NamedQuery(name = "Club.findByCity", query = "SELECT c FROM Club c WHERE c.city = :city")})
public class Club implements Serializable {
    /**
     * Field store club's footballers.
     */
    @OneToMany(cascade = REMOVE, mappedBy = "clubId")
    private Collection<Footballer> footballerCollection;
    /**
     * Field clubId is primary key. 
     */    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Integer clubId;
    /**
     * Field store club's full name.
     */
    @Column(name = "clubfullname")
    private String clubfullname;
    /**
     * Field store club's short name.
     */
    @Column(name = "clubshortname")
    private String clubshortname;
    /**
     * Field store club's founded date.
     */
    @Column(name = "founded")
    @Temporal(TemporalType.DATE)
    private Date founded;
    /**
     * Field store club's city name.
     */
    @Column(name = "city")
    private String city;
    /**
     * Empty constructor.
     */
    public Club() {
    }
    /**
     * Constructor which contain parameter according to every club's field.
     * @param clubfullname Full name string to set.
     * @param clubshortname Short name string to set.
     * @param founded Founded date to set.
     * @param city City name string to set.
     */
    public Club(String clubfullname, String clubshortname, Date founded, String city) {
        this.clubId = 0;
        this.clubfullname = clubfullname;
        this.clubshortname = clubshortname;
        this.founded = founded;
        this.city = city;
    }
    /**
     * Primary key getter.
     * @return Method return primary key.
     */
    public Integer getClubId() {
        return clubId;
    }
    /**
     * Primary key setter.
     * @param clubId Primary key to set. 
     */
    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }
    /**
     * Full ame getter.
     * @return Method return full name.
     */
    public String getClubfullname() {
        return clubfullname;
    }
    /**
     * Full name setter.
     * @param clubfullname Return full name. 
     */
    public void setClubfullname(String clubfullname) {
        this.clubfullname = clubfullname;
    }
    /**
     * Short name getter.
     * @return Method return short name.
     */
    public String getClubshortname() {
        return clubshortname;
    }
    /**
     * Short name setter.
     * @param clubshortname Return short name. 
     */
    public void setClubshortname(String clubshortname) {
        this.clubshortname = clubshortname;
    }
    /**
     * Founded date getter.
     * @return Method return founded date.
     */
    public Date getFounded() {
        return founded;
    }
    /**
     * Founded date setter.
     * @param founded Founded date to set.
     */
    public void setFounded(Date founded) {
        this.founded = founded;
    }
    /**
     * City name getter.
     * @return Method return city name.
     */
    public String getCity() {
        return city;
    }
    /**
     * City name setter.
     * @param city Return city name. 
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Footballers list getter.
     * @return Method return footballers list.
     */
    public Collection<Footballer> getFootballerCollection() {
        return footballerCollection;
    }
    /**
     * Footballers list setter.
     * @param footballerCollection Footballers list to set.
     */    
    public void setFootballerCollection(Collection<Footballer> footballerCollection) {
        this.footballerCollection = footballerCollection;
    }
    /**
     * Method overrides hashCode method.
     * @return Method returns hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clubId != null ? clubId.hashCode() : 0);
        return hash;
    }
    /**
     * Method compares club's object with inputed one.
     * @param object Inputed object for comparison.
     * @return Method returns true if objects are the same.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Club)) {
            return false;
        }
        Club other = (Club) object;
        if ((this.clubId == null && other.clubId != null) || (this.clubId != null && !this.clubId.equals(other.clubId))) {
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
        return "[ Id=" + clubId + " ] " +
                clubfullname + " " + clubshortname + " " + format.format(founded) + " " + city + "\n";
    }
}
 