package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class Billing {

    public TextArea receipt;

    public TextField emailAddress;

    private String roomName;
    private String billDate;
    private String roomCharge;
    private String dinningCharge;
    private String roomService;
    private String extrasCharge;
    private String total;

    private final double roomTax = 0.1;

    @FXML
    private void showBill() {
        fetchBillingInfo();
        StringBuffer bill = new StringBuffer("Statement\n");
        bill.append(String.format("%-25s %-25s %25s %n", "Date", "Charge", "Amount($)"));
        bill.append(String.format("%-25s %-25s %25s %n", billDate, "Room", roomCharge));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Dinning Room", dinningCharge));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Room Service", roomService));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Extras", extrasCharge));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Total", "$100.0"));


        receipt.setText(bill.toString());
        System.out.println(bill.toString());
    }

    private void fetchBillingInfo() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(Main.DIRECTORY);
            Statement statement = connection.createStatement();
            String emailQuery = "SELECT * "
                    + "FROM Guests "
                    + "WHERE emailAddress = '" + emailAddress.getText() + "'";
            ResultSet resultSet = statement.executeQuery(emailQuery);

            // If the record does exist, then retrieve the billing information
            if (resultSet.next()) {
                // The result set is still open, grab the primary key: guestID
                String guestID = resultSet.getString("guestID");
                resultSet.close();
                statement.close();

                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String billQuery = "SELECT * "
                        + "FROM Bills "
                        + "WHERE guestID = '" + guestID + "'";
                ResultSet billResultSet = statement.executeQuery(billQuery);
                billResultSet.next();
                roomCharge = billResultSet.getString("roomCharge");
                dinningCharge = billResultSet.getString("dinningCharge");
                roomService = billResultSet.getString("roomService");
                extrasCharge = billResultSet.getString("extrasCharge");
                billResultSet.close();
                statement.close();
            }
            // If the record doesn't exist
            else {
                JOptionPane.showMessageDialog(Main.popUpFrame,
                        "Guest Not Found",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void print() {
        fetchBillingInfo();
        StringBuffer bill = new StringBuffer("Statement\n");
        bill.append(String.format("%-25s %-25s %25s %n", "Date", "Charge", "Amount($)"));
        bill.append(String.format("%-25s %-25s %25s %n", billDate, "Room", roomCharge));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Dinning Room", dinningCharge));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Room Service", roomService));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Extras", extrasCharge));
        bill.append(String.format("%-25s %-25s %25s %n", "", "Total", "$100.0"));

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("Print Window");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextArea text = new JTextArea();
        text.append(String.valueOf(bill));
        JScrollPane pane = new JScrollPane(text);
        f.add("Center", pane);
        JButton printButton = new JButton("Print this text panel");
        printButton.addActionListener(new PrintScrollPane(pane));
        f.add("South", printButton);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
}
