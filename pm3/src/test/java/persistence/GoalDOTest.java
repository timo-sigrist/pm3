package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GoalDOTest {

    @Test
    void Test_Title()
    {
        final String expectedValue = "Test Title";
        GoalDO goal = new GoalDO();
        goal.setTitle(expectedValue);

        assertEquals(expectedValue, goal.getTitle());
    }

    @Test
    void Test_Description()
    {
        final String expectedValue = "i am great";
        GoalDO goal = new GoalDO();
        goal.setDescription(expectedValue);

        assertEquals(expectedValue, goal.getDescription());
    }

    @Test
    void Test_EmployeeAnswer()
    {
        final String expectedValue = "i am great";
        GoalDO goal = new GoalDO();
        goal.setAnswerEmployee(expectedValue);

        assertEquals(expectedValue, goal.getAnswerEmployee());
    }

    @Test
    void Test_EmployerAnswer()
    {
        final String expectedValue = "i am great";
        GoalDO goal = new GoalDO();
        goal.setAnswerEmployer(expectedValue);

        assertEquals(expectedValue, goal.getAnswerEmployer());
    }
}
