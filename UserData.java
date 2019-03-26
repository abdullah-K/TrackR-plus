import org.json.JSONObject;
import java.io.File;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * UserData
 */
public class UserData {
    // Main main = Main.getMainObject();
    Main main = Main.getMainObject();
    // private JSONObject userData = main.getJSONObject();
    private JSONObject userData; //added
    private static UserData userDataObject = null;

    public JSONObject getJSONObjectFromFile() {
        try {
            // read the user.json file as a String
            String jsonContent = new Scanner(new File("./user.json")).useDelimiter("\\Z").next();// JSON file
            // create a JSONObject from the String `jsonContent`
            userData = new JSONObject(jsonContent); // JSON object
            return userData;
        }
        // catch the exception if `user.json` doesn't exist (can't happen because Login
        // takes care of that)
        catch (FileNotFoundException error) {
            error.printStackTrace();
        }
        return null;
    }

    public JSONObject getJSONObject() {
        return userData;
    }

    public void setJSONObject(JSONObject newUserData) {
        this.userData = newUserData;
        putJSONObjectIntoFile(newUserData);
    }
    
    /**
    public void setUserData(JSONObject data) {
        this.userData = data;
        setJSONObject(data);
    } */

    /**
     * put the userData JSONObject (after updating it and stuff) back into the
     * user.json file
     */
    public void putJSONObjectIntoFile(JSONObject data) {
        try {
            File userFile = new File("user.json"); // JSON file
            FileWriter fileWriter = new FileWriter(userFile.getAbsoluteFile());
            String jsonText = data.toString();
            fileWriter.write(jsonText);
            // close the fileWriter to release the system resources being used and to
            // prevent potential file corruption
            fileWriter.close();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static UserData getUserDataObject() {
        if (userDataObject == null) {
            System.out.println("new object");
            userDataObject = new UserData();
        }
        else {
            System.out.println("same object");
        }
        return userDataObject;
    }

    /**
     * returns balance from JSON file
     */
    public String getBalance() {
        if (userData == null) {
            return "0.00";
        } else {
            return String.valueOf(userData.get("userBalance"));
        }
    }

    /**
     * set balance in JSON file
     */
    public void setBalance() {

    }

    /**
     * set goal in JSON file
     */
    public void setGoal(double goals) {
        // this.currentGoal = goals;
    }

    /**
     * returns goal from JSON file
     */
    public String getSavingsGoal() {
        if (userData == null) {
            return "0.70";
        } else {
            return String.valueOf(userData.get("savingsGoal"));
        }
    }

    Expenses expenses = Expenses.getExpObject();

    /**
     * returns the progress of money saved
     */
    public double getProgress() {
        double progress = 1.0;
        if (userData == null || !userData.has("expensesArray1")) {
            return 0.7;
        } else {
            double totalExpenses = expenses.getTotalExpenses(); // interpretation of progress
            double savingsGoal = (Double) userData.get("savingsGoal");
            progress = savingsGoal / totalExpenses;
            if (progress >= 1.0) {
                return 1.0;
            }
            return progress;
        }
    }

    /**
     * returns the money total of money spent in every category
     */
    public String getTotalExpenses() {
        double sum = 0.0;
        if (userData == null || !userData.has("expensesArray1")) {
            return "0.00";
        } else {
            /*
             * for(int j = 1; j < 6; j ++){ for (int i = 0; i < ((JSONArray)
             * userData.get("expensesArray" + j)).length(); i++) { sum += ((JSONArray)
             * userData.get("expensesArray" + j)).getDouble(i); } }
             */
            return String.valueOf(sum);
        }
    }

    /**
     * returns the username from the JSON file
     */
    public String getUsername() {
        //userData = main.getJSONObject();
        if (userData == null) {
            return "Guest";
        } else {
            return String.valueOf(userData.get("username"));
        }
    }

}

