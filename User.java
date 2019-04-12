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
  * constructor that takes in the string of the file name 
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
  * contructor that takes in name, balance and goal 
  */
  public User(String name, double balance, double goal){
    setUserName(name);
    setUserBalance(balance);
    setSavingsGoal(goal);
  }

  /**
  * returns JSONObject, which contains data 
  */
  public JSONObject getUserData(){
    return this.userData;
  }

  /**
  * returns name of user
  */
  public String getUserName() {
    return this.userName;
  }

  /**
  * returns the savings goal
  */
  public double getSavingsGoal() {
    return this.savingsGoal;
  }

  /**
  *retuns balance 
  */
  public double getUserBalance() {
    return this.userBalance;
  }

  /**
  *sets name of user 
  */
  public void setUserName(String userName) {

    this.userName = userName;
  }

  /**
  * sets the savings goal
  */
  public void setSavingsGoal(double savingsGoal) throws NumberFormatException{
    try{
      if (savingsGoal > 0){
        this.savingsGoal = savingsGoal;
        }
      }
    catch (NumberFormatException nfe){
    }
  }

  /**
  *sets the balance of the user
  */
  public void setUserBalance(double userBalance) {
    if(userBalance > 0) {
      this.userBalance = userBalance;
    }
  }

}
