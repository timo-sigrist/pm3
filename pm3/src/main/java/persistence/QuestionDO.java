package persistence;

import javax.persistence.*;

/**
 * DO for a question
 */
@Entity
@Table(name = "tquestion")
public class QuestionDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private int id;

    @Column(name = "question_title")
    private String title;
    @Column(name = "question_description")
    private String description;
    @Column(name = "answer_text_employee")
    private String answerEmployee;
    @Column(name = "answer_text_employer")
    private String answerEmployer;

    /**
     * Constructor of QuestionDO
     *
     * @param questionId id for the question
     * @param questionTitle title of question
     * @param questionDescription description of the question
     * @param answerEmployer answer of the employer
     */
    public QuestionDO(int questionId, String questionTitle, String questionDescription, String answerEmployer) {
        setQuestionId(questionId);
        setQuestionTitle(questionTitle);
        setQuestionDescription(questionDescription);
        setAnswerEmployer(answerEmployer);
    }

    /**
     * Constructor of QuestionDO
     *
     * @param questionTitle title of question
     * @param questionDescription description of the question
     * @param answerEmployer answer of the employer
     */
    public QuestionDO(String questionTitle, String questionDescription, String answerEmployer) {
        setQuestionTitle(questionTitle);
        setQuestionDescription(questionDescription);
        setAnswerEmployer(answerEmployer);
    }

    public QuestionDO() { }

    //getter and setter
    public int getQuestionId() {
        return id;
    }

    private void setQuestionId(int questionId) {
        this.id = questionId;
    }

    public String getQuestionDescription() {
        return description;
    }

    public void setQuestionDescription(String questionDescription) {
        this.description = questionDescription;
    }

    public String getAnswerEmployee() {
        return answerEmployee;
    }

    public void setAnswerEmployee(String answerEmployee) {
        this.answerEmployee = answerEmployee;
    }

    public String getAnswerEmployer() {
        return answerEmployer;
    }

    public void setAnswerEmployer(String answerEmployer) {
        this.answerEmployer = answerEmployer;
    }

    public String getQuestionTitle() {
        return title;
    }

    public void setQuestionTitle(String questionTitle) {
        this.title = questionTitle;
    }
}