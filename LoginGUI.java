import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;


public class LoginGUI extends Application {
    Stage window;
    private TextField nameInput;
    private TextField baInput;
    private String name;
    private double balance;

    public void start(Stage primaryStage)throws Exception{
        BorderPane grid = new BorderPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        //Welcome label
        Label welcomeLabel = new Label("Welcome to TrackR+");
        welcomeLabel.setStyle("-fx-font-size: 30;");

        //Name Label
        Label nameLabel = new Label("Username");
        nameInput = new TextField();
        nameInput.setMaxWidth(150);

        //balance Label
        Label baLabel = new Label("Balance");
        //balance Input
        baInput = new TextField();
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
            name = nameInput.getText();
            balance = Double.parseDouble(baInput.getText());// put balance in JSON
            TodayGUI today= new TodayGUI();
            today.setBalance(balance);
            Stage differentStage = new Stage();
            today.start(differentStage); //goes to the home screen
            primaryStage.close();
            }
        });

        grid.setTop(welcomeLabel);
        grid.setCenter(centerRoot);

        Scene scene = new Scene(grid, 1000, 600);
        window = primaryStage;
        window.setTitle("TrackR+");
        window.setResizable(false);
        scene.getStylesheets().add("css/Today.css");
        window.setScene(scene);
        window.show();
    }

    /**
     * returns the balance of account
     */
    public double getBalance() {
        return this.balance;
    }

    public static void main(String[] args){
        launch(args);
    }

}
