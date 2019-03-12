import java.util.Scanner;
import org.json.JSONObject;
import java.io.File;
import org.json.JSONArray;

/**
*the Goals class shows the currents savings goal, allows the user to set a new savings goal
*/
public class Goals{

  private double savingsGoal;
  Home home= new Home();
  private JSONObject userData = home.getJSONObject();//JSON object

  /**
  *prompts the user for navigation in goals class
  */
  public void goalsNavigation() {
    System.out.println("To navigate the Goals tab, use the following commands: ");
    System.out.println("Press 'c' to display your current savings goal");
    System.out.println("Press 'n' to set a new savings goal");
    System.out.println("Press 'b' to go back");
    System.out.println("Press 'q' to quit the application");

    Scanner navInput = new Scanner(System.in);
    char option;

    //doesn't allow user to enter char other than c,n,b or q
    while (true) {
      option = navInput.next().toLowerCase().charAt(0);
      if(option=='c'||option=='n'||option=='b'||option=='q'){
        break;
      }
      else {
        // System.out.println(option);
        System.out.print("Invalid option. Please select one of the navigation options.\n");
        option = navInput.next().toLowerCase().charAt(0);
      }
    }

    switch(option) {
      case 'c':
        System.out.println("Your current savings goal is: " + savingsGoal);
        goalsNavigation();
        break;
      case 'n':
        setNewGoal();
        goalsNavigation();
        break;
      case 'b':
        home.navigation(); //fix this
        break;
      case 'q':
        System.out.print("Goodbye\n");
        System.exit(0);
      default:
        System.out.print("Invalid option. Please select one of the navigation options.\n");
    }

  }

  /**
  *returns the goal of money to save
  */
  public double getGoal(){
    return savingsGoal;

  }

  /**
  *prompts user to enter a new goal of money to save
  */
  public void setNewGoal(){
    // create a new key in the JSON file (if it doesn't already exist) which will be used for
    userData.put("savingsGoal", 0);
    System.out.println("Please enter your new savings goal: ");
    Scanner goal = new Scanner(System.in);
    double save = goal.nextDouble();
    userData.put("savingsGoal", save);
    savingsGoal = save;
    // code for saving the goal to the json File
    System.out.println("Your savings new goal is: "+ savingsGoal);
    home.putJSONObjectIntoFile(userData); //fix this

  }
  // /**
  // *shows goals page
  // */
  // public static void displayGoalsTab(){
  //   //read from goals array, and print the corresponding goals
  //   System.out.println("This is the goals tab");
  //   //System.out.println("Your savings goal is: "+ savingsGoal);
  // }
}
