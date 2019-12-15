package application;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class GuestForm {

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField address1Field;
    public TextField address2Field;
    public TextField cityField;
    public TextField stateField;
    public TextField zipField;
    public TextField phoneField;
    public TextField emailField;

    public TextArea specialRequestsArea;

    public TextField cardNumber;
    public TextField cvv;
    public TextField debitOrCredit;
    public TextField expMonth;
    public TextField expYear;

    public DatePicker checkInDate, checkOutDate;

    @FXML
    private void insertOrUpdate() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();

            String emailQuery = "SELECT * "
                    + "FROM Guests "
                    + "WHERE emailAddress = '" + emailField.getText() + "'";

            ResultSet resultSet = statement.executeQuery(emailQuery);

            // If the record doesn't exist, then insert
            if (!resultSet.next()) {

                statement.close();
                // Insert into Guests Table
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String guestQuery = "SELECT * "
                        + "FROM Guests ";
                ResultSet guestResultSet = statement.executeQuery(guestQuery);
                String guestID = "g" + ( (int) (Math.random() * 10000) );

                guestResultSet.moveToInsertRow();
                updateGuests(guestResultSet, guestID);
                guestResultSet.insertRow();

                statement.close();

                // Insert Into CardInfo Table
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String cardInfoQuery = "SELECT * "
                        + "FROM CardInfo ";
                ResultSet cardInfoResultSet = statement.executeQuery(cardInfoQuery);
                String cardID = "c" + ( (int) (Math.random() * 10000) );

                cardInfoResultSet.moveToInsertRow();
                updateCardInfo(cardInfoResultSet, cardID, guestID);
                cardInfoResultSet.insertRow();

                statement.close();

                // Announce success
                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Insertion Successful",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);

            }
            // If the record does exist, then update
            else {
                // The result set is still open, grab the primary key: guestID
                String guestID = resultSet.getString("guestID");
                statement.close();

                // Update Guests table
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String guestQuery = "SELECT * "
                        + "FROM Guests "
                        + "WHERE guestID = '" + guestID + "'";
                ResultSet guestResultSet = statement.executeQuery(guestQuery);

                guestResultSet.next();
                updateGuests(guestResultSet, guestID);
                guestResultSet.updateRow();

                statement.close();

                // Update CardInfo table
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String cardInfoQuery = "SELECT * "
                        + "FROM CardInfo "
                        + "WHERE guestID = '" + guestID + "'";
                ResultSet cardInfoResultSet = statement.executeQuery(cardInfoQuery);
                String cardID = "c" + ( (int) (Math.random() * 10000) );

                cardInfoResultSet.next();
                updateCardInfo(cardInfoResultSet, cardID, guestID);
                cardInfoResultSet.updateRow();

                statement.close();

                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Update Successful",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch(ClassNotFoundException | SQLException cnfex) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

    }

    private void updateGuests(ResultSet guestResultSet, String guestID) throws SQLException {
        guestResultSet.updateString("guestID", guestID);
        guestResultSet.updateString("firstName", firstNameField.getText());
        guestResultSet.updateString("lastName", lastNameField.getText());
        guestResultSet.updateString("address1", address1Field.getText());
        guestResultSet.updateString("address2", address2Field.getText());
        guestResultSet.updateString("city", cityField.getText());
        guestResultSet.updateString("state", zipField.getText());
        guestResultSet.updateString("zipcode", zipField.getText());
        guestResultSet.updateString("phoneNumber", phoneField.getText());
        guestResultSet.updateString("emailAddress", emailField.getText());
        guestResultSet.updateString("roomID", "");
        guestResultSet.updateString("checkIn", String.valueOf(checkInDate.getValue()));
        guestResultSet.updateString("checkOut", String.valueOf(checkOutDate.getValue()));
    }

    private void updateCardInfo(ResultSet cardInfoResultSet, String cardID, String guestID) throws SQLException {
        cardInfoResultSet.updateString("cardID",cardID);
        cardInfoResultSet.updateString("guestID",guestID);
        cardInfoResultSet.updateString("cardNumber",cardNumber.getText());
        cardInfoResultSet.updateString("cvv",cvv.getText());
        cardInfoResultSet.updateString("expMonth",expMonth.getText());
        cardInfoResultSet.updateString("expYear",expYear.getText());
        cardInfoResultSet.updateString("debitOrCredit",debitOrCredit.getText());
    }

}