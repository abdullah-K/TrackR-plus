import static org.junit.Assert.*;
import org.junit.Test;
import org.json.*;

public class UserAttributesTest
{
    @Test 
    public void testConstructor()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        assertEquals("test constructor name", "testName", user.getUserName());
        assertEquals("test constructor balance",1000, user.getUserBalance(), 0.00001d);
        assertEquals("test constructor savings goal",100, user.getSavingsGoal(), 0.00001d);
    }

    @Test 
    public void testGetTotalExpense()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('e',50);
        user.spendByCategory('t',60);
        user.spendByCategory('e',40);
        assertEquals("Test get total expenses after spending 50,60,40 in different categories with initial balance 1000",150d, user.getTotalExpenses(), 0.00001d);
    }

	//tests for get total expense
    @Test 
    public void testGetTotalExpenseE()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('e',300);
        user.spendByCategory('e',100);
        user.spendByCategory('e',200);
        assertEquals("Test get total expenses after spending 300, 100, 200 with initial balance 1000",600d, user.getTotalExpensesByCategory('e'), 0.00001d);
    }
    @Test 
    public void testGetTotalExpenseH()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('h',44);
        user.spendByCategory('h',64);
        user.spendByCategory('h',90);
        assertEquals("Test get total expenses after spending 44, 64, 90 with initial balance 1000",198d, user.getTotalExpensesByCategory('h'), 0.00001d);
    }
    @Test 
    public void testGetTotalExpenseF()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('f',88);
        user.spendByCategory('f',194);
        user.spendByCategory('f',200);
        assertEquals("Test get total expenses after spending 88, 194, 200 with initial balance 1000",482d, user.getTotalExpensesByCategory('f'), 0.00001d);
    }
    @Test 
    public void testGetTotalExpenseT()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.getTotalExpensesByCategory('t');
        user.spendByCategory('t', 20);
        user.spendByCategory('t', 30);
        user.spendByCategory('t', 40);
        user.spendByCategory('t', 30);
        assertEquals("Test get total expenses after spending 20, 30, 40,30 with initial balance 1000",120d, user.getTotalExpensesByCategory('t'), 0.00001d);
    }
    @Test 
    public void testGetTotalExpensesO()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('o', 50);
        user.spendByCategory('o', 20);
        user.spendByCategory('o', 70);
        assertEquals("Test get total expenses after spending 50, 20, 70 with initial balance 1000",140d, user.getTotalExpensesByCategory('o'), 0.00001d);
    }

	
	//test spend on category
    @Test 
    public void testSpendOnCategoryH()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('h',20);
        assertEquals("Spend $20 on 'home' category with initial balance 1000",20d, user.getTotalExpensesByCategory('h'), 0.00001d);
    }

    @Test 
    public void testSpendOnCategoryE()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('e',20);
        assertEquals("Spend 20 on 'education' category with initial balance 1000",20d, user.getTotalExpensesByCategory('e'), 0.00001d);
    }

    @Test 
    public void testSpendOnCategoryT()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('t',100);
        assertEquals("Spend 100 on 'transporation' category with initial balance 1000", 100d, user.getTotalExpensesByCategory('t'), 0.00001d);
    }

    @Test 
    public void testSpendOnCategoryF()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('f',60);
        assertEquals("Spend 60 on 'others' category with initial balance 1000", 60d, user.getTotalExpensesByCategory('f'), 0.00001d);
    }

    @Test 
    public void testSpendOnCategoryO()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.spendByCategory('o',20);
        assertEquals("Spend on 'others' category with initial balance 1000", 20d, user.getTotalExpensesByCategory('o'), 0.00001d);
    }

    @Test 
    public void testDeposit()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        double initialDeposit = user.getUserBalance();
        user.deposit(20d);
        assertEquals("Deposit 20 with initial balance 1000",initialDeposit + 20d, user.getUserBalance(), 0.00001d);
    }

    @Test 
    public void testDepositNegative()
    {
        UserAttributes user = new UserAttributes("testName", 1000, 100);
        user.deposit(-20d);
        assertEquals("Deposited a negative amount, expect no change in balance", 1000d, user.getUserBalance(), 0.00001d);
    }

}
