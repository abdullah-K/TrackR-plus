import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class TodayGUI extends Application{
  private double currentGoal;
  //private double balance;
  private double amount;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * sets the goal
   */
  public void setGoals(double goals) {
    this.currentGoal = goals;
  }

  @Override
  public void start(Stage primaryStage){

    Button today = new Button("Today");
    Button goals = new Button("Goals");
    Button categories = new Button("Expenses");
    Button exit = new Button("Exit");

    VBox navigationBar = new VBox(today, goals, categories,exit);
    navigationBar.getStyleClass().add("navigationPanel");
    navigationBar.getStylesheets().add("css/home.css");
    navigationBar.setSpacing(2);

    //LOGIN_______________________________________________________________________________________
    BorderPane loginRoot = new BorderPane();
    loginRoot.setPadding(new Insets(10, 10, 10, 10));
    //Welcome label
    Label welcomeLabel = new Label("Welcome to TrackR+");
    welcomeLabel.setStyle("-fx-font-size: 30;");

    //Name Label
    Label nameLabel = new Label("Username");
    TextField nameInput = new TextField();
    nameInput.setMaxWidth(150);

    //balance Label
    Label baLabel = new Label("Balance");
    //balance Input
    TextField baInput = new TextField();
    baInput.setMaxWidth(150);

    //Button to login
    Button loginButton = new Button("Log In");
    loginButton.setAlignment(Pos.CENTER_LEFT);

    VBox centerRoot = new VBox();
    centerRoot.setAlignment(Pos.CENTER);
    centerRoot.setSpacing(10);
    centerRoot.getChildren().addAll(nameLabel,nameInput,baLabel,baInput,loginButton);

    loginButton.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e) {
        String name = nameInput.getText();
        double balance = Double.parseDouble(baInput.getText());// put balance in JSON
        //To today screen?
        }
    });

    loginRoot.setTop(welcomeLabel);
    loginRoot.setCenter(centerRoot);

    Scene loginScene = new Scene(loginRoot, 1000, 600);
    loginScene.getStylesheets().add("css/Today.css");
    //_______________________________________________________________________________________

    //GOALs_______________________________________________________________________________________
    BorderPane goalsRoot = new BorderPane();
    HBox goalsHbox = new HBox();
    goalsHbox.setSpacing(208);
    goalsRoot.setPadding(new Insets(10, 10, 10, 1));

    VBox goalsVbox = new VBox();
    goalsVbox.setSpacing(10);
    goalsVbox.setAlignment(Pos.CENTER);

    TextField setInput = new TextField("Enter an amount...");
    setInput.setMaxWidth(145);
    Button setGoalButton = new Button("Set a new savings goal");

    Label currentGoal = new Label("Your current savings goal is: $");//get from JSON file
    currentGoal.setStyle("-fx-font-size: 25;");

    goalsVbox.getChildren().addAll(setInput,setGoalButton);

    setGoalButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e) {
        double goal = Double.parseDouble(setInput.getText());
        currentGoal.setText("Your current savings goal is: $");
        //set goals
    }});

    Scene goalsScene = new Scene(goalsHbox, 1000, 600);

    //Changes the scene to the Goals tab
    goals.setOnAction(event -> {
      primaryStage.setScene(goalsScene);
    });

    goalsRoot.setCenter(goalsVbox);
    goalsRoot.setTop(currentGoal);
    goalsHbox.getChildren().addAll(navigationBar,goalsRoot); //need a navigation bar for every scene
    goalsScene.getStylesheets().add("css/Today.css");
    //_______________________________________________________________________________________


    //EXPENSEs_______________________________________________________________________________________
    BorderPane expensesRoot = new BorderPane();
    HBox expensesHbox = new HBox();
    expensesHbox.setSpacing(240);
    expensesRoot.setPadding(new Insets(10,10,10,1));
    Scene expensesScene = new Scene(expensesHbox,1000,600);

    //Changes the scene to the expenses tab
    categories.setOnAction(event -> {
      primaryStage.setScene(expensesScene);

    });

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
    eduBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

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
    foodBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

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
    homeBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

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
    othersBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    //___Set position for primaryStage___
    expensesRoot.setTop(topPane);
    expensesRoot.setBottom(buttonsPane);
    expensesRoot.setCenter(categoryBox);
    expensesHbox.getChildren().addAll(navigationBar,expensesRoot);

    //___initialize___
    expensesScene.getStylesheets().add("css/Today.css");
    //_______________________________________________________________________________________

    //TODAY_______________________________________________________________________________________
    BorderPane todayRoot = new BorderPane();
    HBox todayHbox = new HBox();
    todayHbox.setSpacing(145);
    todayRoot.setPadding(new Insets(10,10,10,1));
    Scene todayScene = new Scene(todayHbox, 1000, 600);

    //Button profile = new Button("Settings"); Maybe added later

    //Creates labels for balance and savings goal
    VBox labelRoot = new VBox();
    Label acBalance = new Label("Your current balance is: $"); //get the balance from JSON
    Label acGoal = new Label("Your current savings goal is: $"); //get the goal from JSON
    labelRoot.setAlignment(Pos.CENTER);
    labelRoot.getChildren().addAll(acBalance, acGoal);

    //Three buttons for the bottom, including deposit and spend, and back(optional)
    HBox buttonsRoot = new HBox();
    buttonsRoot.setSpacing(10);
    buttonsRoot.setAlignment(Pos.CENTER);
    Button depositButton = new Button("Deposit");
    Button spendButton = new Button("Spend");
    buttonsRoot.getChildren().addAll(depositButton,spendButton);

    //Deposit scene labels and deposit text field
    VBox depositLabelRoot = new VBox();
    depositLabelRoot.setSpacing(10);
    double lastDepositOfArray = 0;
    Label lastDeposit = new Label("Your account's latest deposit was: ");
    Label lastDepositAmount = new Label("$" + lastDepositOfArray);
    TextField depositField = new TextField();
    Button depositAmount = new Button("Deposit Amount");

    depositAmount.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e) {
          amount = Double.parseDouble(depositField.getText());
          if (amount > 0){
            //lastDepositOfArray += amount
            lastDepositAmount.setText("$" + Double.toString(amount));
            //balance += amount;
          }
    }});

    depositField.setMaxWidth(150.0);
    depositLabelRoot.setAlignment(Pos.CENTER);
    depositLabelRoot.getChildren().addAll(lastDeposit,lastDepositAmount,depositField,depositAmount);

    //Deposit scene button root
    HBox depositButtonRoot = new HBox();
    depositButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button depositBackButton = new Button("Go Back");
    depositButtonRoot.getChildren().addAll(depositBackButton);

    //Creates a second border root for the deposit scene
    BorderPane depositRoot = new BorderPane();
    depositRoot.setPadding(new Insets(10,10,10,10));
    depositRoot.setBottom(depositButtonRoot);
    depositRoot.setCenter(depositLabelRoot);

    //Creates a new scene, for the user to deposit money
    Scene depositScene = new Scene(depositRoot, 1000, 600);
    depositScene.getStylesheets().add("css/Today.css");
    //Changes the scene to the deposit screen, if the button is pressed
    depositButton.setOnAction(e -> primaryStage.setScene(depositScene));
    //Changes the scene to the main Today tab, if the back button is pressed
    depositBackButton.setOnAction(e -> primaryStage.setScene(todayScene));

    //Spend scene labels and spend text field
    VBox spendLabelRoot = new VBox();
    spendLabelRoot.setSpacing(15);
    double arrayTotalExpenses = 0.00;
    Label totalExpensesLabel = new Label("Your expenses total to: ");
    Label expensesAmountLabel = new Label("$" + arrayTotalExpenses);
    TextField spendingField = new TextField();
    Button spendAmount = new Button("Spend Amount");

    //spendAmount.setOnAction(new EventHandler<ActionEvent>(){
        //public void handle(ActionEvent e) {
          //amount = Double.parseDouble(spendingField.getText());
          //if (balance - amount > 0 && amount > 0){
            //arrayTotalExpenses += amount;
            //expensesAmountLabel.setText("$" + amount);
            //balance -= amount;
          //}
          //else {
            //totalExpensesLabel.setText("You have insufficient funds! Please try again.");
          //}
    //}});

    spendingField.setMaxWidth(150.0);
    spendLabelRoot.setAlignment(Pos.CENTER);
    spendLabelRoot.getChildren().addAll(totalExpensesLabel,expensesAmountLabel,spendingField,spendAmount);

    //Deposit scene button root
    HBox spendButtonRoot = new HBox();
    spendButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button spendBackButton = new Button("Go Back");
    spendButtonRoot.getChildren().addAll(spendBackButton);

    //Creates a new root for the spend scene
    BorderPane spendRoot = new BorderPane();
    spendRoot.setPadding(new Insets(10,10,10,10));
    spendRoot.setBottom(spendButtonRoot);
    spendRoot.setCenter(spendLabelRoot);

    //Creates a new scene, for the user to spend money
    Scene spendScene = new Scene(spendRoot, 1000, 600);
    spendScene.getStylesheets().add("css/Today.css");
    //Changes the scene to the spend screen, if the button is pressed
    spendButton.setOnAction(e -> primaryStage.setScene(spendScene));
    //Changes the scene to the main Today tab, if the back button is pressed
    spendBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    //Pie chart for each spending category, to the right of it the labels
    ObservableList<PieChart.Data> expensesData = FXCollections.observableArrayList(
    new PieChart.Data("Education", 400),
    new PieChart.Data("Home", 200),
    new PieChart.Data("Food", 300),
    new PieChart.Data("Auto & Transporation", 100),
    new PieChart.Data("Others", 500)
    );

    PieChart categorizedExpenses = new PieChart(expensesData);
    Label caption = new Label("");
    caption.setTranslateY(400);
    caption.setTranslateX(225);
    for (final PieChart.Data data : categorizedExpenses.getData()) {
      data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
	      caption.setText(String.valueOf("$" + data.getPieValue()));
	  });
    }

    //Setting the title of the Pie chart
    categorizedExpenses.setTitle("Your expenses");
    categorizedExpenses.setStyle("-fx-text-fill: #333333");
    //legend for the Pie chart
    categorizedExpenses.setLegendVisible(false);
    Group chartRoot = new Group(categorizedExpenses);
    //adds a percentage label
    chartRoot.getChildren().add(caption);

    //NAVBAR______________________________________________________________________________

    //
    //Sidebar navigation panel, event handlers for each button
    //
    //Changes the scene to the Today tab
    today.setOnAction(event -> {
      primaryStage.setScene(todayScene);
    });

    //Button profile = new Button("Settings"); Maybe added later

    //Adds an event handler that exits the program, when the user clicks 'exit'
    exit.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e) {
            System.exit(0);
    }});

    /**
     * Navigation bar on the left
     */

    //___________________________________________________________________________________

    //Display the stage with the main scene
    todayRoot.setTop(labelRoot);
    todayRoot.setCenter(chartRoot);
    todayRoot.setBottom(buttonsRoot);
    todayHbox.getChildren().addAll(navigationBar,todayRoot);
    todayScene.getStylesheets().add("css/Today.css");
    primaryStage.setResizable(false);
    primaryStage.setTitle("TrackR+");
    primaryStage.setScene(todayScene);
    primaryStage.show();
  }

}
