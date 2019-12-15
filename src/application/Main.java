package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    public static final String DIRECTORY = "jdbc:ucanaccess://" +
            "C:\\Users\\Danny\\IdeaProjects\\Hotel_Application\\src\\HotelDB.accdb";

    public static JFrame popUpFrame;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/login.fxml"));
        Parent root = loader.load();
        Login controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
