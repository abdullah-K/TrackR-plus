// populate the JSON file with "fake" data in order to demo TrackR+
// mimics the spending of a user with semi-random values

import java.util.ArrayList;
import java.util.Random;
public class JSONTester {

    static UserAttributes user;
    
    public static void main(String[] args) {
        user = new UserAttributes("John", 0.0, 0.0);
        user.deposit(5000.0);
        user.setSavingsGoal(1500);
        fillArray('e');
        fillArray('h');
        fillArray('t');
        fillArray('f');
        fillArray('o');
        user.saveInFile();
    }

    private static void fillArray(char category) {
        // assign "bias values" to each category
        // e.g. one *should* spend more in education than in transporation
        int biasValue = 64;
        switch (category) {
            case 'e': //education expenses
                biasValue *= 20;
                break;
            case 'h': //home expenses
                biasValue *= 10;
                break;
            case 't': //transporation expenses
                biasValue *= 2;
                break;
            case 'f':   //food expenses
                biasValue *= 5;
                break;
            case 'o': //other expenses
                biasValue *= 2;
                break;
            default:
                break;
        }
        Random random = new Random();
        for (int index = 0; index <= 5000 / biasValue; index++) {
            user.spendByCategory(category, Double.parseDouble(String.format("%.2f%n", random.nextInt(biasValue) / 10.0)));
        }
    }
}
