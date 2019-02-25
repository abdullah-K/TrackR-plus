import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
*the Expenses class allows the user to see their expenses history
*/
public class Expenses {
  public static double totalExpenses;
  private static ArrayList<Double> expensesList = new ArrayList<Double>();
  private static JSONArray expensesListJSON;

  // read JSON content from the JSON file and create a JSONObject from it (which can be used throughout this class)
  private static JSONObject userData = Home.createJSONObject();

  /**
  * create a new key in the JSON file (if it doesn't already exist) which will be used for 
  * accessing the users spending history
  */
  public static void createExpensesList() {
    // createJSONObject();
    expensesListJSON = new JSONArray(expensesList); //array list into JSONArray
    // checks if expensesArray key exists
    userData.put("expensesArray", expensesListJSON); //expensesListJSON is put into userData
    // putJSONObjectIntoFile();
    Home.putJSONObjectIntoFile(userData);
  }

  /**
  *prompts the user for navigation in the expenses class
  */
  public static void expensesNavigation(){
    createExpensesList(); //creates an empty expenses list 
    System.out.println("To navigate the Expenses tab, use the following commands: ");
    System.out.println("Press 'h' to access your expenses history");
    System.out.println("Press 'b' to go back");
    System.out.println("Press 'q' to quit the application");

    Scanner navInput = new Scanner(System.in);
    char option;

    //doesn't allow user to enter char other than h,b or q
    while (true){
      option = navInput.next().toLowerCase().charAt(0);
      if(option=='h'||option=='b'||option=='q'){
        break;
      }
      else {
        option = navInput.next().toLowerCase().charAt(0);
      }
    }

    switch(option){
      case 'h':
        spendingHistory();
        expensesNavigation();
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
  * adds amount to expenses list, and creates a new key for the JSON file to log the expense
  */
  public static void logToExpensesList(double amount){
    expensesList.add(amount);
    createExpensesList();
  }

  /**
  * shows spending history of the user, including their total expenses and 
  * their individual expenses in chronological order
  */
  public static void spendingHistory() {
    if(expensesListJSON.length() == 0) {
      System.out.print("You have no spending history yet.\n");
    }
    else {
      String formattedExpenses = expensesListJSON.toString().replace("[","").replace("]","");
      System.out.print("Here are all your expenses in chronological order: " + formattedExpenses + "\n");
      double sum = 0;
      for (double i : expensesList) {
        sum += i;
      }
      System.out.print("Your total expenses are: $" + sum + "\n");
    }
  }

  /**
  * default setter method for totalexpenses
  */
  public void setExpenses(double expenses){
    totalExpenses = expenses;
  }
  public static void main(String[] args){

  }
}