package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PriceSheet {

    public PriceSheet() {
        /*
        NOTE:
        This constructor runs before anything else,
        including the initialize() methods. But it
        cannot access @FXML.

        The @FXML annotated fields are populated
        after the constructor is called. Therefore,
        fields referring to components defined in
        the .fxml file are inaccessible.

        Therefore, use initialize() in its place.
        This constructor does nothing.

        Link: https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/doc-files/introduction_to_fxml.html#controllers
         */
    }

    @FXML
    private TextArea something;

    @FXML
    public void initialize() {

        String string = "\t\tNumber of Rooms\tIndoors\tNumber of Rooms\tPatio\tExtras\n" +
                "Luxury\n" +
                "1 Queen Bed\t#\t$Price $Price\t#\t$Price $Price\t$Price\n" +
                "2 Queen Beds\t#\t$Price $Price\t#\t$Price $Price\t$Price\n" +
                "Two Rooms\t#\t$Price $Price\t#\t$Price $Price\t$Price\n" +
                "Three Rooms\t#\t$Price $Price\t#\t$Price $Price\t$Price\n" +
                "Cottage\n" +
                "Two Rooms\t#\t$Price $Price\t#\t$Price $Price\t$Price\n" +
                "Three Rooms\t#\t$Price $Price\t#\t$Price $Price\t$Price\n";
                something.setText(string);

    }
}
