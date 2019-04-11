// populate the JSON file with "fake" data in order to demo TrackR+

import java.util.ArrayList;
import java.util.Random;
// import UserAttributes;

public class JSONTester {

    public static void main(String[] args) {
        
        UserAttributes userAttributes = new UserAttributes();
        // userAttributes.saveInFile();
        ArrayList<Double> testArray=  new ArrayList<Double>();
        // fillArray(userAttributes.get)
        fillArray(testArray);

    }

    private static void fillArray(ArrayList list) {
        Random random = new Random();
        double randomExpense;
        for (int index = 0; index <= 30; index++) {
            randomExpense = Double.parseDouble(String.format("%.2f%n", random.nextInt(22) / 10.0));
            // list.add(randomExpense);
        }
    }
}