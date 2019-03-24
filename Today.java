import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

/**
* the Today class allows the user to register their expenses, deposit money into their account, and shows tthe progress towards their savings goal
*/
public class Today{

  private double balance = 0.0;
  private static Today todayObject = null;
  // read JSON content from the JSON file and create a JSONObject from it (which can be used throughout this class)
  private JSONObject userData;

  public static Today getTodayObject() {
      if(todayObject == null) {
          todayObject = new Today();
      }
      return todayObject;
  }

  Main main = Main.getMainObject(); 
  Expenses expenses = Expenses.getExpObject();
  Goals goals = Goals.getGoalsObject();
  Scanner navInput = new Scanner(System.in);

  /**
  *prompts the user for navigation in the today class
  */
  public void todayNavigation() {
    // userData = main.getJSONObjectFromFile();
    userData = main.getJSONObject(); //added
    JSONObject dummyJSONObject = main.getJSONObjectFromFile();
    if(!dummyJSONObject.has("expensesArray1")) {
      expenses.createExpensesList();
    }
    System.out.println("To navigate the Today tab, use the following commands: ");
    System.out.println("Press 's' to spend or register your expenses");
    System.out.println("Press 'd' to deposit incoming money into your account");
    System.out.println("Press 'p' to view your savings progress");
    System.out.println("Press 'b' to go back");
    System.out.println("Press 'q' to quit the application");

    char option;
    //doesn't allow user to enter char other than s,d,p,b or q
    while (true) {
      option = navInput.next().toLowerCase().charAt(0);
      if(option=='s'||option=='d'||option=='p'||option=='b'||option=='q'||option=='p'){
        break;
      }
    }

    switch(option) {
      case 's':
        spendByCategory();
        todayNavigation();
        break;
      case 'd':
        deposit();
        todayNavigation();
        break;
      case 'b':
        main.navigation();
        break;
      case 'p':
        progress();
        todayNavigation();
        break;
      case 'q':
        System.out.print("Goodbye\n");
        System.exit(0);
      default:
        System.out.print("Invalid option. Please select one of the navigation options.\n");
    }
  }

  public void spendByCategory(){
    ArrayList<Double> anArray;
    // userData = main.getJSONObjectFromFile();
    userData = main.getJSONObject(); //added
    System.out.println("To navigate the Categories tab, use the following commands: ");
    System.out.println("Press 'e' to register a spending action under education category");
    System.out.println("Press 'h' to register a spending action under main category");
    System.out.println("Press 't' to register a spending action under auto & transportation category");
    System.out.println("Press 'f' to register a spending action under food & drinks category");
    System.out.println("Press 'o' to register a spending action under others category");
    System.out.println("Press 'b' to go back");

    char option;

    while (true) {
      option = navInput.next().toLowerCase().charAt(0);
      if(option=='e'||option=='h'||option=='t'||option=='f'||option=='o'||option =='b'){
        break;
      }
  }
  switch(option) {
    case 'e':
      anArray = expenses.expensesList1;
      spend(anArray, "1");
      break;
    case 'h':
      // expenses.updateInstanceExpensesArrays();
      anArray = expenses.expensesList2;
      spend(anArray, "2");
      break;
    case 't':
      // expenses.updateInstanceExpensesArrays();
      anArray = expenses.expensesList3;
      spend(anArray, "3");
      break;
    case 'f':
      // expenses.updateInstanceExpensesArrays();
      anArray = expenses.expensesList4;
      spend(anArray, "4");
      break;
    case 'o':
      // expenses.updateInstanceExpensesArrays();
      anArray = expenses.expensesList5;
      spend(anArray, "5");
      break;
    case 'b':
      todayNavigation();
      break;
    default:
      System.out.print("Invalid option. Please select one of the navigation options.\n");
  }
}

  /**
  * spend an amount of money and log it into your expenses history
  */
  public void spend(ArrayList<Double> expenseArrayToPutIn, String category){
    //userData = main.getJSONObjectFromFile();
    //amount to spend has to be positive number and can't be more than what is in balance
    double balanceJSON = ((Number)userData.get("userBalance")).doubleValue();
    System.out.println("Your current balance is: " + balanceJSON);
    System.out.println("Please enter an amount to spend: ");
    Scanner spendingInput = new Scanner(System.in);
    double amount = spendingInput.nextDouble();

    if (balanceJSON >= amount){
      balanceJSON -= amount;
      System.out.println("Your new balance is: " + balanceJSON);
      userData.put("userBalance", balanceJSON);
      expenseArrayToPutIn.add(amount);
      userData.put("expensesArray" + category, expenseArrayToPutIn);
      //main.putJSONObjectIntoFile(userData);
    }
    // main.putJSONObjectIntoFile(userData);
    main.setJSONObject(userData); //added
    userData = main.getJSONObject();
  }

  /**
  * deposits an amount of money into your already-defined userBalance (from the JSON file)
  */
  public void deposit(){
    userData = main.getJSONObjectFromFile();
    double balanceJSON = ((Number)userData.get("userBalance")).doubleValue();// the balance

    //amount to deposit has to be positive number
    System.out.println("Your current balance is: " + balanceJSON);
    System.out.println("Please enter an amount to deposit: ");
    Scanner depositInput = new Scanner(System.in);
    double amount = depositInput.nextDouble();
    if (amount > 0){
      balanceJSON += amount;
      System.out.println("Your new balance is: " + balanceJSON);
      //userData.remove("userBalance");
      userData.put("userBalance", balanceJSON);
    }
    // main.putJSONObjectIntoFile(userData);
    main.setJSONObject(userData);
    // userData = main.getJSONObjectFromFile();
    userData = main.getJSONObject();
  }

  public void progress(){
    double goal = goals.getGoal();
    double totalExp = expenses.getTotalExpenses();
    double progress = 0.0;
    if (totalExp < goal){
      progress = goal - totalExp;
      System.out.println("Good job, you are " + "$" + progress + " below your desired savings goal!");
    }
    else if (totalExp > goal){
      progress = goal - totalExp;
      System.out.println("Careful, you are " + "-$" + Math.abs(progress) + " above your desired savings goal!");
    }
    else {
      System.out.println("You have saved exatcly your desired goal so far, careful with extra spendings!");
    }
  }
}