import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.geometry.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.event.EventHandler;

public class ExpensesGUI extends Application{

  //maybe create one back button and the back button has that one method to go back

    // private static ArrayList<String> testList = new ArrayList<String>();
    private Stage expensesStage;
    private static Scene scene;

    public static void main(String[] args){
        // testList.add("sdjkfnisehfiushfiushiuboihfishgiuerhgiu");
        launch(args);
    }

    public Stage getexpensesGUI() {
        return expensesStage;
    }

    public void start(Stage primaryStage) {

        //Creates the pane first
        BorderPane root = new BorderPane();
        expensesStage = primaryStage;
        HBox main = new HBox();
        main.setSpacing(240);
        root.setPadding(new Insets(10,10,10,1));
        scene = new Scene(main,1000,600);
        expensesStage.setScene(scene);

        NavBar navBar = new NavBar();
        navBar.setAStage(primaryStage);
        VBox navigationBar = navBar.getNavBar();

        //Label for the expenses info at the top of the screen
        VBox topPane = new VBox();
        Label expensesWelcome, info, username;

        //______________Here need to add the username (which is name input) _________________

        expensesWelcome = new Label("Here are your expenses!");

        //_______________Here need to add the method which can return the expenses _______________

        info = new Label("Your total expenses are:");

        username = new Label("Username");

        topPane.setPadding(new Insets(10,10,10,10));
        topPane.getChildren().add(expensesWelcome);
        topPane.getChildren().add(username);
        topPane.getChildren().add(info);
        topPane.setAlignment(Pos.CENTER);

        //ScrollPane for the arraylist(Expenses History)
        // VBox main = new VBox();
        // ScrollPane scrollPane = new ScrollPane();
        // VBox.setVgrow(scrollPane,Priority.ALWAYS);
        // main.getChildren().add(scrollPane);

        // VBox scroll = new VBox();


        // // ready to add arraylist

        // for (int i = 0; i < testList.size(); ++i)
        //     scroll.getChildren().add(new Label(testList.get(i)));

        // scrollPane.setContent(scroll);
        // scrollPane.setVmax(5);
        // scrollPane.setPrefSize(50,50);
        // scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        //scroll.getChildren().add(scrollPane);




        //________________Set Buttons (history & back)________________

        HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setPadding(new Insets(20,20,20,20));
        buttonsPane.setSpacing(60);
        // buttonsPane.getChildren().add(new Button("Your expenses history"));

        //Expense categories buttons
        VBox categoryBox = new VBox();
        categoryBox.setSpacing(10);
        Button eduButton = new Button("Expense history for Education");
        eduButton.setMinWidth(300);
        Button foodButton = new Button("Expense history for Food");
        foodButton.setMinWidth(300);
        Button homeButton = new Button("Expense history for Home");
        homeButton.setMinWidth(300);
        Button othersButton = new Button("Expense history for Others");
        othersButton.setMinWidth(300);
        Button atButton = new Button("Expense history for Auto & Transportation");
        atButton.setMinWidth(300);
        categoryBox.setPadding(new Insets(10,10,10,10));
        categoryBox.setAlignment(Pos.CENTER);
        categoryBox.getChildren().addAll(eduButton,foodButton,homeButton,othersButton,atButton);

        //education scene labels && buttons.
        VBox eduLabelRoot = new VBox();
        eduLabelRoot.setSpacing(10);

        Label totalEduExpenses = new Label("Your total expenses for Education is : ");
        eduLabelRoot.setAlignment(Pos.CENTER);
        eduLabelRoot.getChildren().add(totalEduExpenses);

        //the scrollpane for education button
        BorderPane eduPane = new BorderPane();
        eduPane.setPadding(new Insets(10,10,10,10));

        // create a new scene for education expenses
        Scene eduScene = new Scene(eduPane,1000,600);
        eduScene.getStylesheets().add("css/Today.css");

        //education scene button root
        HBox eduButtonRoot = new HBox();
        eduButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
        Button eduBackButton = new Button("Go Back");
        eduButtonRoot.getChildren().addAll(eduBackButton);

        //creates a new root for the edu
        eduPane.setPadding(new Insets(10,10,10,10));
        eduPane.setBottom(eduButtonRoot);
        eduPane.setCenter(eduLabelRoot);
        //change the scene to education page
        eduButton.setOnAction(e -> primaryStage.setScene(eduScene));

        //Changes the scene to the main Today tab, if the back button is pressed
        eduBackButton.setOnAction(e -> primaryStage.setScene(scene));

        //label root for food category
        VBox foodLabelRoot = new VBox();
        foodLabelRoot.setSpacing(10);
        Label totalFoodExpenses = new Label("Your total expenses for Food is : ");
        foodLabelRoot.setAlignment(Pos.CENTER);
        foodLabelRoot.getChildren().add(totalFoodExpenses);

        //back to home scene button root
        HBox foodButtonRoot = new HBox();
        foodButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
        Button foodBackButton = new Button("Go Back");
        foodButtonRoot.getChildren().addAll(foodBackButton);

        //creates a new scene for Food scene
        BorderPane foodRoot = new BorderPane();
        foodRoot.setPadding(new Insets(10,10,10,10));
        foodRoot.setBottom(foodButtonRoot);
        foodRoot.setCenter(foodLabelRoot);

        //creates a scene for food
        Scene foodScene = new Scene(foodRoot,1000,600);
        foodScene.getStylesheets().add("css/Today.css");

        //Changes the scene to the food screen, if the button is pressed
        foodButton.setOnAction(e -> primaryStage.setScene(foodScene));

        //Changes the scene to the main expenses, if the back button is pressed
        foodBackButton.setOnAction(e -> primaryStage.setScene(scene));

        //___House___
        //label root for home
        VBox homeLabelRoot = new VBox();
        homeLabelRoot.setSpacing(10);
        Label totalHomeExpenses = new Label("Your total expenses for Housing is : ");
        homeLabelRoot.setAlignment(Pos.CENTER);
        homeLabelRoot.getChildren().add(totalHomeExpenses);

        //back to home scene button root
        HBox homeButtonRoot = new HBox();
        homeButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
        Button homeBackButton = new Button("Go Back");
        homeButtonRoot.getChildren().addAll(homeBackButton);

        //creates a new scene for Home scene
        BorderPane homeRoot = new BorderPane();
        homeRoot.setPadding(new Insets(10,10,10,10));
        homeRoot.setBottom(homeButtonRoot);
        homeRoot.setCenter(homeLabelRoot);

        //creates a scene for home
        Scene homeScene = new Scene(homeRoot,1000,600);
        homeScene.getStylesheets().add("css/Today.css");

        //Changes the scene to the home screen, if the button is pressed
        homeButton.setOnAction(e -> primaryStage.setScene(homeScene));

        //Changes the scene to the main expenses, if the back button is pressed
        homeBackButton.setOnAction(e -> primaryStage.setScene(scene));

        //___Others___
        //label root for others
        VBox othersLabelRoot = new VBox();
        othersLabelRoot.setSpacing(10);
        Label totalOthersExpenses = new Label("Your total expenses for others is : ");
        othersLabelRoot.setAlignment(Pos.CENTER);
        othersLabelRoot.getChildren().add(totalOthersExpenses);

        //back to home scene button root
        HBox othersButtonRoot = new HBox();
        othersButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
        Button othersBackButton = new Button("Go Back");
        othersButtonRoot.getChildren().addAll(othersBackButton);

        //creates a new scene for Home scene
        BorderPane othersRoot = new BorderPane();
        othersRoot.setPadding(new Insets(10,10,10,10));
        othersRoot.setBottom(othersButtonRoot);
        othersRoot.setCenter(othersLabelRoot);

        //creates a scene for home
        Scene othersScene = new Scene(othersRoot,1000,600);
        othersScene.getStylesheets().add("css/Today.css");

        //Changes the scene to the home screen, if the button is pressed
        othersButton.setOnAction(e -> primaryStage.setScene(othersScene));

        //Changes the scene to the main expenses, if the back button is pressed
        othersBackButton.setOnAction(e -> primaryStage.setScene(scene));

        //___Set position for primaryStage___
        root.setTop(topPane);
        root.setBottom(buttonsPane);
        root.setCenter(categoryBox);
        main.getChildren().addAll(navigationBar,root);

        //___initialize___
        expensesStage.setTitle("TrackR+");
        expensesStage.setResizable(false);
        scene.getStylesheets().add("css/Today.css");
        expensesStage.setScene(scene);
        expensesStage.show();


    }
}
