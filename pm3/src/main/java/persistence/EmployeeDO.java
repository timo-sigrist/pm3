package persistence;

import javax.persistence.*;
import java.io.Serializable;

/**
 * DO for an employee
 */
@Entity
@Table(name = "temploye")
public class EmployeeDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private int employeeId;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "birthdate")
    private String birthdate;
    @Column(name = "teamname")
    private String teamName;
    @Column(name = "socialSecurityNumber")
    private String socialSecurityNumber;

    @ManyToOne
    private UserDO userDo;

    public EmployeeDO() {
    }

    /**
     * Constructor of EmployeeDO
     *
     * @param firstname firstname of the employee
     * @param lastname lastname of the employee
     * @param email email of the employee
     * @param birthdate birthdate of the employee
     * @param teamName teamName of the employee
     * @param socialSecurityNumber social security number of the employee
     */
    public EmployeeDO(String firstname, String lastname, String email, String birthdate, String teamName, String socialSecurityNumber) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setBirthdate(birthdate);
        setTeamName(teamName);
        setSocialSecurityNumber(socialSecurityNumber);
    }

    //getter and setter
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public UserDO getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDO userDo) {
        this.userDo = userDo;
    }

    /**
     * @return full name of the employee
     */
    public String getFullName() {
        return getFirstname() + " " + getLastname();
    }

    /**
     * Override toString Methode for dropdown/ combobox
     *
     * @return
     */
    @Override
    public String toString() {
        return getFullName();
    }
}