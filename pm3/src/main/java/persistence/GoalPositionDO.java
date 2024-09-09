package persistence;

import javax.persistence.*;

/**
 * DO for a goal position
 */
@Entity
@Table(name = "tgoal_position")
public class GoalPositionDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "question_position_id", nullable = false)
    private int goalPositionId;
    @ManyToOne
    private PerformanceRatingDO performanceRatingDo;
    @ManyToOne
    private GoalDO goalDo;

    /**
     * @param performanceRatingDo DO for the assigned performance
     * @param goalDo DO for the assigned goal
     */
    public GoalPositionDO(PerformanceRatingDO performanceRatingDo, GoalDO goalDo) {
        setPerformanceRatingDo(performanceRatingDo);
        setGoalDo(goalDo);
    }

    public GoalPositionDO() { }

    //getter and setter
    public int getGoalPositionId() {
        return goalPositionId;
    }

    private void setGoalPositionId(int goalPositionId) {
        this.goalPositionId = goalPositionId;
    }

    public PerformanceRatingDO getPerformanceRatingDo() {
        return performanceRatingDo;
    }

    public void setPerformanceRatingDo(PerformanceRatingDO performanceRatingDo) {
        this.performanceRatingDo = performanceRatingDo;
    }

    public GoalDO getGoalDo() {
        return goalDo;
    }

    public void setGoalDo(GoalDO goalDo) {
        this.goalDo = goalDo;
    }
}