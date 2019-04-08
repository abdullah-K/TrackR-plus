import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest
{
    @Test public void testNameGetter()
    {
	User user = new User("./user.json");
	assertEquals("Mathew", user.getUserName());
    }

    @Test public void testSavingsGoalGetter()
    {
	User user = new User("./user.json");
	assertEquals(20000d, user.getSavingsGoal(),0.00001d);
    }


    @Test public void testUserBalanceGetter()
    {
	User user = new User("./user.json");
	assertEquals(1047930d, user.getUserBalance(),0.00001d);
    }

    @Test public void testSetUserName()
    {
	User user = new User("./user.json");
	user.setUserName("haiyang");
	assertEquals("haiyang", user.getUserName());
    }

    @Test public void testSetSavingsGoal()
    {
	User user = new User("./user.json");
	user.setSavingsGoal(15d);
	assertEquals(15d, user.getSavingsGoal(),0.00001d);
    }

    @Test public void testSetUserBalance()
    {
	User user = new User("./user.json");
	user.setUserBalance(100d);
	assertEquals(100d, user.getUserBalance(),0.00001d);
    }
}
