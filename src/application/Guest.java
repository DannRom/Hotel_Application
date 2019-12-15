package application;

public class Guest {
    private String guestID;
    private String firstName;
    private String lastName;
    private String checkIn;
    private String checkOut;
    private String emailAddress;

    public Guest () {
        this.guestID = "";
        this.firstName = "";
        this.lastName = "";
        this.checkIn = "";
        this.checkOut = "";
        this.emailAddress = "";
    }

    public Guest(String guestID, String firstName, String lastName,
                 String checkIn, String checkOut, String emailAddress) {
        this.guestID = guestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.emailAddress = emailAddress;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
