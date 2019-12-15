package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class AddCharges {

    public TextField emailAddress;
    public TextField roomCharge;
    public TextField dinningCharge;
    public TextField roomService;
    public TextField extrasCharge;

    public TextArea notes;

    @FXML
    private void addBill() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();

            String emailQuery = "SELECT * "
                    + "FROM Guests "
                    + "WHERE emailAddress = '" + emailAddress.getText() + "'";

            ResultSet resultSet = statement.executeQuery(emailQuery);

            // If the record does exist, then insert new bill
            if (resultSet.next()) {
                // The result set is still open, grab the primary key: guestID
                String guestID = resultSet.getString("guestID");
                statement.close();

                // Insert into Bills Table
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String billsQuery = "SELECT * "
                        + "FROM Bills ";
                ResultSet billResultSet = statement.executeQuery(billsQuery);
                String billID = "b" + ( (int) (Math.random() * 10000) );

                billResultSet.moveToInsertRow();
                updateBill(billResultSet, billID, guestID);
                billResultSet.insertRow();

                statement.close();

                // Announce success
                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Insertion Successful",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            // If the record doesn't exist
            else {
                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Guest Not Found",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(ClassNotFoundException | SQLException cnfex) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

    }

    private void updateBill (ResultSet billResultSet, String billID, String guestID) throws SQLException {
        billResultSet.updateString("billID", billID);
        billResultSet.updateString("guestID", guestID);
        billResultSet.updateString("billDate", "");
        billResultSet.updateString("roomCharge", roomCharge.getText());
        billResultSet.updateString("dinningCharge", dinningCharge.getText());
        billResultSet.updateString("roomService", roomService.getText());
        billResultSet.updateString("extrasCharge", extrasCharge.getText());
        billResultSet.updateString("totalCharge", "");
        billResultSet.updateString("notes", notes.getText());
    }
}
