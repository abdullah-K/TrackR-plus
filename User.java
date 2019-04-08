import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class User {

  private String userName;
  private double savingsGoal;
  private double userBalance = 0.0;
  private JSONObject userData;

  /**
  *default constructor 
  */
  public User() {
    setUserBalance(0.0);
    setUserName("");
    setSavingsGoal(0.0);
  }

  /**
  *constructor that takes in the string of the filename 
  */
  public User(String filename) {
    try {
      String jsonContent = new Scanner(new File(filename)).useDelimiter("\\Z").next();
      userData = new JSONObject(jsonContent);
      this.userBalance = ((Number) userData.get("userBalance")).doubleValue();
      this.savingsGoal = ((Number) userData.get("savingsGoal")).doubleValue();
      this.userName = ((String) userData.get("userName"));
      
    } catch (FileNotFoundException e) {

    }
  }

  /**
  * constructor that takes in the name, balance and goal
  */
  public User(String name, double balance, double goal){
    setUserName(name);
    setUserBalance(balance);
    setSavingsGoal(goal);
  }

  /**
  * returns the JSONObject with data 
  */
  public JSONObject getUserData(){
    return this.userData;
  }

  /**
  * returns the name of the user 
  */
  public String getUserName() {
    return this.userName;
  }

  /**
  * returns the savings goal of the user 
  */
  public double getSavingsGoal() {
    return this.savingsGoal;
  }

  /**
  * returns the balance of the user 
  */
  public double getUserBalance() {
    return this.userBalance;
  }

  /**
  * sets the name of the user 
  */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
  * sets the savings goal of the user 
  */
  public void setSavingsGoal(double savingsGoal) {
    // add non-negative/non-weird-character condition here
    this.savingsGoal = savingsGoal;
  }

  /**
  * sets the balance of the user 
  */
  public void setUserBalance(double userBalance) {
    this.userBalance = userBalance;
  }
}
