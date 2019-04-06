import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
// import javafx.scene.layout.Region;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.text.Font;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;
import java.util.ArrayList;
import org.json.JSONObject;

public class TodayGUI extends Application {
  private double currentGoal;
  // private double balance;
  private double amount;
  private JSONObject userData;
  // UserData userDataOBJ = UserData.getUserDataObject();
  // Main main = Main.getMainObject();
  // Login login = Login.getLoginObject();

  public static void main(String[] args) {
    launch(args);
  }

  public void loginCheck(Stage primaryStage, Scene todayScene) {
    // NEED TO CHECK IF FILE EXISTS, TO SEE IF WE GO TO LOGIN OR TODAY
    File userFile = new File("./user.json");// JSON file
    if (userFile.exists()) {
      // JSONObject userData = userDataOBJ.getJSONObjectFromFile(); 
      // userDataOBJ.putJSONObjectIntoFile(userData);
      primaryStage.setScene(todayScene);
    } else {
      login(primaryStage, todayScene);
    }
  }

  /**
   * creates and returns a scroll pane for expenses categories
   */
  public ScrollPane createScrollPane() {
    // https://docs.oracle.com/javafx/2/ui_controls/scrollpane.htm helped
    // maybe have parameter for what category it is, then we can grab the expenses
    // for it
    ArrayList<Label> heyo = new ArrayList<Label>();
    double amount = 33.0;
    for (int i = 0; i < 40; i++) {
      Label tempLabel = new Label(String.valueOf(amount));
      heyo.add(tempLabel);
      amount += 2.5;
      tempLabel.setStyle("-fx-text-fill: #e6e6e6;");

    }

    ScrollPane scrollpane = new ScrollPane();
    scrollpane.getStyleClass().add("scrollPane");
    VBox textst = new VBox();
    // for (int i=0; i<11;i++)
    // testst.getChildren.add(anexpensesarry.get(i));

    for (int i = 0; i < heyo.size(); i++) {
      textst.getChildren().add(heyo.get(i));
    }

    // textst.getChildren().addAll(hi, bye, dfad);
    scrollpane.setMaxWidth(500);
    scrollpane.setMaxHeight(450);
    scrollpane.setContent(textst);
    scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    return scrollpane;
  }

  public void login(Stage stage, Scene today) {
    BorderPane loginRoot = new BorderPane();
    loginRoot.setPadding(new Insets(10, 10, 10, 10));

    // Welcome label
    Label welcomeLabel = new Label("Welcome to TrackR+");
    welcomeLabel.setStyle("-fx-font-size: 30;");

    // Name Label
    Label nameLabel = new Label("Username");
    TextField nameInput = new TextField();
    nameInput.setMaxWidth(150);

    // balance Label
    Label baLabel = new Label("Balance");
    // balance Input
    TextField baInput = new TextField();
    baInput.setMaxWidth(150);

    // Button to login
    Button loginButton = new Button("Log In");
    loginButton.setAlignment(Pos.CENTER_LEFT);

    // Vbox that has all the labels, textfields and buttons in
    VBox centerRoot = new VBox();
    centerRoot.setAlignment(Pos.CENTER);
    centerRoot.setSpacing(10);
    centerRoot.getChildren().addAll(nameLabel, nameInput, baLabel, baInput, loginButton);

    // events for when the user clicks the log in button
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        String name = nameInput.getText(); // GET USERNAME AND PUT IN JSON TOO
        double balance = Double.parseDouble(baInput.getText());// GET BALANCE AND PUT IN JSON TOO
        Login login = new Login();
        login.userLoginGUI(name, balance);
        // userDataOBJ.setJSONObject(main.getJSONObjectFromFile());

        // GOES TO TODAY SCREEN AFTER
        //updateDataAfterLogin();
        stage.setScene(today);
      }
    });

    loginRoot.setTop(welcomeLabel);
    loginRoot.setCenter(centerRoot);

    Scene loginScene = new Scene(loginRoot, 1000, 600);
    loginScene.getStylesheets().add("css/Today.css");
    stage.setScene(loginScene);
    stage.show();
  }

  // private void updateDataAfterLogin() {
  //   if (main.userFileExists()) {
  //     userDataOBJ.setJSONObject(main.getJSONObjectFromFile());
  //   } else {
  //     userDataOBJ.setJSONObject(login.getUserData());
  //   }
  // }

  @Override
  public void start(Stage primaryStage) {
    // TODAY_______________________________________________________________________________________
    BorderPane todayRoot = new BorderPane();
    HBox todayHbox = new HBox();
    todayHbox.setSpacing(145);
    todayRoot.setPadding(new Insets(10, 10, 10, 1));
    Scene todayScene = new Scene(todayHbox, 1000, 600);
    loginCheck(primaryStage, todayScene);
    System.out.println("continuing");
    // updateDataAfterLogin();
    // userData = userDataOBJ.getJSONObject();
    // UserData userData = UserData.getUserDataObject();
    // JSONObject userDataJSON = userData.getJSONObjectFromFile(); // added
    // userData.putJSONObjectIntoFile(userDataJSON);
    // UserData userData = UserData.getUserDataObject();
    // userData = newUserData();
    // userData = main.getJSONObjectFromFile(); //added

    // if(main.userFileExists()){
    // userDataOBJ.setJSONObject(main.getJSONObjectFromFile());
    // }
    // else {
    // userDataOBJ.setJSONObject(login.getUserData());
    // }
    // Buttons for the Navigation bar
    Button today = new Button("Today");
    Button goals = new Button("Goals");
    Button expenses = new Button("Expenses");
    Button exit = new Button("Exit");

    // Label userNameLabel = new Label("Hello, Guest");
    // Labels for the username
    // if(main.userFileExists()) {

    //Label userNameLabel = new Label("Hello, " + userDataOBJ.getUsername());
    // }
     Label userNameLabel = new Label("Hello, " );
    userNameLabel.setPadding(new Insets(15, 0, 25, 0));
    userNameLabel.getStyleClass().add("userNameLabel");
    userNameLabel.getStylesheets().add("css/home.css");

    // TrackR+ Logo
    Image logoImage = new Image("public/trackr-plus_logo.png", true);
    ImageView imageView = new ImageView(logoImage);
    Label imgLabel = new Label();
    imageView.setFitWidth(125);
    imageView.setFitHeight(125);
    imgLabel.setGraphic(imageView);
    imgLabel.setTranslateY(175);

    // Navigation Bar for the Today scene
    VBox navigationBarToday = new VBox(userNameLabel, today, goals, expenses, exit, imgLabel);
    navigationBarToday.setAlignment(Pos.TOP_CENTER);
    navigationBarToday.getStyleClass().add("navigationPanel");
    navigationBarToday.getStylesheets().add("css/home.css");
    navigationBarToday.setSpacing(2);

    // GOALS
    // TAB_______________________________________________________________________________________
    BorderPane goalsRoot = new BorderPane();
    HBox goalsHbox = new HBox();
    goalsHbox.setSpacing(208);
    goalsRoot.setPadding(new Insets(10, 10, 10, 1));

    VBox goalsVbox = new VBox();
    goalsVbox.setSpacing(10);
    goalsVbox.setAlignment(Pos.CENTER);

    // text field to enter amount to save
    TextField setInput = new TextField("Enter an amount...");
    setInput.setMaxWidth(145);
    Button setGoalButton = new Button("Set a new savings goal");
    goalsVbox.getChildren().addAll(setInput, setGoalButton);

    // displays current savings goal
    Label currentGoal = new Label("Your current savings goal is: $" );// get from JSON
                                                                                                    // file
    Label goalsProgress = new Label("This is your savings goal progress");
    currentGoal.setStyle("-fx-font-size: 25;");
    goalsProgress.setStyle("-fx-font-size: 20;");

    // Progress Bar for the Goals Tab
    ProgressBar goalsProgressBar = new ProgressBar(0);
    goalsProgressBar.setMinHeight(15);
    goalsProgressBar.setMinWidth(250);
    // if(userData.has("expensesArray1")){
    //goalsProgressBar.setProgress(userDataOBJ.getProgress());
    // }

    // Top VBox for current goal and goals progress labels
    VBox goalsTop = new VBox();
    goalsTop.setPadding(new Insets(10, 10, 10, 10));
    goalsTop.getChildren().addAll(currentGoal, goalsProgress, goalsProgressBar);
    goalsTop.setSpacing(10);
    goalsTop.setAlignment(Pos.CENTER);

    setGoalButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        double goal = Double.parseDouble(setInput.getText());// send to JSON file
        // goalsProgressBar.setProgress(userData.getProgress());
        currentGoal.setText("Your current savings goal is: $"); // might be redundant
      }
    });

    Scene goalsScene = new Scene(goalsHbox, 1000, 600);
    goalsRoot.setCenter(goalsVbox);
    goalsRoot.setTop(goalsTop);

    Button today1 = new Button("Today");
    Button goals1 = new Button("Goals");
    Button expenses1 = new Button("Expenses");
    Button exit1 = new Button("Exit");

    // TrackR+ Logo
    ImageView imageView2 = new ImageView(logoImage);
    imageView2.setFitWidth(125);
    imageView2.setFitHeight(125);
    Label imgLabel2 = new Label();
    imgLabel2.setGraphic(imageView2);
    imgLabel2.setTranslateY(235);

    // Navigation bar for the Goals tab
    VBox navigationBarGoals = new VBox(today1, goals1, expenses1, exit1, imgLabel2);
    navigationBarGoals.setAlignment(Pos.TOP_CENTER);
    navigationBarGoals.getStyleClass().add("navigationPanel");
    navigationBarGoals.getStylesheets().add("css/home.css");
    navigationBarGoals.setSpacing(2);

    goalsHbox.getChildren().addAll(navigationBarGoals, goalsRoot);
    goalsScene.getStylesheets().add("css/Today.css");
    // ______________________________________________________________________________________________

    // EXPENSES_______________________________________________________________________________________
    BorderPane expensesRoot = new BorderPane();
    HBox expensesHbox = new HBox();
    expensesHbox.setSpacing(240);
    expensesRoot.setPadding(new Insets(10, 10, 10, 1));
    Scene expensesScene = new Scene(expensesHbox, 1000, 600);

    // Label for the expenses info at the top of the screen
    VBox topPane = new VBox();
    Label info, totalExpensesNum;

    // Here need to add the method which can return the expenses

    // label which dispalys the total money spent
    info = new Label("Your total expenses are:"); // get the expenses of everything
    info.setStyle("-fx-font-size: 25;");

    // label which displays the username
    totalExpensesNum = new Label("$");
    totalExpensesNum.setStyle("-fx-font-size: 20;");

    topPane.setPadding(new Insets(10, 10, 10, 10));
    topPane.getChildren().addAll(info, totalExpensesNum);
    topPane.setAlignment(Pos.CENTER);

    // ________________Set Buttons (history & back)________________
    HBox buttonsPane = new HBox();
    buttonsPane.setAlignment(Pos.CENTER);
    buttonsPane.setPadding(new Insets(20, 20, 20, 20));
    buttonsPane.setSpacing(60);

    // Expense categories buttons
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
    Button autoButton = new Button("Expense history for Auto & Transportation");
    autoButton.setMinWidth(300);
    categoryBox.setPadding(new Insets(10, 10, 10, 10));
    categoryBox.setAlignment(Pos.CENTER);
    categoryBox.getChildren().addAll(eduButton, foodButton, homeButton, autoButton, othersButton);

    // education scene labels && buttons
    VBox eduLabelRoot = new VBox();
    eduLabelRoot.setSpacing(10);

    Label totalEduExpenses = new Label("Your total expenses for Education are: ");
    totalEduExpenses.setStyle("-fx-font-size: 20;");
    eduLabelRoot.setAlignment(Pos.TOP_CENTER);
    Button eduBackButton = new Button("Go Back");
    eduLabelRoot.getChildren().addAll(totalEduExpenses, createScrollPane(), eduBackButton);

    // create a new scene for education expenses
    Scene eduScene = new Scene(eduLabelRoot, 1000, 600);
    eduScene.getStylesheets().add("css/Today.css");
    eduButton.setOnAction(e -> primaryStage.setScene(eduScene));

    // Changes the scene to the expenses tab, if the back button is pressed
    eduBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // ___FOOD__
    // label root for food category
    VBox foodLabelRoot = new VBox();
    foodLabelRoot.setSpacing(10);
    Label totalFoodExpenses = new Label("Your total expenses for Food are: ");
    totalFoodExpenses.setStyle("-fx-font-size: 20;");
    foodLabelRoot.setAlignment(Pos.CENTER);
    Button foodBackButton = new Button("Go Back");
    foodLabelRoot.getChildren().addAll(totalFoodExpenses, createScrollPane(), foodBackButton);

    // creates a scene for food
    Scene foodScene = new Scene(foodLabelRoot, 1000, 600);
    foodScene.getStylesheets().add("css/Today.css");

    // Changes the scene to the food screen, if the button is pressed
    foodButton.setOnAction(e -> primaryStage.setScene(foodScene));

    // Changes the scene to the expenses tab, if the back button is pressed
    foodBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // ___House___
    // label root for home
    VBox homeLabelRoot = new VBox();
    homeLabelRoot.setSpacing(10);
    Label totalHomeExpenses = new Label("Your total expenses for Housing are: ");
    totalHomeExpenses.setStyle("-fx-font-size: 20;");
    homeLabelRoot.setAlignment(Pos.CENTER);
    Button homeBackButton = new Button("Go Back");
    homeLabelRoot.getChildren().addAll(totalHomeExpenses, createScrollPane(), homeBackButton);

    // creates a scene for home
    Scene homeScene = new Scene(homeLabelRoot, 1000, 600);
    homeScene.getStylesheets().add("css/Today.css");

    // Changes the scene to the home screen, if the button is pressed
    homeButton.setOnAction(e -> primaryStage.setScene(homeScene));

    // Changes the scene to the main expenses, if the back button is pressed
    homeBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // ___Others___
    // label root for others
    VBox othersLabelRoot = new VBox();
    othersLabelRoot.setSpacing(10);
    Label totalOthersExpenses = new Label("Your total expenses for others are: ");
    totalOthersExpenses.setStyle("-fx-font-size: 20;");
    othersLabelRoot.setAlignment(Pos.CENTER);
    Button othersBackButton = new Button("Go Back");
    othersLabelRoot.getChildren().addAll(totalOthersExpenses, createScrollPane(), othersBackButton);

    // creates a scene for others
    Scene othersScene = new Scene(othersLabelRoot, 1000, 600);
    othersScene.getStylesheets().add("css/Today.css");

    // Changes the scene to the home screen, if the button is pressed
    othersButton.setOnAction(e -> primaryStage.setScene(othersScene));

    // Changes the scene to the main expenses, if the back button is pressed
    othersBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // label root for auto and tansportation
    VBox autoLabelRoot = new VBox();
    autoLabelRoot.setSpacing(10);
    Label totalAutoExpenses = new Label("Your total expenses for Auto Transportation are: ");
    totalAutoExpenses.setStyle("-fx-font-size: 20;");
    autoLabelRoot.setAlignment(Pos.TOP_CENTER);
    Button autoBackButton = new Button("Go Back");
    autoLabelRoot.getChildren().addAll(totalAutoExpenses, createScrollPane(), autoBackButton);

    // creates a scene for auto and transporation category
    Scene autoScene = new Scene(autoLabelRoot, 1000, 600);
    autoScene.getStylesheets().add("css/Today.css");

    // Changes the scene to the other screen, if the button is pressed
    autoButton.setOnAction(e -> primaryStage.setScene(autoScene));

    // Changes the scene to the main expenses, if the back button is pressed
    autoBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    Button today2 = new Button("Today");
    Button goals2 = new Button("Goals");
    Button expenses2 = new Button("Expenses");
    Button exit2 = new Button("Exit");

    // TrackR+ Logo
    Label imgLabel3 = new Label();
    ImageView imageView3 = new ImageView(logoImage);
    imageView3.setFitWidth(125);
    imageView3.setFitHeight(125);
    imgLabel3.setGraphic(imageView3);
    imgLabel3.setTranslateY(235);

    // Navigation bar for the expenses tab
    VBox navigationBarExp = new VBox(today2, goals2, expenses2, exit2, imgLabel3);
    navigationBarExp.setAlignment(Pos.TOP_CENTER);
    navigationBarExp.getStyleClass().add("navigationPanel");
    navigationBarExp.getStylesheets().add("css/home.css");
    navigationBarExp.setSpacing(2);

    // ___Set position for primaryStage___
    expensesRoot.setTop(topPane);
    expensesRoot.setBottom(buttonsPane);
    expensesRoot.setCenter(categoryBox);
    expensesHbox.getChildren().addAll(navigationBarExp, expensesRoot);
    expensesScene.getStylesheets().add("css/Today.css");

    // ___________________________________________________________________________________________

    // Creates labels for balance and savings goal
    VBox labelRoot = new VBox();
    Label acBalance = new Label("Your current balance is: $" ); // get the balance from JSON
    Label acGoal = new Label("Your current savings goal is: $" ); // get the goal from
                                                                                                // JSON
    labelRoot.setAlignment(Pos.CENTER);
    labelRoot.getChildren().addAll(acBalance, acGoal);

    // Three buttons for the bottom, including deposit and spend, and back(optional)
    HBox buttonsRoot = new HBox();
    buttonsRoot.setSpacing(10);
    buttonsRoot.setAlignment(Pos.CENTER);
    Button depositButton = new Button("Deposit");
    Button spendButton = new Button("Spend");
    buttonsRoot.getChildren().addAll(depositButton, spendButton);

    // Deposit scene labels and deposit text field
    VBox depositLabelRoot = new VBox();
    depositLabelRoot.setSpacing(10);
    double lastDepositOfArray = 0;
    Label lastDeposit = new Label("Your account's latest deposit was: ");
    Label lastDepositAmount = new Label("$" + lastDepositOfArray);
    TextField depositField = new TextField();
    Button depositAmount = new Button("Deposit Amount");

    depositAmount.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        amount = Double.parseDouble(depositField.getText());
        if (amount > 0) {
          // lastDepositOfArray += amount
          lastDepositAmount.setText("$" + Double.toString(amount));
          // balance += amount;
        }
      }
    });

    depositField.setMaxWidth(150.0);
    depositLabelRoot.setAlignment(Pos.CENTER);
    depositLabelRoot.getChildren().addAll(lastDeposit, lastDepositAmount, depositField, depositAmount);

    // Deposit scene button root
    HBox depositButtonRoot = new HBox();
    depositButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button depositBackButton = new Button("Go Back");
    depositButtonRoot.getChildren().addAll(depositBackButton);

    // Creates a second border root for the deposit scene
    BorderPane depositRoot = new BorderPane();
    depositRoot.setPadding(new Insets(10, 10, 10, 10));
    depositRoot.setBottom(depositButtonRoot);
    depositRoot.setCenter(depositLabelRoot);

    // Creates a new scene, for the user to deposit money
    Scene depositScene = new Scene(depositRoot, 1000, 600);
    depositScene.getStylesheets().add("css/Today.css");
    // Changes the scene to the deposit screen, if the button is pressed
    depositButton.setOnAction(e -> primaryStage.setScene(depositScene));
    // Changes the scene to the main Today tab, if the back button is pressed
    depositBackButton.setOnAction(e -> primaryStage.setScene(todayScene));

    // Spend scene labels and spend text field
    VBox spendLabelRoot = new VBox();
    spendLabelRoot.setSpacing(15);
    double arrayTotalExpenses = 0.00;
    Label totalExpensesLabel = new Label("Your expenses total to: ");
    Label expensesAmountLabel = new Label("$" + arrayTotalExpenses);
    TextField spendingField = new TextField();
    Button spendAmount = new Button("Spend Amount");

    // spendAmount.setOnAction(new EventHandler<ActionEvent>(){
    // public void handle(ActionEvent e) {
    // amount = Double.parseDouble(spendingField.getText());
    // if (balance - amount > 0 && amount > 0){
    // arrayTotalExpenses += amount;
    // expensesAmountLabel.setText("$" + amount);
    // balance -= amount;
    // }
    // else {
    // totalExpensesLabel.setText("You have insufficient funds! Please try again.");
    // }
    // }});

    spendingField.setMaxWidth(150.0);
    spendLabelRoot.setAlignment(Pos.CENTER);
    spendLabelRoot.getChildren().addAll(totalExpensesLabel, expensesAmountLabel, spendingField, spendAmount);

    // Deposit scene button root
    HBox spendButtonRoot = new HBox();
    spendButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button spendBackButton = new Button("Go Back");
    spendButtonRoot.getChildren().addAll(spendBackButton);

    // Creates a new root for the spend scene
    BorderPane spendRoot = new BorderPane();
    spendRoot.setPadding(new Insets(10, 10, 10, 10));
    spendRoot.setBottom(spendButtonRoot);
    spendRoot.setCenter(spendLabelRoot);

    // Creates a new scene, for the user to spend money
    Scene spendScene = new Scene(spendRoot, 1000, 600);
    spendScene.getStylesheets().add("css/Today.css");
    // Changes the scene to the spend screen, if the button is pressed
    spendButton.setOnAction(e -> primaryStage.setScene(spendScene));
    // Changes the scene to the main Today tab, if the back button is pressed
    spendBackButton.setOnAction(e -> primaryStage.setScene(todayScene));

    // Pie chart for each spending category, to the right of it the labels
    ObservableList<PieChart.Data> expensesData = FXCollections.observableArrayList(new PieChart.Data("Education", 400),
        new PieChart.Data("Home", 200), new PieChart.Data("Food", 300), new PieChart.Data("Auto & Transporation", 100),
        new PieChart.Data("Others", 500));

    PieChart categorizedExpenses = new PieChart(expensesData);
    Label caption = new Label("");
    caption.setTranslateY(400);
    caption.setTranslateX(225);
    for (final PieChart.Data data : categorizedExpenses.getData()) {
      data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
        caption.setText(String.valueOf("$" + data.getPieValue()));
      });
    }

    // Setting the title of the Pie chart
    categorizedExpenses.setTitle("Your expenses");
    categorizedExpenses.setStyle("-fx-text-fill: #333333");
    // legend for the Pie chart
    categorizedExpenses.setLegendVisible(false);
    Group chartRoot = new Group(categorizedExpenses);
    // adds a percentage label
    chartRoot.getChildren().add(caption);

    // NAVBAR______________________________________________________________________________

    // Sidebar navigation panel, event handlers for each button

    today.setOnAction(event -> {
      primaryStage.setScene(todayScene);
    });

    today1.setOnAction(event -> {
      primaryStage.setScene(todayScene);
    });

    today2.setOnAction(event -> {
      primaryStage.setScene(todayScene);
    });

    // Changes the scene to the Goals tab
    goals.setOnAction(event -> {
      primaryStage.setScene(goalsScene);
    });

    goals1.setOnAction(event -> {
      primaryStage.setScene(goalsScene);
    });

    goals2.setOnAction(event -> {
      primaryStage.setScene(goalsScene);
    });

    // Changes the scene to the expenses tab
    expenses.setOnAction(event -> {
      primaryStage.setScene(expensesScene);

    });

    expenses1.setOnAction(event -> {
      primaryStage.setScene(expensesScene);
    });

    expenses2.setOnAction(event -> {
      primaryStage.setScene(expensesScene);
    });

    // Adds an event handler that exits the program, when the user clicks 'exit'
    exit.setOnAction(event -> {
      System.exit(0);
    });

    exit1.setOnAction(event -> {
      System.exit(0);
    });

    exit2.setOnAction(event -> {
      System.exit(0);
    });

    // ___________________________________________________________________________________

    // Display the stage with the main scene
    todayRoot.setTop(labelRoot);
    todayRoot.setCenter(chartRoot);
    todayRoot.setBottom(buttonsRoot);
    todayHbox.getChildren().addAll(navigationBarToday, todayRoot);
    todayScene.getStylesheets().add("css/Today.css");
    primaryStage.setResizable(false);
    primaryStage.setTitle("TrackR+");
    primaryStage.getIcons().add(new Image("file:public/trackr-plus_logo.png"));

    primaryStage.show();
  }

}
