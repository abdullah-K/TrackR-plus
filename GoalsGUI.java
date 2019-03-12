import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;

public class GoalsGUI extends Application{
    private double goal = 0.0;

    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        HBox main = new HBox();
        main.setSpacing(208);
        root.setPadding(new Insets(10, 10, 10, 1));

        NavBar navBar = new NavBar();
        navBar.setAStage(primaryStage);
        VBox navigationBar = navBar.getNavBar();

        VBox goalsRoot = new VBox();
        goalsRoot.setSpacing(10);
        goalsRoot.setAlignment(Pos.CENTER);

        TextField setInput = new TextField("Enter an amount...");
        setInput.setMaxWidth(145);
        Button setGoalButton = new Button("Set a new savings goal");

        Label currentGoal = new Label("Your current savings goal is: $" + goal);//get from JSON file
        currentGoal.setStyle("-fx-font-size: 25;");

        goalsRoot.getChildren().addAll(setInput,setGoalButton);

        setGoalButton.setOnAction(new EventHandler<ActionEvent>(){
          public void handle(ActionEvent e) {
            goal = Double.parseDouble(setInput.getText());
            currentGoal.setText("Your current savings goal is: $" + Double.toString(goal));
            TodayGUI todaygui = new TodayGUI();
            todaygui.setGoals(goal);
        }});


        Scene scene = new Scene(main, 1000, 600);
        root.setCenter(goalsRoot);
        root.setTop(currentGoal);
        main.getChildren().addAll(navigationBar,root);
        scene.getStylesheets().add("css/Today.css");
        primaryStage.setTitle("TrackR+");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * returns the goal of money to save
     */
    public double getGoal() {
        return this.goal;
    }

    public static void main(String[] args){
        launch(args);
    }
}
