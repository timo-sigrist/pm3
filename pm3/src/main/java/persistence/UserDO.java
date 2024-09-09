package persistence;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

/**
 * DO for a user
 */
@Entity
@Table(name = "tuser")
public class UserDO {

    @Id
    @Column(name= "user_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * @param username unique name of the user
     * @param firstname firstname of the user
     * @param lastname lastname of the user
     * @param email email of the user
     * @param password password of the user
     */
    public UserDO(String username, String firstname, String lastname, String email, String password) {
        setUsername(username);
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
    }

    public UserDO() { }

    //getter and setter
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String password) {
        return BCrypt.checkpw(password, getPassword());
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return full name of the user
     */
    public String getFullName() {
        return getFirstname() + " " + getLastname();
    }
}