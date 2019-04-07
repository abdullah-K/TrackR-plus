import java.io.File;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * the Login class is only called upon if it's the user's first time using the
 * app
 */

public class Login {

  private JSONObject userData;

  public void userLoginGUI(String nameInput, double balanceInput, double goal) {
    try {
      goal = 0; 
      // make new User file (in the root directory)
      File userFile = new File("user.json");
      // create user JSON object
      userData = new JSONObject();
      userData.put("userName", nameInput);
      userData.put("userBalance", balanceInput);
      userData.put("savingsGoal", goal);

      // write data (after converting it to a String) to the userFile with fileWriter
      // reference: https://www.tutorialspoint.com/java/io/file_getabsolutefile.htm
      FileWriter fileWriter = new FileWriter(userFile.getAbsoluteFile());
      String jsonText = userData.toString();
      fileWriter.write(jsonText);
      // close the fileWriter to release the system resources being used and to
      // prevent potential file corruption
      fileWriter.close();
    }
    // print error stack traces for java.io exceptions
    catch (FileNotFoundException error) {
      error.printStackTrace();
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

}
