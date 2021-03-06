import java.io.File;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
*the Login class is only called upon if it's the user's first time using the app
*/

public class Login {
  private JSONObject userData;
  private static Login loginObject = null;

  // public JSONObject newUserData() {
  //   return this.userData;
  // }
  public static Login getLoginObject() {
    if (loginObject == null) {
        loginObject = new Login();
    }
    return loginObject;
  }
  
  public JSONObject getUserData() {
    return this.userData;
  }
  public void userLoginGUI(String nameInput, double balanceInput){
    try {
        // make new User file (in the root directory)
        File userFile = new File("user.json");
        // create user JSON object
        userData = new JSONObject();
        UserData mainObject = UserData.getUserDataObject();
        // Main main = Main.getMainObject();
        userData.put("username", nameInput);
        userData.put("userBalance", balanceInput);
        userData.put("savingsGoal", 0.0);

        // Expenses expenses = Expenses.getExpObject();
        // expenses.createExpensesList();
        // expenses.main.putJSONObjectIntoFile(userData);

        // userDataOBJ.setJSONObject(userData);
        // newUserData(userData);
        // write data (after converting it to a String) to the userFile with fileWriter
        // reference: https://www.tutorialspoint.com/java/io/file_getabsolutefile.htm
        FileWriter fileWriter = new FileWriter(userFile.getAbsoluteFile());
        String jsonText = userData.toString();
        fileWriter.write(jsonText);
        // close the fileWriter to release the system resources being used and to prevent potential file corruption
        fileWriter.close();
      }
      // print error stack traces for java.io exceptions
      catch (FileNotFoundException error) {
        error.printStackTrace();
      }
      catch (IOException error) {
        error.printStackTrace();
      }
  }
  
  public void userLogin(){
    try {
      // make new User file (in the root directory)
      File userFile = new File("user.json");
      // create user JSON object
      JSONObject userData = new JSONObject();

      // take input from user and store it as a value in the JSON user object with the "username" key
      Scanner input = new Scanner(System.in);

      System.out.print("\nPlease enter your name: ");
      String name = input.nextLine();
      userData.put("username", name);
      System.out.print("Hello, "+ name + ". Please enter your current balance: ");
      double balance = input.nextDouble();
      userData.put("userBalance", balance);
      userData.put("savingsGoal", 0.0);

      // write data (after converting it to a String) to the userFile with fileWriter
      // reference: https://www.tutorialspoint.com/java/io/file_getabsolutefile.htm
      FileWriter fileWriter = new FileWriter(userFile.getAbsoluteFile());
      String jsonText = userData.toString();
      fileWriter.write(jsonText);
      // close the fileWriter to release the system resources being used and to prevent potential file corruption
      fileWriter.close();
    }
    // print error stack traces for java.io exceptions
    catch (FileNotFoundException error) {
      error.printStackTrace();
    }
    catch (IOException error) {
      error.printStackTrace();
    }
  }
}

