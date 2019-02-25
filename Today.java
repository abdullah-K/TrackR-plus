import java.util.Scanner;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;

/**
* the Today class allows the user to register their expenses, deposit money into their account, and shows tthe progress towards their savings goal
*/
public class Today{

  // private static double progress;
  private static double balance;
  private static double goal;
  private static double expenses;

  // read JSON content from the JSON file and create a JSONObject from it (which can be used throughout this class)
  private static JSONObject userData = Home.createJSONObject();

  /**
  *prompts the user for navigation in the today class
  */
  public static void todayNavigation(){
    System.out.println("To navigate the Today tab, use the following commands: ");
    System.out.println("Press 's' to spend or register your expenses");
    System.out.println("Press 'd' to deposit incoming money into your account");
    System.out.println("Press 'b' to go back");
    System.out.println("Press 'q' to quit the application");

    Scanner navInput = new Scanner(System.in);
    char option;

    //doesn't allow user to enter char other than s,d,p,b or q
    while (true) {
      option = navInput.next().toLowerCase().charAt(0);
      if(option=='s'||option=='d'||option=='p'||option=='b'||option=='q'){
        break;
      }
      else {
        // System.out.println(option);
        option = navInput.next().toLowerCase().charAt(0);
      }
    }

    switch(option) {
      case 's':
        spend();
        todayNavigation();
        break;
      case 'd':
        deposit();
        todayNavigation();
        break;
      case 'b':
        Home.navigation();
        break;
      case 'q':
        System.out.print("Goodbye\n");
        System.exit(0);
      default:
        System.out.print("Invalid option. Please select one of the navigation options.\n");
    }

  }

  /**
  * spend an amount of money and log it into your expenses history
  */
  public static void spend(){
    Expenses.createExpensesList();
    //amount to spend has to be positive number and can't be more than what is in balance
    //double balanceJSON = (double)((Integer)(Object)userData.get("userBalance"));
    double balanceJSON = ((Number)userData.get("userBalance")).doubleValue();
    //double balanceJSON = (double)userData.get("userBalance");
    System.out.println("Your current balance is: " + balanceJSON);
    System.out.println("Please enter an amount to spend: ");
    Scanner spendingInput = new Scanner(System.in);
    double amount = spendingInput.nextDouble();
    // logs this expense to the expenses array
    // Expenses.getExpensesList().add(amount);
    Expenses.logToExpensesList(amount);
    balanceJSON -= amount;
    System.out.println("Your new balance is: " + balanceJSON);
    userData.put("userBalance", balanceJSON);
    Home.putJSONObjectIntoFile(userData);

    //prompt user for options again?
  }

  /**
  * deposits an amount of money into your already-defined userBalance (from the JSON file)
  */
  public static void deposit(){
    //amount to deposit has to be positive number
    double balanceJSON = ((Number)userData.get("userBalance")).doubleValue();
    System.out.println("Your current balance is: " + balanceJSON);
    System.out.println("Please enter an amount to deposit: ");
    Scanner depositInput = new Scanner(System.in);
    double amount = depositInput.nextDouble();
    balanceJSON += amount;
    System.out.println("Your new balance is: " + balanceJSON);
    userData.put("userBalance", balanceJSON);
    Home.putJSONObjectIntoFile(userData);

  }

  /**
  *shows the user balance
  */
  public static void displayUserBalance(){
    System.out.println("Your balance is: "+balance);
  }

  /**
  *shows the savings goal
  */
  public static void displaySavingsGoal(){
    System.out.println("Your savings goal is: " + goal);
  }


  /*
  public static String progress(){
    double goalProgress = Goals.getGoal() - Expenses.spendingHistory();
    if(goalProgress < 0){
      System.out.println("You are currently $" + goalProgress + " past your set goal.");
    }
    else{
      System.out.println("You're on track to save money this month!");
    }

  }*/

  /**
  *sets the progress ?
  */
  // public static void setProgress(double progress){
  //
  //   this.progress = progress;
  //
  // }
  //
  // /**
  // *returns expenses
  // */
  // public static double getExpenses(){
  //
  //   return this.expenses;
  //
  // }
  //
  // /**
  // *sets how much was spent
  // */
  // public static void setExpenses(double expenses){
  //
  //   this.expenses = expenses;
  //
  // }
  //
  // /**
  // *sets the amount of money the person has
  // */
  // public static void setBalance(double balance){
  //
  //   this.balance = balance;
  // }

}
