import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class User {

  private String userName;
  private double savingsGoal;
  private double userBalance = 0.0;
  private JSONObject userData;

  public User() {
    setUserBalance(0.0);
    setUserName("");
    setSavingsGoal(0.0);
  }

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

  public User(String name, double balance, double goal){
    setUserName(name);
    setUserBalance(balance);
    setSavingsGoal(goal);
  }

  public JSONObject getUserData(){
    return this.userData;
  }

  public String getUserName() {
    return this.userName;
  }

  public double getSavingsGoal() {
    return this.savingsGoal;
  }

  public double getUserBalance() {
    return this.userBalance;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setSavingsGoal(double savingsGoal) {
    // add non-negative/non-weird-character condition here
    this.savingsGoal = savingsGoal;
  }

  public void setUserBalance(double userBalance) {
    this.userBalance = userBalance;
  }

}
