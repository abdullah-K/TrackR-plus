import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest
{ 
    @Test 
    public void testSetName()
    {
        User user = new User("testName", 1000, 100);
        user.setUserName("test");
        assertEquals("test", user.getUserName());
    }

    @Test 
    public void testSavingsGoalGetter()
    {
        User user = new User("testName", 1000, 100);
        assertEquals(100d, user.getSavingsGoal(),0.00001d);
    }

    @Test 
    public void testSetUserBalanceNegative()
    {
        User user = new User("testName", 0, 0);
        user.setUserBalance(-500);
        assertEquals("Set user balance to a negative amount, no change expected",0d, user.getUserBalance(),0.00001d);
    }

    @Test 
    public void testUserBalanceGetter()
    {
        User user = new User("testName", 1000, 100);
        assertEquals("Get the user balance (set by a constructor)",1000d, user.getUserBalance(),0.00001d);
    }

    @Test 
    public void testSetSavingsGoalNegative()
    {
        User user = new User("testName", 1000, 0);
        user.setSavingsGoal(-150d);
        assertEquals("Set user savings goal to a negative amount, no change expected", 0d, user.getSavingsGoal(),0.00001d);
    }

}
