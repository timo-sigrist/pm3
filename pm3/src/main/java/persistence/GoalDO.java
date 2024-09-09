package persistence;

import javax.persistence.*;

/**
 * DO for a goal
 */
@Entity
@Table(name="tgoal")
public class GoalDO {
    /**
     * Constructor of GoalDO
     *
     * @param title title of the goal
     * @param description description of the employer
     * @param answerEmployee answer of the employee
     * @param answerEmployer answer of the employer
     */
    public GoalDO(String title, String description, String answerEmployer, String answerEmployee) {
        setTitle(title);
        setDescription(description);
        setAnswerEmployer(answerEmployer);
        setAnswerEmployee(answerEmployee);
    }

    public GoalDO() { }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="goal_id", nullable = false, unique = true)
    private int id;

    @Column(name = "goal_title")
    private String title;

    @Column(name="goal_description")
    private String description;

    @Column(name="answer_employer", nullable = true)
    private String answerEmployer;

    @Column(name="answer_employee", nullable = true)
    private String answerEmployee;

    // getter and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getAnswerEmployer() {
        return answerEmployer;
    }

    public void setAnswerEmployer(String answerEmployer) {
        this.answerEmployer = answerEmployer;
    }

    public String getAnswerEmployee() {
        return answerEmployee;
    }

    public void setAnswerEmployee(String answerEmployee) {
        this.answerEmployee = answerEmployee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}