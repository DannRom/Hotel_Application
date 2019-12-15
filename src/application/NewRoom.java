package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class NewRoom {

    public TextField roomName, roomStatus, luxuryOrCottage, roomSize, patioOrForrest, oneOrTwo, price, extra;

    @FXML
    private void initialize() {
        roomStatus.setEditable(false);
        luxuryOrCottage.setEditable(false);
        roomSize.setEditable(false);
        patioOrForrest.setEditable(false);
        oneOrTwo.setEditable(false);
    }

    @FXML
    private void addNewRoom() {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();
            String query = "SELECT * "
                    + "FROM Rooms "
                    + "WHERE roomName = '" + roomName.getText() + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // If the Room does exist, update
            if (resultSet.next()) {
                String roomID = resultSet.getString("roomID");
                resultSet.close();
                statement.close();

                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String roomsQuery = "SELECT * "
                        + "FROM Rooms "
                        + "WHERE roomID = '" + roomID + "'";
                ResultSet roomsResultSet = statement.executeQuery(roomsQuery);
                String roomTypeID = "r" + ( (int) (Math.random() * 10000) );

                roomsResultSet.next();
                updateRooms(roomsResultSet, roomID, roomTypeID);
                roomsResultSet.updateRow();

                roomsResultSet.close();
                statement.close();

                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String roomTypesQuery = "SELECT * "
                        + "FROM roomTypes ";
                ResultSet roomTypesResultSet = statement.executeQuery(roomTypesQuery);

                roomTypesResultSet.next();
                updateRoomTypes(roomTypesResultSet, roomTypeID);
                roomTypesResultSet.updateRow();

                roomTypesResultSet.close();
                statement.close();

                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Update Successful",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            // If the room doesn't exist, insert
            else {
                resultSet.close();
                statement.close();

                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String roomQuery = "SELECT * "
                        + "FROM Rooms ";
                ResultSet roomsResultSet = statement.executeQuery(roomQuery);
                String roomID = "r" + ( (int) (Math.random() * 10000) );
                String roomTypeID = "rt" + ( (int) (Math.random() * 10000) );

                roomsResultSet.moveToInsertRow();
                updateRooms(roomsResultSet, roomID, roomTypeID);
                roomsResultSet.insertRow();

                roomsResultSet.close();
                statement.close();

                // Insert Into RoomType Table
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String roomTypesQuery = "SELECT * "
                        + "FROM roomTypes ";
                ResultSet roomTypesResultSet = statement.executeQuery(roomTypesQuery);

                roomTypesResultSet.moveToInsertRow();
                updateRoomTypes(roomTypesResultSet, roomTypeID);
                roomTypesResultSet.insertRow();

                roomTypesResultSet.close();
                statement.close();

                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Insertion Successful",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRooms(ResultSet roomsResultSet, String roomID, String roomTypeID) throws SQLException {
        roomsResultSet.updateString("roomID", roomID);
        roomsResultSet.updateString("roomName", roomName.getText());
        roomsResultSet.updateString("roomStatus", roomStatus.getText());
        roomsResultSet.updateString("roomCleaning", "");
        roomsResultSet.updateString("roomLaundry", "");
        roomsResultSet.updateString("roomTypeID", roomTypeID);
    }

    private void updateRoomTypes(ResultSet roomTypesResultSet, String roomTypeID) throws SQLException {
        roomTypesResultSet.updateString("roomTypeID", roomTypeID);
        roomTypesResultSet.updateString("luxuryOrCottage", luxuryOrCottage.getText());
        roomTypesResultSet.updateString("roomSize", roomSize.getText());
        roomTypesResultSet.updateString("patioOrForrest", patioOrForrest.getText());
        roomTypesResultSet.updateString("oneOrTwo", oneOrTwo.getText());
        roomTypesResultSet.updateString("price", price.getText());
        roomTypesResultSet.updateString("extra", extra.getText());
    }

    @FXML
    private void setReady() {
        roomStatus.setText("Ready");
    }

    @FXML
    private void setNotReady() {
        roomStatus.setText("Not Ready");
    }

    @FXML
    private void setLuxury() {
        luxuryOrCottage.setText("Luxury");
    }

    @FXML
    private void setCottage() {
        luxuryOrCottage.setText("Cottage");
    }

    @FXML
    private void setOneQueenBed() {
        roomSize.setText("1 Queen Bed");
    }

    @FXML
    private void setTwoQueenBeds() {
        roomSize.setText("2 Queen Beds");
    }

    @FXML
    private void setTwoRooms() {
        roomSize.setText("Two Rooms");
    }

    @FXML
    private void setThreeRooms() {
        roomSize.setText("Three Rooms");
    }

    @FXML
    private void setBridal() {
        roomSize.setText("Bridal");
    }

    @FXML
    private void setFourRooms() {
        roomSize.setText("Four Rooms");
    }

    @FXML
    private void setPatio() {
        patioOrForrest.setText("Patio");
    }

    @FXML
    private void setForrest() {
        patioOrForrest.setText("Forrest");
    }

    @FXML
    private void setOne() {
        oneOrTwo.setText("One");
    }

    @FXML
    private void setTwo() {
        oneOrTwo.setText("Two");
    }

}
