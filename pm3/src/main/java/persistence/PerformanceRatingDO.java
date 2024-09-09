package persistence;

import javax.persistence.*;

/**
 * DO for a performance rating
 */
@Entity
@Table(name = "tperformanceRating")
public class PerformanceRatingDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "performanceRating_id", nullable = false)
    private int performanceRatingId;
    @Column(name = "comment")
    private String comment;
    @Column(name = "status")
    private String status;
    @Column(name = "employe_secretkey ")
    private String employeSecretkey ;
    @ManyToOne
    private EmployeeDO employeeDo;
    
    @ManyToOne
    private UserDO userDo;

    /**
     * @param employeeDo DO for the assigned employee
     * @param userDo DO for the assigned user
     */
    public PerformanceRatingDO(EmployeeDO employeeDo, UserDO userDo) {
        setEmployeeDo(employeeDo);
        setUserDo(userDo);
    }

    public PerformanceRatingDO() { }

    //getter and setter
    public int getPerformanceRatingId() {
        return performanceRatingId;
    }

    private void setPerformanceRatingId(int performanceRatingId) {
        this.performanceRatingId = performanceRatingId;
    }
    public String getComment() {
        return comment;
    }

    private void setComment(String comment) {
        this.comment = comment;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeSecretkey() {
        return employeSecretkey;
    }

    public void setEmployeSecretkey(String employeSecretkey) {
        this.employeSecretkey = employeSecretkey;
    }

    public EmployeeDO getEmployeeDo() {
        return employeeDo;
    }

    public void setEmployeeDo(EmployeeDO employeeDo) {
        this.employeeDo = employeeDo;
    }

    public UserDO getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDO userDo) {
        this.userDo = userDo;
    }
}