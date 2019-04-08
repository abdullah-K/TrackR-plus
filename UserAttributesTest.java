import static org.junit.Assert.*;
import org.junit.Test;
import org.json.*;

public class UserAttributesTest
{
    @Test public void testGetTotalExpense()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
		double sum =  2000 + 70 ;
	assertEquals(sum, user.getTotalExpenses(), 0.00001d);
    }

	//tests for get total expense
    @Test public void testGetTotalExpenseE()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.getTotalExpensesByCategory('e');
	assertEquals(0.d, user.getTotalExpensesByCategory('e'), 0.00001d);
    }
    @Test public void testGetTotalExpenseH()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.getTotalExpensesByCategory('h');
	assertEquals(1000.d, user.getTotalExpensesByCategory('h'), 0.00001d);
    }
    @Test public void testGetTotalExpenseF()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.getTotalExpensesByCategory('f');
	assertEquals(1000.d, user.getTotalExpensesByCategory('f'), 0.00001d);
    }
    @Test public void testGetTotalExpenseT()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.getTotalExpensesByCategory('t');
	assertEquals(70.d, user.getTotalExpensesByCategory('t'), 0.00001d);
    }
    @Test public void testGetTotalOtherExpenses()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.getTotalExpensesByCategory('o');
	assertEquals(0.d, user.getTotalExpensesByCategory('o'), 0.00001d);
    }


	
	//test spend on category
	//assume that if I spend money the total balance will be subtracted, and not added
    @Test public void testSpendOnCategoryH()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.spendByCategory('h',20);
	assertEquals(980d, user.getTotalExpensesByCategory('h'), 0.00001d);
    }

    @Test public void testSpendOnCategoryE()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.spendByCategory('e',20);
	assertEquals(-20d, user.getTotalExpensesByCategory('e'), 0.00001d);
    }

    @Test public void testSpendOnCategoryT()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.spendByCategory('t',20);
	assertEquals(50d, user.getTotalExpensesByCategory('t'), 0.00001d);
    }

    @Test public void testSpendOnCategoryF()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.spendByCategory('f',20);
	assertEquals(800d, user.getTotalExpensesByCategory('f'), 0.00001d);
    }

    @Test public void testSpendOnCategoryO()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	user.spendByCategory('o',20);
	assertEquals(-20d, user.getTotalExpensesByCategory('o'), 0.00001d);
    }

    @Test public void testDeposit()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	double initialDeposit=user.getUserBalance();
	user.deposit(20d);
	assertEquals(initialDeposit + 20d, user.getUserBalance(), 0.00001d);
    }

    @Test public void testDepositNegative()
    {
	UserAttributes user = 
      user = new UserAttributes("./user.json");
	System.out.println("initial user balance" + user.getUserBalance());
	double initialDeposit=user.getUserBalance();
	user.deposit(-20d);
	assertEquals(initialDeposit , user.getUserBalance(), 0.00001d);
    }

}
