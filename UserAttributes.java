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

  private ArrayList<Double> homeExpenses;
  private ArrayList<Double> foodExpenses;
  private ArrayList<Double> educationExpenses;
  private ArrayList<Double> transportationExpenses;
  private ArrayList<Double> otherExpenses;

  /**
   * returns the progress towards amount of money saved
   */
  public double getProgress() {
    // add parameters and code for values used to calculate progress
    double difference;
    return 0;
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

    // use the array corresponding to the given category

    switch (category) { 
      case 'e': //Education category
        spend(educationExpenses, amount);
        
        break;
      case 'h': //Home category
        spend(homeExpenses, amount);
        
        break;
      case 't': //Transporation category
        spend(transportationExpenses, amount);
        
        break;
      case 'f': //Food category
        spend(foodExpenses, amount);

        break;
      case 'o': //Other category
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
    }
  }

}
