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
import javafx.scene.layout.Region;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


public class TodayGUI extends Application{
  //private double goal; //to display goal
  //private double balance; //to display and use balance
  private double amount;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * returns balance from JSON file
   */
  public double getBalance(){
    return 0;
  }

  /**
   * set balance in JSON file 
   */
  public void setBalance(){

  }

  /**
   * set goal in JSON file 
   */
  public void setGoal(double goals) {
    //this.currentGoal = goals;
  }

  /**
   * returns goal from JSON file
   */
  public double getGoal(){
    return 0;
  }

  /**
   * creates login scene and shows it
   */
  public void login (Stage stage, Scene today){
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
        String name = nameInput.getText(); //GET USERNAME AND PUT IN JSON TOO
        double balance = Double.parseDouble(baInput.getText());// GET BALANCE AND PUT IN JSON TOO
        //GOES TO TODAY SCREEN AFTER
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

  /**
   * creates and returns a scroll pane for categories
   */
  public ScrollPane createScrollPane(){
    //https://docs.oracle.com/javafx/2/ui_controls/scrollpane.htm helped
    //maybe have parameter for what category it is, then we can grab the expenses for it
    ScrollPane sp= new ScrollPane();
    VBox textst= new VBox();
    //for (int i=0; i<11;i++)
    //testst.getChildren.add(anexpensesarry.get(i));
    
    Text hi= new Text("WASSAP");
    Text bye= new Text("NO");
    Text dfad= new Text("SADSADA");
    textst.getChildren().addAll(hi,bye,dfad);
    sp.setContent(textst); 
    sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    return sp;
    
  }

  @Override
  public void start(Stage primaryStage){
    HBox todayHbox = new HBox();
    Scene todayScene = new Scene(todayHbox, 1000, 600);

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

    Label currentGoal = new Label("Your current savings goal is: $");//GET FROM JSON FILE
    currentGoal.setStyle("-fx-font-size: 25;");

    goalsVbox.getChildren().addAll(setInput,setGoalButton);

    setGoalButton.setOnAction(new EventHandler<ActionEvent>(){//STILL NEEDS SOME WORK
      public void handle(ActionEvent e) {
        double goal = Double.parseDouble(setInput.getText());
        currentGoal.setText("Your current savings goal is: $");
    }});

    Scene goalsScene = new Scene(goalsHbox, 1000, 600);

    goalsRoot.setCenter(goalsVbox);
    goalsRoot.setTop(currentGoal);

    //navgiation bar for goals
    Button today1 = new Button("Today");
    Button goals1 = new Button("Goals");
    Button categories1 = new Button("Expenses");
    Button exit1 = new Button("Exit");

    VBox navigationBarGoals = new VBox(today1, goals1, categories1,exit1);
    navigationBarGoals.getStyleClass().add("navigationPanel");
    navigationBarGoals.getStylesheets().add("css/home.css");
    navigationBarGoals.setSpacing(2);

    goalsHbox.getChildren().addAll(navigationBarGoals,goalsRoot); //need a navigation bar for every scene
    goalsScene.getStylesheets().add("css/Today.css");
    //______________________________________________________________________________________________


    //EXPENSEs_______________________________________________________________________________________

    BorderPane expensesRoot = new BorderPane();
    HBox expensesHbox = new HBox();
    expensesHbox.setSpacing(240);
    expensesRoot.setPadding(new Insets(10,10,10,1));
    Scene expensesScene = new Scene(expensesHbox,1000,600);

    //Label for the expenses info at the top of the screen
    VBox topPane = new VBox();
    Label expensesWelcome, info, username;

    //______________Here need to add the username (which is name input) _________________

    expensesWelcome = new Label("Here are your expenses!");

    //_______________Here need to add the method which can return the expenses _______________

    info = new Label("Your total expenses are:"); //get the expenses of everything

    username = new Label("Username"); //actually get the username

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
    eduLabelRoot.setAlignment(Pos.TOP_CENTER);
    //eduLabelRoot.getChildren().add(totalEduExpenses);

    //the scrollpane for education
    //BorderPane eduPane = new BorderPane();
    //eduPane.setPadding(new Insets(10,10,10,10));

    //education scene button root
    //HBox eduButtonRoot = new HBox();
    //eduButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button eduBackButton = new Button("Go Back");
    //eduButtonRoot.getChildren().addAll(eduBackButton);

    eduLabelRoot.getChildren().addAll(totalEduExpenses,createScrollPane(),eduBackButton);

    // create a new scene for education expenses
    Scene eduScene = new Scene(eduLabelRoot,1000,600);
    eduScene.getStylesheets().add("css/Today.css");

    //creates a new root for the edu
    //eduPane.setBottom(eduButtonRoot);
    //eduPane.setCenter(eduLabelRoot);
    //change the scene to education page
    eduButton.setOnAction(e -> primaryStage.setScene(eduScene));

    //Changes the scene to the main Today tab, if the back button is pressed
    eduBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    //___FOOD__
    //label root for food category
    VBox foodLabelRoot = new VBox();
    foodLabelRoot.setSpacing(10);
    Label totalFoodExpenses = new Label("Your total expenses for Food is : ");
    foodLabelRoot.setAlignment(Pos.CENTER);
    // foodLabelRoot.getChildren().add(totalFoodExpenses);

    //back to home scene button root
    // HBox foodButtonRoot = new HBox();
    // foodButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button foodBackButton = new Button("Go Back");
    // foodButtonRoot.getChildren().addAll(foodBackButton);

    //creates a new scene for Food scene
    // BorderPane foodRoot = new BorderPane();
    // foodRoot.setPadding(new Insets(10,10,10,10));
    // foodRoot.setBottom(foodButtonRoot);
    // foodRoot.setCenter(foodLabelRoot);

    foodLabelRoot.getChildren().addAll(totalFoodExpenses,createScrollPane(),foodBackButton);

    //creates a scene for food
    Scene foodScene = new Scene(foodLabelRoot,1000,600);
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
    // homeLabelRoot.getChildren().add(totalHomeExpenses);

    //back to home scene button root
    // HBox homeButtonRoot = new HBox();
    // homeButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button homeBackButton = new Button("Go Back");
    // homeButtonRoot.getChildren().addAll(homeBackButton);

    //creates a new scene for Home scene
    // BorderPane homeRoot = new BorderPane();
    // homeRoot.setPadding(new Insets(10,10,10,10));
    // homeRoot.setBottom(homeButtonRoot);
    // homeRoot.setCenter(homeLabelRoot);
    
    homeLabelRoot.getChildren().addAll(totalHomeExpenses,createScrollPane(),homeBackButton);

    //creates a scene for home
    Scene homeScene = new Scene(homeLabelRoot,1000,600);
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
    // othersLabelRoot.getChildren().add(totalOthersExpenses);

    //back to home scene button root
    // HBox othersButtonRoot = new HBox();
    // othersButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
    Button othersBackButton = new Button("Go Back");
    // othersButtonRoot.getChildren().addAll(othersBackButton);

    // BorderPane othersRoot = new BorderPane();
    // othersRoot.setPadding(new Insets(10,10,10,10));
    // othersRoot.setBottom(othersButtonRoot);
    // othersRoot.setCenter(othersLabelRoot);
    
    othersLabelRoot.getChildren().addAll(totalOthersExpenses,createScrollPane(),othersBackButton);


    //creates a scene for others
    Scene othersScene = new Scene(othersLabelRoot,1000,600);
    othersScene.getStylesheets().add("css/Today.css");

    //Changes the scene to the home screen, if the button is pressed
    othersButton.setOnAction(e -> primaryStage.setScene(othersScene));

    //Changes the scene to the main expenses, if the back button is pressed
    othersBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

     //label root for auto tansportation
     VBox atLabelRoot = new VBox();
     atLabelRoot.setSpacing(10);
     Label totalAtExpenses = new Label("Your total expenses for Auto Transportation is : ");
     atLabelRoot.setAlignment(Pos.TOP_CENTER);
    //  atLabelRoot.getChildren().add(totalAtExpenses);


     //at scene button root
    //  HBox atButtonRoot = new HBox();
    //  atButtonRoot.setAlignment(Pos.BOTTOM_CENTER);
     Button atBackButton = new Button("Go Back");
    //  atButtonRoot.getChildren().addAll(atBackButton);

     
     //creates a new scene for at scene
    //  BorderPane atRoot = new BorderPane();
    //  atRoot.setPadding(new Insets(10,10,10,10));
    //  atRoot.setBottom(atButtonRoot);
    //  atRoot.setCenter(atLabelRoot);
    
    atLabelRoot.getChildren().addAll(totalAtExpenses,createScrollPane(),atBackButton);


     //creates a scene for at 
     Scene atScene = new Scene(atLabelRoot,1000,600);
     atScene.getStylesheets().add("css/Today.css");

     //Changes the scene to the other screen, if the button is pressed
     atButton.setOnAction(e -> primaryStage.setScene(atScene));

     //Changes the scene to the main expenses, if the back button is pressed
     atBackButton.setOnAction(e -> primaryStage.setScene(expensesScene));

    //navigation bar for expenses
    Button today2 = new Button("Today");
    Button goals2 = new Button("Goals");
    Button categories2 = new Button("Expenses");
    Button exit2 = new Button("Exit");

    VBox navigationBarExp = new VBox(today2, goals2, categories2,exit2);
    navigationBarExp.getStyleClass().add("navigationPanel");
    navigationBarExp.getStylesheets().add("css/home.css");
    navigationBarExp.setSpacing(2);

    //Set position for primaryStage
    expensesRoot.setTop(topPane);
    expensesRoot.setBottom(buttonsPane);
    expensesRoot.setCenter(categoryBox);
    expensesHbox.getChildren().addAll(navigationBarExp,expensesRoot);

    expensesScene.getStylesheets().add("css/Today.css");
    //___________________________________________________________________________________________

    //TODAY_______________________________________________________________________________________
    BorderPane todayRoot = new BorderPane();

    todayHbox.setSpacing(145);
    todayRoot.setPadding(new Insets(10,10,10,1));


    //Button profile = new Button("Settings"); Maybe added later

    //Creates labels for balance and savings goal
    VBox labelRoot = new VBox();
    
    Label acGoal = new Label("Your current savings goal is: $"); //GET GOAL
    labelRoot.setAlignment(Pos.CENTER);
    Label acBalance = new Label("Your current balance is: $"); //GET BALANCE
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

    depositAmount.setOnAction(new EventHandler<ActionEvent>(){//STILL NEEDS SOME WORK
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
    Button spendAmount = new Button("Spend Amount"); //NEEDS SPENDING

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
    Scene spendScene = new Scene(spendRoot, 1000, 600); //SPENDING. NEED TO WORK ON
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

    Button today = new Button("Today");
    Button goals = new Button("Goals");
    Button categories = new Button("Expenses");
    Button exit = new Button("Exit");

    VBox navigationBar = new VBox(today, goals, categories,exit);
    navigationBar.getStyleClass().add("navigationPanel");
    navigationBar.getStylesheets().add("css/home.css");
    navigationBar.setSpacing(2);

    //NAVBAR______________________________________________________________________________

    //Sidebar navigation panel, event handlers for each button
    //Changes the scene to the Today tab
    today.setOnAction(event -> {
      primaryStage.setScene(todayScene);
    });

    today1.setOnAction(event -> {
      primaryStage.setScene(todayScene);
    });

    today2.setOnAction(event -> {
      primaryStage.setScene(todayScene);
    });

    //Changes the scene to the Goals tab
    goals.setOnAction(event -> {
      primaryStage.setScene(goalsScene);
    });

   goals1.setOnAction(event -> {
      primaryStage.setScene(goalsScene);
    });

    goals2.setOnAction(event -> {
      primaryStage.setScene(goalsScene);
    });

    //Changes the scene to the expenses tab
    categories.setOnAction(event -> {
      primaryStage.setScene(expensesScene);

    });

    categories1.setOnAction(event -> {
      primaryStage.setScene(expensesScene);
    });

    categories2.setOnAction(event -> {
      primaryStage.setScene(expensesScene);
    });

    //Adds an event handler that exits the program, when the user clicks 'exit'
    exit.setOnAction(event ->{
            System.exit(0);
    });

    exit1.setOnAction(event -> {
      System.exit(0);
    });

    exit2.setOnAction(event -> {
      System.exit(0);
    });

    //___________________________________________________________________________________

    //navigation bar for today


    //Display the stage with the main scene
    todayRoot.setTop(labelRoot);
    todayRoot.setCenter(chartRoot);
    todayRoot.setBottom(buttonsRoot);
    todayHbox.getChildren().addAll(navigationBar,todayRoot);
    todayScene.getStylesheets().add("css/Today.css");
    primaryStage.setResizable(false);
    primaryStage.setTitle("TrackR+");
    //primaryStage.setScene(todayScene);

    //NEED TO CHECK IF FILE EXISTS, TO SEE IF WE GO TO LOGIN OR TODAY
    File userFile = new File("./user.json");//JSON file
    if (userFile.exists()){
      primaryStage.setScene(todayScene);
    }else{
      login(primaryStage, todayScene);
    }

    primaryStage.show();
  }

}