import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
*the Expenses class allows the user to see the expenses history
*/
public class Expenses extends Main{

  Main main = new Main();

  private double totalExpenses;
  public ArrayList<Double> expensesList1 = new ArrayList<Double>();
  public ArrayList<Double> expensesList2= new ArrayList<Double>();
  public ArrayList<Double> expensesList3= new ArrayList<Double>();
  public ArrayList<Double> expensesList4= new ArrayList<Double>();
  public ArrayList<Double> expensesList5= new ArrayList<Double>();
  public JSONArray expensesListJSON1;
  public JSONArray expensesListJSON2;
  public JSONArray expensesListJSON3;
  public JSONArray expensesListJSON4;
  public JSONArray expensesListJSON5;

  //main main = new main();
  // read JSON content from the JSON file and create a JSONObject from it (which can be used throughout this class)
  private JSONObject userData = main.getJSONObjectFromFile();//JSON object
  /**
  * create a new key in the JSON file (if it doesn't already exist) which will be used for
  */
  public void createExpensesList(){
    userData = main.getJSONObjectFromFile();
    //1
    expensesListJSON1 = new JSONArray(expensesList1); //array list into JSONArray
    // checks if expensesArray key exists
    userData.put("expensesArray1", expensesListJSON1); //expensesListJSON is put into userData

    //2
    expensesListJSON2 = new JSONArray(expensesList2);
    userData.put("expensesArray2", expensesListJSON2);

    //3
    expensesListJSON3= new JSONArray(expensesList3);
    userData.put("expensesArray3", expensesListJSON3);

    //4
    expensesListJSON4= new JSONArray(expensesList4);
    userData.put("expensesArray4", expensesListJSON4);

    //5
    expensesListJSON5 = new JSONArray(expensesList5);
    userData.put("expensesArray5", expensesListJSON5);
    main.putJSONObjectIntoFile(userData);
  }

  /**
  *prompts the user for navigation in the expenses class
  */
  public void expensesNavigation(){
    userData = main.getJSONObjectFromFile();
    JSONObject dummyJSONObject = main.getJSONObjectFromFile();
    if(!dummyJSONObject.has("expensesArray1") || !dummyJSONObject.has("expensesArray2") || !dummyJSONObject.has("expensesArray3") || !dummyJSONObject.has("expensesArray4") || !dummyJSONObject.has("expensesArray5")) {
      createExpensesList();
    }
    System.out.println("To navigate the Expenses tab, use the following commands: ");
    System.out.println("Press 'h' to access your expenses history");
    System.out.println("Press 'b' to go back");
    System.out.println("Press 'q' to quit the application");

    Scanner navInput = new Scanner(System.in);
    char option;

    //doesn't allow user to enter char other than h,b or q
    while (true) {
      option = navInput.next().toLowerCase().charAt(0);
      if(option=='h'||option=='b'||option=='q'){
        break;
      }
      else {
        option = navInput.next().toLowerCase().charAt(0);
      }
    }

    switch(option) {
      case 'h':
        spendingHistory();
        expensesNavigation();
        break;
      case 'b':
        main.navigation();
        break;
      case 'q':
        System.out.print("Goodbye\n");
        System.exit(0);
      default:
        System.out.print("Invalid option. Please select one of the navigation options.\n");
    }
  }

  /**
  * adds amount to expenses list
  */
  public void logToExpensesList(double amount){

    createExpensesList();
  }

  /**
  * shows the list of money spent by the user
  */
  public void spendingHistory() {
    userData = main.getJSONObjectFromFile();
    // convert the Objects returned by userData.get() to  JSONArrays in order to get its length
    if(((JSONArray) userData.get("expensesArray1")).length() == 0 &&
      ((JSONArray) userData.get("expensesArray2")).length() == 0 &&
      ((JSONArray) userData.get("expensesArray3")).length() == 0 &&
      ((JSONArray) userData.get("expensesArray4")).length() == 0 &&
      ((JSONArray) userData.get("expensesArray5")).length() == 0) {
      System.out.print("You have no spending history yet.\n");
    }
    else {
      System.out.print("Here are all your expenses categorically: " + "\n");
      System.out.print("Education category " +  ((JSONArray) userData.get("expensesArray1")).toString() + "\n");
      System.out.print("main category " +  ((JSONArray) userData.get("expensesArray2")).toString() + "\n");
      System.out.print("Auto and Transportation category " +  ((JSONArray) userData.get("expensesArray3")).toString() + "\n");
      System.out.print("Food and Drinks category " +  ((JSONArray) userData.get("expensesArray4")).toString() + "\n");
      System.out.print("Other category " +  ((JSONArray) userData.get("expensesArray5")).toString() + "\n");
      double sum = 0.0;

      // go over the JSONArrays and get each element's value and add it to the total sum
      for(int j = 1; j < 6; j ++){
        for (int i = 0; i < ((JSONArray) userData.get("expensesArray" + j)).length(); i++) {
          sum += ((JSONArray) userData.get("expensesArray" + j)).getDouble(i);
        }
      }

      System.out.print("Your total expenses are: $" + sum + "\n");
    }
  }
}
