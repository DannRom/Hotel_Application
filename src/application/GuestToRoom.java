package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class GuestToRoom {

    public TextField emailAddress;
    public TextField roomName;

    @FXML
    private void addGuestToRoom() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();

            String emailQuery = "SELECT * "
                    + "FROM Guests "
                    + "WHERE emailAddress = '" + emailAddress.getText() + "'";

            ResultSet resultSet = statement.executeQuery(emailQuery);

            // If the guest does exist, then check if room exists
            if (resultSet.next()) {
                String guestID = resultSet.getString("guestID");
                resultSet.close();
                statement.close();
                statement = connection.createStatement();
                String roomQuery = "SELECT * "
                        + "FROM Rooms "
                        + "WHERE roomName = '" + roomName.getText() + "'";
                resultSet = statement.executeQuery(roomQuery);

                // If room does exist, then add roomID to Guest record
                if (resultSet.next()) {
                    String roomID = resultSet.getString("roomID");
                    resultSet.close();
                    statement.close();
                    statement = connection.createStatement(
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    String guestQuery = "SELECT * "
                            + "FROM Guests "
                            + "WHERE guestID = '" + guestID + "'";
                    ResultSet guestResultSet = statement.executeQuery(guestQuery);

                    guestResultSet.next();
                    guestResultSet.updateString("roomID", roomID);
                    guestResultSet.updateRow();

                    guestResultSet.close();
                    statement.close();

                    JOptionPane.showMessageDialog(Main.popUpFrame,
                            "Update Successful",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                // If room does not exist
                else {
                    JOptionPane.showMessageDialog(Main.popUpFrame,
                            "Guest Not Found",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            // If the guest does not exist
            else {
                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Guest Not Found",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}