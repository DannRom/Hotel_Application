package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class GuestTab {

    public TableView guestTableView;

    public TableColumn guestIDCol;
    public TableColumn firstNameCol;
    public TableColumn lastNameCol;
    public TableColumn checkInCol;
    public TableColumn checkOutCol;
    public TableColumn emailAddressCol;

    public Button displayGuestFormButton;

    @FXML
    public void initialize() {
        guestIDCol.setCellValueFactory(new PropertyValueFactory("guestID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        checkInCol.setCellValueFactory(new PropertyValueFactory("checkIn"));
        checkOutCol.setCellValueFactory(new PropertyValueFactory("checkOut"));
        emailAddressCol.setCellValueFactory(new PropertyValueFactory("emailAddress"));

        guestTableView.setItems(getGuest());
    }

    @FXML
    public void searchGuests() {
        Guest guest = new Guest("dunderID", "firstDunder", "lastDunder", "dunderIn", "dunderOut", "dunEmailAddress");
        guestTableView.getItems().add(guest);
        //guestTableView.setItems(getGuest());
        //guestTableView.refresh();
    }

    @FXML
    public ObservableList<Guest> getGuest() {
        ObservableList<Guest> guests = FXCollections.observableArrayList();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();
            String guestsQuery = "SELECT guestID, firstName, lastName, checkIn, checkOut, emailAddress "
                    + "FROM Guests ";
            ResultSet resultSet = statement.executeQuery(guestsQuery);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            String guestID = null, firstName = null, lastName = null, checkIn = null, checkOut = null, emailAddress = null;
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    if (i == 1)
                        guestID = "" + resultSet.getObject(i);
                    if (i == 2)
                        firstName = "" + resultSet.getObject(i);
                    if (i == 3)
                        lastName = "" + resultSet.getObject(i);
                    if (i == 4)
                        checkIn = "" + resultSet.getObject(i);
                    if (i == 5)
                        checkOut = "" + resultSet.getObject(i);
                    if (i == 6)
                        emailAddress = "" + resultSet.getObject(i);
                }
                guests.add(new Guest(guestID, firstName, lastName, checkIn, checkOut, emailAddress));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    private void tempMethod() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String guestQuery = "SELECT guestID, firstName, lastName, checkIn, checkOut "
                    + "FROM Guests ";
            ResultSet resultSet = statement.executeQuery(guestQuery);
            resultSet.close();
            statement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayGuestForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/guestForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Guest Entry Window");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayAddChargesWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/addCharges.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Charge Window");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayBillingWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/billing.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Billing Window");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayGuestToRoom() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/guestToRoom.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Select Guest and Room");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
