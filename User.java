import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class User {

  private String userName;
  private double savingsGoal;
  private double userBalance = 0.0;

  public String getUserName(){
    return this.userName;
  }

  public double getSavingsGoal(){
    return this.savingsGoal;
  }

  public double getUserBalance(){
    return this.userBalance;
  }

  public void setUserName(String userName){
    this.userName = userName;
  }

  public void setSavingsGoal(double savingsGoal){
    // add non-negative/non-weird-character condition here
    this.savingsGoal = savingsGoal;
  }

  public void setUserBalance(double userBalance){
    this.userBalance = userBalance;
  }

  public User(String userName, double savingsGoal, double userBalance){
    setUserName(userName);
    setSavingsGoal(savingsGoal);
    setUserBalance(userBalance);
  }
 
   public void saveInFile(){
    try{
      File userFile = new File("user.json");
      JSONObject userData = new JSONObject();
      userData.put("userName", userName);
      userData.put("userBalance", userBalance);
      userData.put("savingsGoal", savingsGoal);
      FileWriter write = new FileWriter(userFile.getAbsoluteFile());
      String jsonText = userData.toString();
      write.write(jsonText);
      write.close();
    }catch(FileNotFoundException e){
      
    }catch(IOException e){

    }
    
  }
  
  public void setupFromFile(){
    try{
      String jsonContent = new Scanner(new File("./user.json")).useDelimiter("\\Z").next();
      JSONObject userData = new JSONObject(jsonContent);
      this.userBalance = ((Number)userData.get("userBalance")).doubleValue();
      this.savingsGoal = ((Number)userData.get("savingsGoal")).doubleValue();
      this.userName = ((String)userData.get("userName"));
    }catch(FileNotFoundException e) {
        
    }
  }


  public User(){
    setUserBalance(0.0);
    setUserName("");
    setSavingsGoal(0.0);
  }


}
