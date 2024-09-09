package persistence;

import javax.persistence.*;

/**
 * DO for a question position
 */
@Entity
@Table(name = "tquestion_position")
public class QuestionPositionDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "question_position_id", nullable = false)
    private int questionPositionId;
    @ManyToOne
    private QuestionDO questionDo;
    @ManyToOne
    private PerformanceRatingDO performanceRatingDO;

    /**
     * @param questionDo DO for the assigned question
     * @param performanceRatingDo DO for the assigned performance rating
     */
    public QuestionPositionDO(QuestionDO questionDo, PerformanceRatingDO performanceRatingDo) {
        setQuestionDo(questionDo);
        setPerformanceRatingDo(performanceRatingDo);
    }

    public QuestionPositionDO() { }

    //getter and setter
    public int getQuestionPositionId() {
        return questionPositionId;
    }

    private void setQuestionPositionId(int questionPositionId) {
        this.questionPositionId = questionPositionId;
    }

    public QuestionDO getQuestionDo() {
        return questionDo;
    }

    public void setQuestionDo(QuestionDO questionDo) {
        this.questionDo = questionDo;
    }

    public PerformanceRatingDO getPerformanceRatingDO() {
        return performanceRatingDO;
    }

    public void setPerformanceRatingDo(PerformanceRatingDO performanceRatingDO) {
        this.performanceRatingDO = performanceRatingDO;
    }
}