import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomManage implements ManageInterface<Room>, IOFileInterface<Room> {
    private ArrayList<Room> rooms;
    private Scanner scanner;
    private String roomPath = "D:\\hieujava\\casestudy\\productManage\\src\\data\\room.txt";
    private static RoomManage roomManage;

    protected RoomManage() {
        rooms = readFile(roomPath);
        scanner = new Scanner(System.in);
    }

    public static RoomManage getInstance() {
        if (roomManage == null) {
            roomManage = new RoomManage();
        }
        return roomManage;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room create() {
        int id = getLastRoomId() + 1;
        System.out.println("Input room code");
        String code = scanner.nextLine();
        System.out.println("Input price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Input type");
        String type = getType();
        String description = getDescription(type);
        Room room = new Room(id, code, price, type, description);
        rooms.add(room);
        writeFile(rooms, roomPath);
        return room;
    }

    @Override
    public Room update() {
        displayAll();
        System.out.println("Input id u want");
        int id = Integer.parseInt(scanner.nextLine());
        Room room = getRoomById(id);
        if (room != null) {
            System.out.println("Input new code");
            room.setCode(scanner.nextLine());
            System.out.println("Input new price");
            room.setPrice(Double.parseDouble(scanner.nextLine()));
            System.out.println("Input type");
            String type = getType();
            String description = getDescription(type);
            room.setType(type);
            room.setDescription(description);
            writeFile(rooms, roomPath);
        } else {
            System.out.println("Not have that id");
        }
        return room;
    }

    @Override
    public Room delete() {
        displayAll();
        System.out.println("Input id u want");
        int id = Integer.parseInt(scanner.nextLine());
        Room room = getRoomById(id);
        if (room != null) {
            rooms.remove(room);
            writeFile(rooms, roomPath);
        } else {
            System.out.println("Not have that id");
        }
        return room;
    }

    @Override
    public void displayAll() {
        System.out.printf("%60s%n", "----------- ROOM ------------");
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%25s%n", "ID", "code", "price", "type", "isAvailable", "description");
        for (Room room : rooms) {
            room.displayRoom();
        }
    }

    @Override
    public void writeFile(ArrayList<Room> rooms, String path) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(rooms);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Room> readFile(String path) {
        ArrayList<Room> newRooms = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            newRooms = (ArrayList<Room>) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newRooms;
    }

    public String getType() {
        System.out.println("1. Normal");
        System.out.println("2. Vip");
        System.out.println("Select !!");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) return "Normal";
        else return "Vip";
    }

    public String getDescription(String type) {
        if (type.equalsIgnoreCase("Vip"))
            return "TV, air conditioner, washer, 2 beds";
        else
            return "TV, air conditioner, 1 bed";
    }

    public int getLastRoomId() {
        if (rooms.size() == 0) return 0;
        else return rooms.get(rooms.size() - 1).getId();
    }

    public Room getRoomById(int id) {
        for (Room room : rooms) {
            if (room.getId() == id) return room;
        }
        return null;
    }

    public int showAllAvailableRoom() {
        boolean hasAvailableRoom = false;
        System.out.printf("%60s%n", "--------------- ROOM --------------");
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%25s%n", "ID", "code", "price", "type", "isAvailable", "description");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                room.displayRoom();
                hasAvailableRoom = true;
            }
        }
        if (!hasAvailableRoom) {
            System.out.println("No available rooms !!");
            return 0;
        }
        return -1;
    }

    public void setRoomStatus(String code) {
        for (Room room : rooms) {
            if (room.getCode().equals(code))
                room.setAvailable(true);
        }
        writeFile(rooms, roomPath);
    }
}
