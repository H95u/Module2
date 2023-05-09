import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void userMenu() {
        int choice = -1;
        do {
            System.out.println("USER");
            System.out.println("1. Show all available room");
            System.out.println("2. Booking room");
            System.out.println("3. Display your booking");
            System.out.println("4. Cancel your booking");
            System.out.println("5. Update your booking");
            System.out.println("0. Log out");
            System.out.println("PLS SELECT YOUR CHOICE !!");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (choice) {
                case 1:
                    RoomManage.getInstance().showAllAvailableRoom();
                    break;
                case 2:
                    BookingManage.getInstance().create();
                    break;
                case 3:
                    BookingManage.getInstance().displayBookingOfUser();
                    break;
                case 4:
                    BookingManage.getInstance().cancelUserBooking();
                    break;
                case 5:
                    BookingManage.getInstance().update();
                    break;
            }
        } while (choice != 0);
    }

    public static void adminMenu() {
        int choice = -1;
        do {
            System.out.println("ADMIN");
            System.out.println("1. Add new room ");
            System.out.println("2. Update room ");
            System.out.println("3. Delete room ");
            System.out.println("4. Display all room ");
            System.out.println("5. Display all available room ");
            System.out.println("6. Display all booking ");
            System.out.println("7. Remove all booking ");
            System.out.println("8. Calculate money ");
            System.out.println("0. Log out ");
            System.out.println("PLS SELECT YOUR CHOICE !!");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (choice) {
                case 1:
                    RoomManage.getInstance().create();
                    break;
                case 2:
                    RoomManage.getInstance().update();
                    break;
                case 3:
                    RoomManage.getInstance().delete();
                    break;
                case 4:
                    RoomManage.getInstance().displayAll();
                    break;
                case 5:
                    RoomManage.getInstance().showAllAvailableRoom();
                    break;
                case 6:
                    BookingManage.getInstance().displayAll();
                    break;
                case 7:
                    BookingManage.getInstance().deleteAllBooking();
                    break;
                case 8:
                    BookingManage.getInstance().calculateMoney();
                    break;
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("-------------- MENU ------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot password");
            System.out.println("0. Exit");
            System.out.println("PLS SELECT YOUR CHOICE !!");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (choice) {
                case 1:
                    AccountManage.getInstance().create();
                    break;
                case 2:
                    AccountManage.getInstance().login();
                    if (AccountManage.getInstance().getLoggingUser().get(0) != null) {
                        if (AccountManage.getInstance().getLoggingUser().get(0).getRole().equals("admin")) adminMenu();
                        else userMenu();
                    }
                    break;
                case 3:
                    AccountManage.getInstance().findPassWord();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
