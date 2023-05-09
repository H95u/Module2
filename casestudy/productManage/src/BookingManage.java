import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingManage implements ManageInterface<Booking>, IOFileInterface<Booking> {
    private static BookingManage bookingManage;
    private String bookingPath = "D:\\hieujava\\casestudy\\productManage\\src\\data\\booking.txt";
    private ArrayList<Booking> bookings;
    private Scanner scanner;

    private BookingManage() {
        bookings = readFile(bookingPath);
        scanner = new Scanner(System.in);
    }

    public static BookingManage getInstance() {
        if (bookingManage == null) {
            bookingManage = new BookingManage();
        }
        return bookingManage;
    }

    @Override
    public void writeFile(ArrayList<Booking> bookings, String path) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(bookings);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Booking> readFile(String path) {
        ArrayList<Booking> newBookings = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            newBookings = (ArrayList<Booking>) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newBookings;
    }

    @Override
    public Booking create() {
        int id = getLastBookingId() + 1;
        int quantity = RoomManage.getInstance().showAllAvailableRoom();
        if (quantity == 0) return null;
        System.out.println("SELECT ROOM YOU WANT");
        int idRoom = inputNum();
        Room room = RoomManage.getInstance().getRoomById(idRoom);
        LocalDateTime checkInTime = getValidCheckInTime();
        LocalDateTime checkOutTime = getValidCheckOutTime();
        System.out.println("Input number of Guests");
        int numOfGuests = inputNum();
        Account loggingUser = getLoggingUser();
        room.setAvailable(false);
        RoomManage.getInstance().writeFile(RoomManage.getInstance().getRooms(),
                "D:\\hieujava\\casestudy\\productManage\\src\\data\\room.txt");
        Booking booking = new Booking(id, checkInTime, checkOutTime, numOfGuests, loggingUser, room);
        bookings.add(booking);
        writeFile(bookings, bookingPath);
        return booking;
    }

    private LocalDateTime getValidCheckOutTime() {
        LocalDateTime checkOutTime = null;
        do {
            try {
                checkOutTime = getCheckInOutTime("out");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return checkOutTime;
    }

    private LocalDateTime getValidCheckInTime() {
        LocalDateTime checkInTime;
        do {
            try {
                checkInTime = getCheckInOutTime("in");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return checkInTime;
    }

    private LocalDateTime getCheckInOutTime(String str) {
        System.out.println("Input check-" + str + " day");
        int checkInOutDay = inputNum();
        System.out.println("Input check-" + str + " month");
        int checkInOutMonth = inputNum();
        int checkInOutHours = getValidHours();
        return LocalDateTime.of(2023, checkInOutMonth, checkInOutDay, checkInOutHours, 0);
    }

    private int getValidHours() {
        int hours;
        do {
            System.out.println("Input hours");
            hours = inputNum();
            if (hours < 0 || hours > 24) System.out.println("Pls re-input !! because hours invalid !!");
        } while (hours < 0 || hours > 24);
        return hours;
    }

    @Override
    public Booking update() {
        displayBookingOfUser();
        if (!bookings.isEmpty()) {
            System.out.println("Input id u want");
            int id = inputNum();
            Booking booking = getBookingById(id);
            if (booking != null) {
                updateMenu(booking);
            } else {
                System.out.println("Not have that id");
            }
        } else {
            System.out.println("There is no booking");
        }
        return null;
    }

    private void updateMenu(Booking booking) {
        System.out.println("1. Update checkInTime");
        System.out.println("2. Update checkOutTime");
        System.out.println("3. Update num of guests");
        System.out.println("4. Update room");
        System.out.println("5. Update all");
        System.out.println("Which one you wanna update ?");
        int choice = inputNum();
        switch (choice) {
            case 1:
                updateCheckInTime(booking);
                break;
            case 2:
                updateCheckOutTime(booking);
                break;
            case 3:
                updateNumOfGuests(booking);
                break;
            case 4:
                updateRoom(booking);
                break;
            case 5:
                updateAll(booking);
                break;
        }
        writeFile(bookings, bookingPath);
    }

    @Override
    public Booking delete() {
        displayAll();
        System.out.println("Input id u want");
        int id = inputNum();
        Booking booking = getBookingById(id);
        if (booking != null) {
            bookings.remove(booking);
            writeFile(bookings, bookingPath);
        } else {
            System.out.println("Not have that id");
        }
        return booking;
    }

    @Override
    public void displayAll() {
        System.out.printf("%-50s%s%n", "", "-------- BOOKING ROOM ----------");
        System.out.printf("%s%-15s%s%-20s%s%-10s%s%-10s%s%-7s%s%-5s%s%n"
                , "ID", "", "CheckInTime", "", "CheckOutTime", "", "Num of guests", ""
                , "User name", "", "Room info", "", "Duration");
        for (Booking booking : bookings) {
            booking.displayBook();
        }
    }

    public int getLastBookingId() {
        if (bookings.size() == 0) return 0;
        else return bookings.get(bookings.size() - 1).getId();
    }

    public Account getLoggingUser() {
        ArrayList<Account> loggingUser = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("D:\\hieujava\\casestudy\\productManage\\src\\data\\loggingUser.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            loggingUser = (ArrayList<Account>) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loggingUser.get(0);
    }

    public boolean displayBookingOfUser() {
        boolean hasBooking = false;
        System.out.printf("%70s%s%n", "", "-------------- BOOKING ROOM ---------------");
        System.out.printf("%s%30s%40s%22s%20s%25s%27s%n"
                , "ID", "CheckInTime", "CheckOutTime", "Num of guests"
                , "User name", "Room info", "Duration");
        for (Booking booking : bookings) {
            if (booking.getAccountInfo().getUserName().equals(getLoggingUser().getUserName())) {
                booking.displayBook();
                hasBooking = true;
            }
        }
        return hasBooking;
    }

    public void deleteAllBooking() {
        bookings.clear();
    }

    public Booking getBookingById(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) return booking;
        }
        return null;
    }

    public void calculateMoney() {
        displayAll();
        if (!bookings.isEmpty()) {
            System.out.println("Input id u want");
            int id = inputNum();
            Booking booking = getBookingById(id);
            double money = (booking.getDuration().toHours() * booking.getRoomInfo().getPrice()) / 24;
            System.out.println("Your bill is : " + money + "USD");
            bookings.remove(booking);
            writeFile(bookings, bookingPath);
        } else {
            System.out.println("There is no booking !!");
        }
    }

    public void cancelUserBooking() {
        if (displayBookingOfUser()) {
            System.out.println("Input id u want");
            int id = inputNum();
            Booking booking = getBookingById(id);
            RoomManage.getInstance().setRoomStatus(booking.getRoomInfo().getCode());
            bookings.remove(booking);
            writeFile(bookings, bookingPath);
        } else {
            System.out.println("There is no booking");
        }
    }

    public void updateCheckInTime(Booking booking) {
        LocalDateTime checkInTime = null;
        try {
            checkInTime = getCheckInOutTime("in");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        booking.setCheckInTime(checkInTime);
        booking.setDuration(Duration.between(checkInTime, booking.getCheckOutTime()));
    }

    public void updateCheckOutTime(Booking booking) {
        LocalDateTime checkOutTime = null;
        try {
            checkOutTime = getCheckInOutTime("out");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        booking.setCheckOutTime(checkOutTime);
        booking.setDuration(Duration.between(booking.getCheckInTime(), booking.getCheckOutTime()));
    }

    public void updateNumOfGuests(Booking booking) {
        System.out.println("Input num of guests");
        int numOfGuests = inputNum();
        booking.setNumOfGuests(numOfGuests);
    }

    public void updateRoom(Booking booking) {
        RoomManage.getInstance().displayAll();
        System.out.println("Input id u want");
        int id = inputNum();
        Room room = RoomManage.getInstance().getRoomById(id);
        booking.setRoomInfo(room);
    }

    public void updateAll(Booking booking) {
        updateCheckInTime(booking);
        updateCheckOutTime(booking);
        updateNumOfGuests(booking);
        updateRoom(booking);
    }

    public int inputNum() {
        int x;
        do {
            try {
                x = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return x;
    }
}
