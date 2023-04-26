import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ClassroomManage classroomManage = new ClassroomManage();
        StudentManage studentManage = new StudentManage(classroomManage);
        int choice;
        do {
            System.out.println("--------------MENU-------------");
            System.out.println("1. Create new Student");
            System.out.println("2. Update Student by id");
            System.out.println("3. Delete Student by id");
            System.out.println("4. Display Student by id");
            System.out.println("5. Display all Student");
            System.out.println("6. Display all Student by min point");
            System.out.println("7. Display all Student by max point");
            System.out.println("8. Display all Student by rank");
            System.out.println("9. Display all Student by gender");
            System.out.println("10. Display all Student by Classroom");
            System.out.println("11. Search Student by name");
            System.out.println("12. Action with Classroom");
            System.out.println("0. Exit");
            System.out.println("Input your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                continue;
            }
            switch (choice) {
                case 1:
                    studentManage.create();
                    break;
                case 2:
                    studentManage.update();
                    break;
                case 3:
                    studentManage.delete();
                    break;
                case 4:
                    studentManage.displayById();
                    break;
                case 5:
                    studentManage.displayAll();
                    break;
                case 6:
                    studentManage.displayByMinPoint();
                    break;
                case 7:
                    studentManage.displayByMaxPoint();
                    break;
                case 8:
                    studentManage.displayStudentByRank();
                    break;
                case 9:
                    studentManage.displayByGender();
                    break;
                case 10:
                    studentManage.displayStudentByClassroom();
                    break;
                case 11:
                    studentManage.searchByName();
                    break;
                case 12:
                    subMenu(studentManage);
                    break;
                case 0:
                    System.exit(0);
            }
        } while (true);
    }

    public static void subMenu(StudentManage studentManage) {
        int choice = -1;
        do {
            System.out.println("MENU");
            System.out.println("1. Create new Classroom");
            System.out.println("2. Update Classroom by id");
            System.out.println("3. Delete Classroom by id");
            System.out.println("4. Display Classroom by id");
            System.out.println("5. Display all Classroom");
            System.out.println("0. Exit");
            System.out.println("Input your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            switch (choice) {
                case 1:
                    studentManage.getClassroomManage().create();
                    break;
                case 2:
                    studentManage.getClassroomManage().update();
                    break;
                case 3:
                    studentManage.getClassroomManage().delete();
                    break;
                case 4:
                    studentManage.getClassroomManage().displayById();
                    break;
                case 5:
                    studentManage.getClassroomManage().displayAll();
                    break;
            }
        } while (choice != 0);
    }
}
