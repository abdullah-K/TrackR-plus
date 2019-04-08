import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class UserAttributes extends User {

  private ArrayList<Double> homeExpenses = new ArrayList<Double>();
  private ArrayList<Double> foodExpenses = new ArrayList<Double>();
  private ArrayList<Double> educationExpenses = new ArrayList<Double>();
  private ArrayList<Double> transportationExpenses = new ArrayList<Double>();
  private ArrayList<Double> otherExpenses = new ArrayList<Double>();
  public ArrayList<Double> inflowArray = new ArrayList<Double>();
  private char category = 'p';

  /**
   * 
   */
  public UserAttributes() {
    super();
  }

  /**
   * 
   */
  public UserAttributes(String filename) {
    super(filename);

      populateArrayList("homeArray", homeExpenses);
      populateArrayList("foodArray", foodExpenses);
      populateArrayList("educationArray", educationExpenses);
      populateArrayList("transportationArray", transportationExpenses);
      populateArrayList("otherArray", otherExpenses);
      populateArrayList("inflowArray", inflowArray);
    } 

  /**
   * 
   */
  public void populateArrayList(String JSONKey, ArrayList<Double> instanceList){
    // try {
      for (int index = 0; index < super.getUserData().getJSONArray(JSONKey).length(); index++) {
        instanceList.add(Double.parseDouble(String.valueOf(super.getUserData().getJSONArray(JSONKey).get(index))));
      } 
      
    // } catch (FileNotFoundException e) {
    //   //TODO: handle exception
    // }
  }
  
  /**
   * 
   */
  public UserAttributes(String name, double balance, double goal) {
    super(name, balance, goal);
  }

  /**
   * Getter methods for each of the expenses arrays
   */
  public char getCategory(){
    return this.category;
  }
  
  /**
   * 
   */
  public void setCategory(char aChar){
    this.category = aChar; 
  }
  
  public ArrayList<Double> getHomeExpenses() {
    return this.homeExpenses;
  }

  public ArrayList<Double> getFoodExpenses() {
    return this.foodExpenses;
  }

  public ArrayList<Double> getEducationExpenses() {
    return this.educationExpenses;
  }

  public ArrayList<Double> getTransportationExpenses() {
    return this.transportationExpenses;
  }

  public ArrayList<Double> getOtherExpenses() {
    return this.otherExpenses;
  }

  public ArrayList<Double> getInflowArray() {
    return this.inflowArray;
  }

  /**
   * 
   */
  public double getInflowArrayTotal() {
    double inflowSum = 0.0;
    for (int i = 0; i < inflowArray.size(); i++) {
      inflowSum += inflowArray.get(i);
    }
    return inflowSum;
  }

  /**
   * returns the progress towards amount of money saved
   */
  public double getProgress(double totalExpenses, double inflowTotal) {
    inflowTotal = getInflowArrayTotal();
    totalExpenses = getTotalExpenses();
    // add parameters and code for values used to calculate progress
    return (totalExpenses / inflowTotal) * 100;
    // super.getSavingsGoal()
  }

  /**
   * returns total amount of money spent
   */
  public double getTotalExpenses() {
    // add parameters and sum all arrays up to return a value
    double sum = 0;
    double sum1 = 0;
    double sum2 = 0;
    double sum3 = 0;
    double sum4 = 0;
    for (int i = 0; i < homeExpenses.size(); i++) {
      sum = sum + homeExpenses.get(i);
    }
    for (int j = 0; j < foodExpenses.size(); j++) {
      sum1 = sum1 + foodExpenses.get(j);
    }
    for (int k = 0; k < educationExpenses.size(); k++) {
      sum2 = sum2 + educationExpenses.get(k);
    }
    for (int l = 0; l < transportationExpenses.size(); l++) {
      sum3 = sum3 + transportationExpenses.get(l);
    }
    for (int m = 0; m < otherExpenses.size(); m++) {
      sum4 = sum4 + otherExpenses.get(m);
    }

    return sum + sum1 + sum2 + sum3 + sum4;
  }

  /**
   * returns the total money spent of a certain category
   */

  public double getTotalExpensesByCategory(ArrayList<Double> categoryArray) {
    // sum all values in provided array and return a double
    double sum = 0;
    for (int i = 0; i < categoryArray.size(); i++) {
      sum = sum + categoryArray.get(i);
    }
    return sum;
  }

  /**
   * spends money
   */
  public void spend(ArrayList<Double> expenseArray, double amount) {
    // amount to spend has to be positive number and can't be more than what is in
    // balance
    double newBalance = super.getUserBalance();
    if (super.getUserBalance() >= amount && amount > 0) {
      super.setUserBalance(newBalance - amount);
      expenseArray.add(amount);
    }
  }

  /**
   * spends money on a certain category
   */
  public void spendByCategory(char category, double amount) {
    category = getCategory();
    // use the array corresponding to the given category

    switch (category) {
    case 'e': // Education category
      spend(educationExpenses, amount);

      break;
    case 'h': // Home category
      spend(homeExpenses, amount);

      break;
    case 't': // Transporation category
      spend(transportationExpenses, amount);

      break;
    case 'f': // Food category
      spend(foodExpenses, amount);

      break;
    case 'o': // Other category
      spend(otherExpenses, amount);

      break;
    default:
      System.out.print("Invalid option. Please select one of the navigation options.\n");
    }
  }

  /**
   * deposits money into account
   */

  public void deposit(double amount) {
    // copy deposit method from text based? modify if needed
    double newBalance = super.getUserBalance();
    // amount = depositInput.nextDouble();
    if (amount > 0) {
      super.setUserBalance(newBalance + amount);
      inflowArray.add(amount);
    }

  }

  /**
   * 
   */
  public void saveInFile() {
    try {
      File userFile = new File("user.json");
      JSONObject userData = new JSONObject();
      JSONArray homeArray = new JSONArray(homeExpenses);
      JSONArray foodArray = new JSONArray(foodExpenses);
      JSONArray educationArray = new JSONArray(educationExpenses);
      JSONArray transportationArray = new JSONArray(transportationExpenses);
      JSONArray otherArray = new JSONArray(otherExpenses);
      JSONArray inflowArr = new JSONArray(inflowArray);
      userData.put("homeArray", homeArray);
      userData.put("foodArray", foodArray);
      userData.put("educationArray", educationArray);
      userData.put("transportationArray", transportationArray);
      userData.put("otherArray", otherArray);
      userData.put("inflowArray", inflowArr);
      userData.put("userName", getUserName());
      userData.put("userBalance", getUserBalance());
      userData.put("savingsGoal", getSavingsGoal());
      FileWriter write = new FileWriter(userFile.getAbsoluteFile());
      String jsonText = userData.toString();
      write.write(jsonText);
      write.close();
    } catch (FileNotFoundException e) {

    } catch (IOException e) {

    }
  }

}
