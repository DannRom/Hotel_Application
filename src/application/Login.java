package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class Login {

    public TextField usernameField;
    public PasswordField passwordField;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void loginRequest() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();

            String loginQuery = "SELECT * "
                    + "FROM LogIns "
                    + "WHERE username = '" + usernameField.getText() + "' "
                    + "AND password = '" + passwordField.getText() + "'";

            ResultSet resultSet = statement.executeQuery(loginQuery);

            if (resultSet.next()) {
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/mainWindow.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Main Window");
                stage.setScene(new Scene(root));
                stage.sizeToScene();
                stage.show();
                primaryStage.hide();
            }
            else {
                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Login Credentials Are Incorrect",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(ClassNotFoundException | SQLException | IOException cnfex) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

    }

    @FXML
    private void registerRequest() {
        ;
    }
}
