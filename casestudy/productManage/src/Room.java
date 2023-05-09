import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String code;
    private double price;
    private String type;

    private boolean isAvailable;
    private String description;

    public Room() {
    }

    public Room(int id, String code, double price, String type, String description) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.type = type;
        this.isAvailable = true;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +
                ", description='" + description + '\'' +
                '}';
    }

    public void displayRoom() {
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%40s%n", id, code, price, type, isAvailable, description);
    }

    public String displayBook() {
        return code + "," + type + ", price : " + price;
    }
}
