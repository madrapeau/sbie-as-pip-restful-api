package me.madrapeau.sbieaspiprestfulapi;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
// Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=100, allocationSize=100)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;
    @Column
    private String userName;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany(mappedBy = "user")
    private List<Right> rights;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Right> getRights() {
        return rights;
    }
}