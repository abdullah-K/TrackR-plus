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
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NavBar{
  Stage stage;

  public void setAStage(Stage aStage){
    stage=aStage;
  }

  public VBox getNavBar() {
    /**
     * Navigation bar on the left, buttons for navigating to other tabs
     */
    Button today = new Button("Today");
    Button goals = new Button("Goals");
    Button categories = new Button("Expenses");
    Button exit = new Button("Exit");

    /**
    *Sidebar navigation panel, event handlers for each button
    */
    //Changes the scene to the Today tab
    today.setOnAction(event -> {
      Stage primaryStage= new Stage();
      TodayGUI todayScreen = new TodayGUI();
      todayScreen.start(primaryStage);
      stage.close();
    });

    //Changes the scene to the Goals tab
    goals.setOnAction(event -> {
      Stage primaryStage = new Stage();
      GoalsGUI goalsScreen= new GoalsGUI();
      goalsScreen.start(primaryStage);
      stage.close();
    });

    //Changes the scene to the expenses tab
    categories.setOnAction(event -> {
      Stage primaryStage = new Stage();
      ExpensesGUI expensesScreen = new ExpensesGUI();
      expensesScreen.start(primaryStage);
      stage.close();

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
    VBox navigationPane = new VBox(today, goals, categories,exit);
    navigationPane.getStyleClass().add("navigationPanel");
    navigationPane.getStylesheets().add("css/home.css");
    navigationPane.setSpacing(2);
    return navigationPane;
  }
}
