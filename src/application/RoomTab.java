package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class RoomTab {

    public TableView roomTableView;

    public TableColumn roomID;
    public TableColumn roomName;
    public TableColumn luxuryOrCottage;
    public TableColumn roomSize;
    public TableColumn patioOrForrest;
    public TableColumn oneOrTwo;
    public TableColumn price;
    public TableColumn extra;

    @FXML
    public void initialize() {
        roomID.setCellValueFactory(new PropertyValueFactory("roomID"));
        roomName.setCellValueFactory(new PropertyValueFactory("roomName"));
        luxuryOrCottage.setCellValueFactory(new PropertyValueFactory("luxuryOrCottage"));
        roomSize.setCellValueFactory(new PropertyValueFactory("roomSize"));
        patioOrForrest.setCellValueFactory(new PropertyValueFactory("patioOrForrest"));
        oneOrTwo.setCellValueFactory(new PropertyValueFactory("oneOrTwo"));
        price.setCellValueFactory(new PropertyValueFactory("price"));
        extra.setCellValueFactory(new PropertyValueFactory("extra"));

        roomTableView.setItems(getRoom());
    }

    @FXML
    public ObservableList<Room> getRoom() {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();
            String roomsQuery = "SELECT Rooms.roomID, roomName, luxuryOrCottage,"
                    + "roomSize, patioOrForrest, oneOrTwo, price, extra "
                    + "FROM Rooms INNER JOIN RoomTypes "
                    + "ON Rooms.roomTypeID = RoomTypes.roomTypeID";
            ResultSet resultSet = statement.executeQuery(roomsQuery);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            String roomID = null, roomName = null, luxuryOrCottage = null, roomSize = null, patioOrForrest = null, oneOrTwo = null, price = null, extra = null;
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    if (i == 1)
                        roomID = "" + resultSet.getObject(i);
                    if (i == 2)
                        roomName = "" + resultSet.getObject(i);
                    if (i == 3)
                        luxuryOrCottage = "" + resultSet.getObject(i);
                    if (i == 4)
                        roomSize = "" + resultSet.getObject(i);
                    if (i == 5)
                        patioOrForrest = "" + resultSet.getObject(i);
                    if (i == 6)
                        oneOrTwo = "" + resultSet.getObject(i);
                    if (i == 7)
                        price = "" + resultSet.getObject(i);
                    if (i == 8)
                        extra = "" + resultSet.getObject(i);
                }
                rooms.add(new Room(roomID, roomName, luxuryOrCottage, roomSize, patioOrForrest, oneOrTwo, price, extra));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @FXML
    private void displayNewRoomWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/newRoom.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New Room Window");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayPriceSheet() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/priceSheet.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Price Sheet");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
