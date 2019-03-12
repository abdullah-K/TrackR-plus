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
  private double balance;
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

  /**
   * sets the balance
   */
  public void setBalance(double balance) {
      this.balance = balance;
  }

  @Override
  public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();
    HBox main = new HBox();
    main.setSpacing(145);
    root.setPadding(new Insets(10,10,10,1));
    Scene scene = new Scene(main, 1000, 600);
    primaryStage.setScene(scene);

    //Button profile = new Button("Settings"); Maybe added later

    //Creates labels for balance and savings goal
    VBox labelRoot = new VBox();
    Label acBalance = new Label("Your current balance is: $" + balance); //get the balance from JSON
    Label acGoal = new Label("Your current savings goal is: $" + currentGoal); //get the goal from JSON
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
            balance += amount;
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
    depositBackButton.setOnAction(e -> primaryStage.setScene(scene));

    //Spend scene labels and spend text field
    VBox spendLabelRoot = new VBox();
    spendLabelRoot.setSpacing(15);
    double arrayTotalExpenses = 0.00;
    Label totalExpensesLabel = new Label("Your expenses total to: ");
    Label expensesAmountLabel = new Label("$" + arrayTotalExpenses);
    TextField spendingField = new TextField();
    Button spendAmount = new Button("Spend Amount");

    spendAmount.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e) {
          amount = Double.parseDouble(spendingField.getText());
          if (balance - amount > 0 && amount > 0){
            //arrayTotalExpenses += amount;
            expensesAmountLabel.setText("$" + amount);
            balance -= amount;
          }
          else {
            totalExpensesLabel.setText("You have insufficient funds! Please try again.");
          }
    }});

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
    spendBackButton.setOnAction(e -> primaryStage.setScene(scene));


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


    //gets the navigation sidebar
    NavBar navBar = new NavBar();
    navBar.setAStage(primaryStage);
    VBox navigationBar = navBar.getNavBar();

    //Display the stage with the main scene
    root.setTop(labelRoot);
    root.setCenter(chartRoot);
    root.setBottom(buttonsRoot);
    main.getChildren().addAll(navigationBar,root);
    scene.getStylesheets().add("css/Today.css");
    primaryStage.setResizable(false);
    primaryStage.setTitle("TrackR+");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

}
