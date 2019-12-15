package application;

public class Room {
    private String roomID;
    private String roomName;
    private String luxuryOrCottage;
    private String roomSize;
    private String patioOrForrest;
    private String oneOrTwo;
    private String price;
    private String extra;


    public Room() {
        this.roomID = "";
        this.roomName = "";
        this.luxuryOrCottage = "";
        this.roomSize = "";
        this.patioOrForrest = "";
        this.oneOrTwo = "";
        this.price = "";
        this.extra = "";
    }

    public Room(String roomID, String roomName, String luxuryOrCottage,
                String roomSize, String patioOrForrest, String oneOrTwo,
                String price, String extra) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.luxuryOrCottage = luxuryOrCottage;
        this.roomSize = roomSize;
        this.patioOrForrest = patioOrForrest;
        this.oneOrTwo = oneOrTwo;
        this.price = price;
        this.extra = extra;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLuxuryOrCottage() {
        return luxuryOrCottage;
    }

    public void setLuxuryOrCottage(String luxuryOrCottage) {
        this.luxuryOrCottage = luxuryOrCottage;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getPatioOrForrest() {
        return patioOrForrest;
    }

    public void setPatioOrForrest(String patioOrForrest) {
        this.patioOrForrest = patioOrForrest;
    }

    public String getOneOrTwo() {
        return oneOrTwo;
    }

    public void setOneOrTwo(String oneOrTwo) {
        this.oneOrTwo = oneOrTwo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
