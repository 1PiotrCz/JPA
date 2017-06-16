package pl.piotrcz.Jpa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Created by Piotr Czubkowski on 2017-06-12.
 */
@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class User {
    @XmlAttribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @XmlElement
    private String username;
    @XmlElement
    private String password;
    @XmlElement
    private String role;
    @XmlElement
    private String gender;
    @XmlElement
    private Date datetime;

    public User() {
    }

    public User(String username, String password, String role, String gender, Date datetime) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", gender='" + gender + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}