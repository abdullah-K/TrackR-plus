import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
import java.io.*;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.WindowEvent;

public class TodayGUI extends Application {

  private UserAttributes user;
  private Label userNameLabel;
  private Label accBalanceLabel;
  private Label accGoalsLabel;

  public static void main(String[] args) {
    launch(args);
  }

  public void loginCheck(Stage primaryStage, Scene todayScene) {
    // check if file exists to see if it goes the today scene or login scene 
    userNameLabel = new Label("Hello");
    accBalanceLabel = new Label("Your current balance is $0.0");
    accGoalsLabel = new Label("Your current savings goal is $0.0");
    File userFile = new File("./user.json");
    if (userFile.exists()) {
      user = new UserAttributes("./user.json");
      userNameLabel.setText("Hello, " + user.getUserName());
      accBalanceLabel.setText("Your current balance is $" + user.getUserBalance());
      accGoalsLabel.setText("Your current savings goal is $" + user.getSavingsGoal());
      primaryStage.setScene(todayScene);

    } else {
      user = new UserAttributes();
      login(primaryStage, todayScene);
    }
  }

  /**
   * creates and returns a scroll pane for a certain category
   */
  public ScrollPane createScrollPane(ArrayList<Double> array) {
    // https://docs.oracle.com/javafx/2/ui_controls/scrollpane.htm helped
    // creates a scroll pane with each category's array elements 
    ArrayList<Label> scrollList = new ArrayList<Label>();
    for (int i = 0; i < array.size(); i++) {
      Label tempLabel = new Label(String.valueOf(array.get(i)));
      scrollList.add(tempLabel);
      tempLabel.setStyle("-fx-text-fill: #e6e6e6;");

    }

    ScrollPane scrollpane = new ScrollPane();
    scrollpane.getStyleClass().add("scrollPane");
    VBox expenseslist = new VBox();

    for (int i = 0; i < scrollList.size(); i++) {
      expenseslist.getChildren().add(scrollList.get(i));
    }

    scrollpane.setMaxWidth(500);
    scrollpane.setMinHeight(200);
    scrollpane.setMaxHeight(450);
    scrollpane.setContent(expenseslist);
    scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    return scrollpane;
  }

  /**
   * login scene
   */
  public void login(Stage stage, Scene today) {
    Login login = new Login();

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
        String name = nameInput.getText();
        double balance = Double.parseDouble(baInput.getText());
        double goal = 0.0;
        user.setUserName(name);
        user.setUserBalance(balance);
        user.setSavingsGoal(goal);
        userNameLabel.setText("Hello, " + user.getUserName());
        accBalanceLabel.setText("Your current balance is $" + user.getUserBalance());
        accGoalsLabel.setText("Your current savings goal is $" + user.getSavingsGoal());
        stage.setScene(today);
        login.userLoginGUI(name, balance, goal);
        user.inflowArray.add(user.getUserBalance());
      }
    });

    loginRoot.setTop(welcomeLabel);
    loginRoot.setCenter(centerRoot);

    Scene loginScene = new Scene(loginRoot, 1000, 600);
    loginScene.getStylesheets().add("css/Today.css");
    stage.setScene(loginScene);
    stage.show();
  }

  @Override
  public void start(Stage primaryStage) {
    // TODAY_______________________________________________________________________________________
    BorderPane todayRoot = new BorderPane();
    HBox todayHbox = new HBox();
    todayHbox.setSpacing(145);
    todayRoot.setPadding(new Insets(10, 10, 10, 1));
    Scene todayScene = new Scene(todayHbox, 1000, 600);

    loginCheck(primaryStage, todayScene);

    // nav bar buttons for today
    Button today = new Button("Today");
    Button overview = new Button("Overview");
    Button progressButton = new Button("Progress");
    Button expenses = new Button("Expenses");
    Button exit = new Button("Exit");

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
    imgLabel.setTranslateY(120);

    // Navigation Bar for the Today scene
    VBox navigationBarToday = new VBox(userNameLabel, today, overview, progressButton, expenses, exit, imgLabel);
    navigationBarToday.setAlignment(Pos.TOP_CENTER);
    navigationBarToday.getStyleClass().add("navigationPanel");
    navigationBarToday.getStylesheets().add("css/home.css");
    navigationBarToday.setSpacing(2);

    // GOALS TAB_______________________________________________________________________________________
    BorderPane goalsRoot = new BorderPane();
    HBox goalsHbox = new HBox();
    goalsHbox.setSpacing(208);
    goalsRoot.setPadding(new Insets(10, 10, 10, 1));

    VBox goalsVbox = new VBox();
    goalsVbox.setSpacing(10);
    goalsVbox.setAlignment(Pos.CENTER);
    TextField setInput = new TextField(); // text field to enter amount to save
    setInput.setMaxWidth(145);
    Button setGoalButton = new Button("Set a new savings goal");
    goalsVbox.getChildren().addAll(setInput, setGoalButton);

    // displays current savings goal
    Label currentGoal = new Label("Your current savings goal is: $" + user.getSavingsGoal());
    Label goalsProgress = new Label("You have currently spent: $" + user.getTotalExpenses()); // getTotalExpenses
    Label spendingHistory = new Label("Your account's outflow of money compared to inflow is: ");
    currentGoal.setStyle("-fx-font-size: 25;");
    goalsProgress.setStyle("-fx-font-size: 20;");
    spendingHistory.setStyle("-fx-font-size: 20;");

    // Progress bar for the Goals Tab
    double progress = user.getProgress(user.getTotalExpenses(), user.getInflowArrayTotal());
    ProgressBar goalsProgressBar = new ProgressBar(progress/100); // change 0 to progress.
    ProgressIndicator progressIndicator = new ProgressIndicator(progress/100);
    goalsProgressBar.setMinHeight(15);
    goalsProgressBar.setMinWidth(250);

    // Top VBox for current goal and goals progress labels
    VBox goalsTop = new VBox();
    goalsTop.setPadding(new Insets(10, 10, 10, 10));
    goalsTop.getChildren().addAll(currentGoal, goalsProgress, spendingHistory, goalsProgressBar, progressIndicator);
    goalsTop.setSpacing(10);
    goalsTop.setAlignment(Pos.CENTER);

    setGoalButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        double savingsAmount = Double.parseDouble(setInput.getText());
        user.setSavingsGoal(savingsAmount);
        currentGoal.setText("Your current savings goal is: $" + user.getSavingsGoal());
      }
    });

    Scene goalsScene = new Scene(goalsHbox, 1000, 600);
    goalsRoot.setCenter(goalsVbox);
    goalsRoot.setTop(goalsTop);

    // nav bar buttons for goals
    Button today1 = new Button("Today");
    Button overview1 = new Button("Overview");
    Button progress1 = new Button("Progress");
    Button expenses1 = new Button("Expenses");
    Button exit1 = new Button("Exit");

    // TrackR+ Logo
    ImageView imageView2 = new ImageView(logoImage);
    imageView2.setFitWidth(125);
    imageView2.setFitHeight(125);
    Label imgLabel2 = new Label();
    imgLabel2.setGraphic(imageView2);
    imgLabel2.setTranslateY(187);

    // Navigation bar for the Goals tab
    VBox navigationBarGoals = new VBox(today1, overview1, progress1, expenses1, exit1, imgLabel2);
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

    // label which displays the total money spent
    info = new Label("Your total expenses are:"); // get the expenses of everything @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    info.setStyle("-fx-font-size: 25;");

    totalExpensesNum = new Label("$" + user.getTotalExpenses());
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

    //___EDUCATION___
    VBox eduLabelRoot = new VBox();
    eduLabelRoot.setSpacing(10);
    Label totalEduExpenses = new Label("Your total expenses for Education are: "); 
    totalEduExpenses.setStyle("-fx-font-size: 20;");
    eduLabelRoot.setAlignment(Pos.CENTER);
    Button eduBackButton = new Button("Go Back");
    eduLabelRoot.getChildren().addAll(totalEduExpenses, createScrollPane(user.getEducationExpenses()), eduBackButton);

    // create a new scene for education expenses
    Scene eduScene = new Scene(eduLabelRoot, 1000, 600);
    eduScene.getStylesheets().add("css/Today.css");
    eduButton.setOnAction(e -> primaryStage.setScene(eduScene));
    eduBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // ___FOOD___
    // label root for food category
    VBox foodLabelRoot = new VBox();
    foodLabelRoot.setSpacing(10);
    Label totalFoodExpenses = new Label("Your total expenses for Food are: ");
    totalFoodExpenses.setStyle("-fx-font-size: 20;");
    foodLabelRoot.setAlignment(Pos.CENTER);
    Button foodBackButton = new Button("Go Back");
    foodLabelRoot.getChildren().addAll(totalFoodExpenses, createScrollPane(user.getFoodExpenses()), foodBackButton);

    // creates a scene for food
    Scene foodScene = new Scene(foodLabelRoot, 1000, 600);
    foodScene.getStylesheets().add("css/Today.css");
    foodButton.setOnAction(e -> primaryStage.setScene(foodScene));
    foodBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // ___House___
    // label root for home
    VBox homeLabelRoot = new VBox();
    homeLabelRoot.setSpacing(10);
    Label totalHomeExpenses = new Label("Your total expenses for Housing are: ");
    totalHomeExpenses.setStyle("-fx-font-size: 20;");
    homeLabelRoot.setAlignment(Pos.CENTER);
    Button homeBackButton = new Button("Go Back");
    homeLabelRoot.getChildren().addAll(totalHomeExpenses, createScrollPane(user.getHomeExpenses()), homeBackButton);

    // creates a scene for home
    Scene homeScene = new Scene(homeLabelRoot, 1000, 600);
    homeScene.getStylesheets().add("css/Today.css");
    homeButton.setOnAction(e -> primaryStage.setScene(homeScene));
    homeBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // ___Others___
    // label root for others
    VBox othersLabelRoot = new VBox();
    othersLabelRoot.setSpacing(10);
    Label totalOthersExpenses = new Label("Your total expenses for others are: ");
    totalOthersExpenses.setStyle("-fx-font-size: 20;");
    othersLabelRoot.setAlignment(Pos.CENTER);
    Button othersBackButton = new Button("Go Back");
    othersLabelRoot.getChildren().addAll(totalOthersExpenses, createScrollPane(user.getOtherExpenses()), othersBackButton);

    // creates a scene for others
    Scene othersScene = new Scene(othersLabelRoot, 1000, 600);
    othersScene.getStylesheets().add("css/Today.css");
    othersButton.setOnAction(e -> primaryStage.setScene(othersScene));
    othersBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // label root for auto and tansportation
    VBox autoLabelRoot = new VBox();
    autoLabelRoot.setSpacing(10);
    Label totalAutoExpenses = new Label("Your total expenses for Auto Transportation are: ");
    totalAutoExpenses.setStyle("-fx-font-size: 20;");
    autoLabelRoot.setAlignment(Pos.CENTER);
    Button autoBackButton = new Button("Go Back");
    autoLabelRoot.getChildren().addAll(totalAutoExpenses, createScrollPane(user.getTransportationExpenses()), autoBackButton);

    // creates a scene for auto and transporation category
    Scene autoScene = new Scene(autoLabelRoot, 1000, 600);
    autoScene.getStylesheets().add("css/Today.css");
    autoButton.setOnAction(e -> primaryStage.setScene(autoScene));
    autoBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    // nav bar buttons for expenses
    Button today2 = new Button("Today");
    Button overview2 = new Button("Overview");
    Button progress2 = new Button("Progress");
    Button expenses2 = new Button("Expenses");
    Button exit2 = new Button("Exit");

    // TrackR+ Logo
    Label imgLabel3 = new Label();
    ImageView imageView3 = new ImageView(logoImage);
    imageView3.setFitWidth(125);
    imageView3.setFitHeight(125);
    imgLabel3.setGraphic(imageView3);
    imgLabel3.setTranslateY(187);

    // Navigation bar for the expenses tab
    VBox navigationBarExp = new VBox(today2, overview2, progress2, expenses2, exit2, imgLabel3);
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

    //TODAY CONTINUED_____________________________________________________________________________
    // Creates labels for balance and savings goal
    VBox labelRoot = new VBox();
    // Label accBalance = new Label("Your current balance is: $" +
    // user.getUserBalance());
    // Label accGoal = new Label("Your current savings goal is: $" +
    // user.getSavingsGoal());
    labelRoot.setAlignment(Pos.CENTER);
    labelRoot.getChildren().addAll(accBalanceLabel, accGoalsLabel);

    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    // New scene for spending, allows the user to pick a category and spend in the corresponding category
    BorderPane spendCategoriesRoot = new BorderPane();
    spendCategoriesRoot.setPadding(new Insets(10, 10, 10, 10));

    Scene spendCategoriesScene = new Scene(spendCategoriesRoot, 1000, 600);
    spendCategoriesScene.getStylesheets().add("css/Categories.css");

    //All the category buttons 
    Label chooseSpendCategory = new Label("Choose a category to spend in");
    chooseSpendCategory.setStyle("-fx-font-size: 20");
    Button eduCategoryButton = new Button();
    Button foodCategoryButton = new Button();
    Button homeCategoryButton = new Button();
    Button autoCategoryButton = new Button();
    Button othersCategoryButton = new Button();
    Button spendCategoriesBackButton = new Button("Go back");
    spendCategoriesBackButton.getStylesheets().add("css/Today.css");  

    // https://docs.oracle.com/javafx/2/ui_controls/button.htm
    Image eduIcon = new Image("public/educationIcon.png", 95, 75, false, false);
    Image foodIcon = new Image("public/foodIcon.png", 75, 75, false, false);
    Image homeIcon = new Image("public/homeIcon.png", 75, 75, false, false);
    Image autoIcon = new Image("public/autoIcon.png", 85, 75, false, false);
    Image othersIcon = new Image("public/othersIcon.png", 75, 75, false, false);

    eduCategoryButton.setGraphic(new ImageView(eduIcon));
    foodCategoryButton.setGraphic(new ImageView(foodIcon));
    homeCategoryButton.setGraphic(new ImageView(homeIcon));
    autoCategoryButton.setGraphic(new ImageView(autoIcon));
    othersCategoryButton.setGraphic(new ImageView(othersIcon));

    Label eduCategoryLabel = new Label("Education");
    Label foodCategoryLabel = new Label("Food");
    Label homeCategoryLabel = new Label("Home");
    Label autoCategoryLabel = new Label("Auto");
    Label othersCategoryLabel = new Label("Others");

    VBox eduVBox = new VBox();
    eduVBox.setAlignment(Pos.CENTER);
    eduVBox.getChildren().addAll(eduCategoryButton, eduCategoryLabel);
    VBox foodVBox = new VBox();
    foodVBox.setAlignment(Pos.CENTER);
    foodVBox.getChildren().addAll(foodCategoryButton, foodCategoryLabel);
    VBox homeVBox = new VBox();
    homeVBox.setAlignment(Pos.CENTER);
    homeVBox.getChildren().addAll(homeCategoryButton, homeCategoryLabel);
    VBox autoVBox = new VBox();
    autoVBox.setAlignment(Pos.CENTER);
    autoVBox.getChildren().addAll(autoCategoryButton, autoCategoryLabel);
    VBox othersVBox = new VBox();
    othersVBox.setAlignment(Pos.CENTER);
    othersVBox.getChildren().addAll(othersCategoryButton, othersCategoryLabel);

    HBox spendHBoxRoot = new HBox(eduVBox, foodVBox, homeVBox, autoVBox, othersVBox);
    spendHBoxRoot.setSpacing(10);
    spendHBoxRoot.setAlignment(Pos.CENTER);
    spendHBoxRoot.setPadding(new Insets(10, 10, 10, 10));

    spendCategoriesRoot.setAlignment(spendCategoriesBackButton, Pos.CENTER);
    spendCategoriesRoot.setAlignment(chooseSpendCategory, Pos.CENTER);
    spendCategoriesRoot.setCenter(spendHBoxRoot);
    spendCategoriesRoot.setTop(chooseSpendCategory);
    spendCategoriesRoot.setBottom(spendCategoriesBackButton);

    // Three buttons for the bottom, including deposit and spend, and back(optional)
    HBox buttonsRoot = new HBox();
    buttonsRoot.setSpacing(10);
    buttonsRoot.setAlignment(Pos.CENTER);
    Button depositButton = new Button("Deposit"); // goes to deposit scene
    Button spendButton = new Button("Spend"); // goes to spend scene
    buttonsRoot.getChildren().addAll(depositButton, spendButton);

    // Deposit scene labels and deposit text field
    VBox depositLabelRoot = new VBox();
    depositLabelRoot.setSpacing(10);
    TextField depositField = new TextField();
    Label lastDeposit = new Label("Your account's latest deposit was: ");
    Label lastDepositAmount = new Label("$");
    Button depositAmountButton = new Button("Deposit Amount");

    depositAmountButton.setOnAction(new EventHandler<ActionEvent>() { // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
      public void handle(ActionEvent e) {
        double depositAmount = Double.parseDouble(depositField.getText());
        user.deposit(depositAmount);
        accBalanceLabel.setText("Your current balance is $" + user.getUserBalance());
        lastDepositAmount.setText("$" + depositAmount);
      }
    });

    depositField.setMaxWidth(150.0);
    depositLabelRoot.setAlignment(Pos.CENTER);
    depositLabelRoot.getChildren().addAll(lastDeposit, lastDepositAmount, depositField, depositAmountButton);

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
    depositButton.setOnAction(e -> primaryStage.setScene(depositScene)); // Changes the scene to the deposit screen, if the button is pressed
    depositBackButton.setOnAction(e -> primaryStage.setScene(todayScene)); // Changes the scene to the main Today tab, if the back button is pressed

    // Spend scene labels and spend text field
    VBox spendLabelRoot = new VBox();
    spendLabelRoot.setSpacing(15);
    Label totalExpensesLabel = new Label("Your previous expenses total to: ");
    Label expensesAmountLabel = new Label("$" + user.getTotalExpenses());
    TextField spendingField = new TextField();

    Button spendAmount = new Button("Spend Amount"); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    spendAmount.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        double spendingAmount = Double.parseDouble(spendingField.getText());
        user.spendByCategory(user.getCategory(), spendingAmount);
        expensesAmountLabel.setText("$" + user.getTotalExpenses());
        spendingField.setText("");
        accBalanceLabel.setText("Your current balance is $" + user.getUserBalance());
      }
    });

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
    // Changes the scene to the spend categories screen, if the button is pressed
    spendButton.setOnAction(e -> primaryStage.setScene(spendCategoriesScene));
    // Changes the scene to the main Today tab, if the back button is pressed
    spendBackButton.setOnAction(e-> primaryStage.setScene(spendCategoriesScene));

    //spendCategoriesBackButton.setOnAction(e -> primaryStage.setScene(todayScene));

    eduCategoryButton.setOnAction(event -> {
      primaryStage.setScene(spendScene);
      user.setCategory('e');
      // System.out.println(spendingAmount);
    });

    foodCategoryButton.setOnAction(event -> {
      primaryStage.setScene(spendScene);
        user.setCategory('f');
    });

    homeCategoryButton.setOnAction(event -> {
      primaryStage.setScene(spendScene);
      user.setCategory('h');
    });

    autoCategoryButton.setOnAction(event -> {
      primaryStage.setScene(spendScene);
      user.setCategory('t');
    });

    othersCategoryButton.setOnAction(event -> {
      primaryStage.setScene(spendScene);
      user.setCategory('o');
    });

    // Pie chart for each spending category, to the right of it the labels
    // calls userAttributes getexpensesbycategory to update the piechart
    ObservableList<PieChart.Data> expensesData = FXCollections.observableArrayList(
        new PieChart.Data("Education", user.getTotalExpensesByCategory(user.getEducationExpenses())),
        new PieChart.Data("Home", user.getTotalExpensesByCategory(user.getHomeExpenses())),
        new PieChart.Data("Food", user.getTotalExpensesByCategory(user.getFoodExpenses())),
        new PieChart.Data("Auto & Transporation", user.getTotalExpensesByCategory(user.getTransportationExpenses())),
        new PieChart.Data("Others", user.getTotalExpensesByCategory(user.getOtherExpenses())));
    PieChart categorizedExpenses = new PieChart(expensesData);
    Label caption = new Label("");
    caption.setTranslateY(400);
    caption.setTranslateX(225);
    for (PieChart.Data data : categorizedExpenses.getData()) {
      data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
        caption.setText(String.valueOf("$" + data.getPieValue()));
      });
    }
    // Setting the title of the Pie chart
    categorizedExpenses.setTitle("Your current expenses are: ");
    categorizedExpenses.setStyle("-fx-text-fill: #333333");
    // legend for the Pie chart
    categorizedExpenses.setLegendVisible(false);
    Group chartRoot = new Group(categorizedExpenses);
    // adds a percentage label
    chartRoot.getChildren().add(caption);

    // back button to today that updates pie chart
    spendCategoriesBackButton.setOnAction(event -> {
      //categorizedExpenses.setData(expensesData);
      ObservableList<PieChart.Data> expensesData2 = FXCollections.observableArrayList(
          new PieChart.Data("Education", user.getTotalExpensesByCategory(user.getEducationExpenses())),
          new PieChart.Data("Home", user.getTotalExpensesByCategory(user.getHomeExpenses())),
          new PieChart.Data("Food", user.getTotalExpensesByCategory(user.getFoodExpenses())),
          new PieChart.Data("Auto & Transporation", user.getTotalExpensesByCategory(user.getTransportationExpenses())),
          new PieChart.Data("Others", user.getTotalExpensesByCategory(user.getOtherExpenses())));
      categorizedExpenses.setData(expensesData2);
      for (PieChart.Data data2 : categorizedExpenses.getData()) {
        data2.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
          caption.setText(String.valueOf("$" + data2.getPieValue()));
        });
      }
      primaryStage.setScene(todayScene);
    });

    //Overview page_______________________________________________________________________________________

    GridPane overviewRoot = new GridPane();
    HBox overviewHBox = new HBox();
    overviewHBox.setSpacing(8);
    overviewRoot.getStylesheets().add("css/Categories.css");
    overviewRoot.setVgap(8);
    overviewRoot.setHgap(8);
    overviewRoot.setPadding(new Insets(15, 15, 15, 15));

    VBox overviewIncome = new VBox();
    overviewIncome.setStyle("-fx-background-color: #1D2027; -fx-background-radius: 15;");
    overviewIncome.setAlignment(Pos.CENTER);
    overviewIncome.setMaxSize(225, 175);
    overviewIncome.setMinSize(225,175);
    VBox overviewExpenses = new VBox();
    overviewExpenses.setStyle("-fx-background-color: #1D2027; -fx-background-radius: 15;");
    overviewExpenses.setAlignment(Pos.CENTER);
    overviewExpenses.setMaxSize(225, 175);
    overviewExpenses.setMinSize(225,175);
    VBox overviewProfit = new VBox();
    overviewProfit.setStyle("-fx-background-color: #1D2027; -fx-background-radius: 15;");
    overviewProfit.setAlignment(Pos.CENTER);
    overviewProfit.setMaxSize(225, 175);
    overviewProfit.setMinSize(225,175);

    Label ovIncomeNumber = new Label("$" + user.getInflowArrayTotal());
    ovIncomeNumber.setStyle("-fx-font-size:30");
    Label ovIncomeLabel = new Label("Income");
    ovIncomeLabel.setStyle("-fx-text-fill: #30A4FB; -fx-font-family: SanSerif; -fx-font-size:20");
    Label ovExpensesNumber = new Label("$" + user.getTotalExpenses());
    ovExpensesNumber.setStyle("-fx-font-size:30");
    Label ovExpensesLabel = new Label("Expenses");
    ovExpensesLabel.setStyle("-fx-text-fill: #30A4FB; -fx-font-family: SanSerif; -fx-font-size:20");

    overviewIncome.getChildren().addAll(ovIncomeNumber, ovIncomeLabel);
    overviewExpenses.getChildren().addAll(ovExpensesNumber, ovExpensesLabel);

    // TrackR+ Logo
    ImageView imageView4 = new ImageView(logoImage);
    imageView4.setFitWidth(125);
    imageView4.setFitHeight(125);
    Label imgLabel4 = new Label();
    imgLabel4.setGraphic(imageView4);
    imgLabel4.setTranslateY(187);

    //Navigation bar for the overview page
    Button today3 = new Button("Today");
    Button overview3 = new Button("Overview");
    Button progress3 = new Button("Progress");
    Button expenses3 = new Button("Expenses");
    Button exit3 = new Button("Exit");
    VBox navigationBarOv = new VBox(today3, overview3, progress3, expenses3, exit3, imgLabel4);
    navigationBarOv.setAlignment(Pos.TOP_CENTER);
    navigationBarOv.getStyleClass().add("navigationPanel");
    navigationBarOv.getStylesheets().add("css/home.css");
    navigationBarOv.setSpacing(2);

    overviewRoot.setConstraints(overviewIncome, 0, 0);
    overviewRoot.setConstraints(overviewExpenses, 5, 0);
    overviewRoot.setConstraints(overviewProfit, 10, 0);
    overviewRoot.getChildren().addAll(overviewIncome, overviewExpenses, overviewProfit);
    overviewHBox.getChildren().addAll(navigationBarOv, overviewRoot);
    Scene overviewScene = new Scene(overviewHBox, 1000, 600);
    overviewScene.getStylesheets().add("css/categories.css");


    // NAVBAR______________________________________________________________________________
    // Sidebar navigation panel, event handlers for each button
    //sets scene to today tab
    today.setOnAction(event -> {
      accGoalsLabel.setText("Your current savings goal is: $" + user.getSavingsGoal());
      primaryStage.setScene(todayScene);
    });

    today1.setOnAction(event -> {
      accGoalsLabel.setText("Your current savings goal is: $" + user.getSavingsGoal());
      primaryStage.setScene(todayScene);
    });

    today2.setOnAction(event -> {
      accGoalsLabel.setText("Your current savings goal is: $" + user.getSavingsGoal());
      primaryStage.setScene(todayScene);
    });

    today3.setOnAction(event -> {
        accGoalsLabel.setText("Your current savings goal is: $" + user.getSavingsGoal());
        primaryStage.setScene(todayScene);
      });

    //Adds an event handler that changes the scene to the overview tab
    overview.setOnAction(event -> {
        ovExpensesNumber.setText("$" + user.getTotalExpenses());
        ovIncomeNumber.setText("$" + user.getInflowArrayTotal());
        primaryStage.setScene(overviewScene);
    });

    overview1.setOnAction(event -> {
        ovExpensesNumber.setText("$" + user.getTotalExpenses());
        ovIncomeNumber.setText("$" + user.getInflowArrayTotal());
        primaryStage.setScene(overviewScene);
    });

    overview2.setOnAction(event -> {
        ovExpensesNumber.setText("$" + user.getTotalExpenses());
        ovIncomeNumber.setText("$" + user.getInflowArrayTotal());
        primaryStage.setScene(overviewScene);
    });

    overview3.setOnAction(event -> {
        ovExpensesNumber.setText("$" + user.getTotalExpenses());
        ovIncomeNumber.setText("$" + user.getInflowArrayTotal());
        primaryStage.setScene(overviewScene);
    });

    // Changes the scene to the Goals tab
    progressButton.setOnAction(event -> {
      currentGoal.setText("Your current savings goal is: $"); 
      goalsProgress.setText("You have currently spent: $" + user.getTotalExpenses()); // getTotalExpenses
      spendingHistory.setText("Your account's outflow of money compared to inflow is: ");
      currentGoal.setText("Your current savings goal is: $" + user.getSavingsGoal());
      double progressPercent = user.getProgress(user.getTotalExpenses(), user.getInflowArrayTotal());
      goalsProgressBar.setProgress(progressPercent/100);
      progressIndicator.setProgress(progressPercent/100);
      primaryStage.setScene(goalsScene);
    });

    progress1.setOnAction(event -> {
      currentGoal.setText("Your current savings goal is: $"); 
      goalsProgress.setText("You have currently spent: $" + user.getTotalExpenses()); // getTotalExpenses
      spendingHistory.setText("Your account's outflow of money compared to inflow is: ");
      currentGoal.setText("Your current savings goal is: $" + user.getSavingsGoal());
      double progressPercent = user.getProgress(user.getTotalExpenses(), user.getInflowArrayTotal());
      goalsProgressBar.setProgress(progressPercent/100);
      progressIndicator.setProgress(progressPercent/100);
      primaryStage.setScene(goalsScene);
    });

    progress2.setOnAction(event -> {
      currentGoal.setText("Your current savings goal is: $"); 
      goalsProgress.setText("You have currently spent: $" + user.getTotalExpenses()); // getTotalExpenses
      spendingHistory.setText("Your account's outflow of money compared to inflow is: ");
      currentGoal.setText("Your current savings goal is: $" + user.getSavingsGoal());
      double progressPercent = user.getProgress(user.getTotalExpenses(), user.getInflowArrayTotal());
      goalsProgressBar.setProgress(progressPercent/100);
      progressIndicator.setProgress(progressPercent/100);
      primaryStage.setScene(goalsScene);
    });

    progress3.setOnAction(event -> {
      currentGoal.setText("Your current savings goal is: $"); 
      goalsProgress.setText("You have currently spent: $" + user.getTotalExpenses()); // getTotalExpenses
      spendingHistory.setText("Your account's outflow of money compared to inflow is: ");
      currentGoal.setText("Your current savings goal is: $" + user.getSavingsGoal());
      double progressPercent = user.getProgress(user.getTotalExpenses(), user.getInflowArrayTotal());
      goalsProgressBar.setProgress(progressPercent/100);
      progressIndicator.setProgress(progressPercent/100);
      primaryStage.setScene(goalsScene);
      });

    // Changes the scene to the expenses tab
    expenses.setOnAction(event -> {
      totalExpensesNum.setText("$" + user.getTotalExpenses());
      primaryStage.setScene(expensesScene);

    });

    expenses1.setOnAction(event -> {
      totalExpensesNum.setText("$" + user.getTotalExpenses());
      primaryStage.setScene(expensesScene);
    });

    expenses2.setOnAction(event -> {
      totalExpensesNum.setText("$" + user.getTotalExpenses());
      primaryStage.setScene(expensesScene);
    });

    expenses3.setOnAction(event -> {
      totalExpensesNum.setText("$" + user.getTotalExpenses());
      primaryStage.setScene(expensesScene);
      });

    // Adds an event handler that exits the program, when the user clicks 'exit'
    exit.setOnAction(event -> {
      user.saveInFile();
      System.exit(0);
    });

    exit1.setOnAction(event -> {
      user.saveInFile();
      System.exit(0);
    });

    exit2.setOnAction(event -> {
      user.saveInFile();
      System.exit(0);
    });

    exit3.setOnAction(event -> {
        user.saveInFile();
        System.exit(0);
      });

    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent event) {
          user.saveInFile();
      }
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
