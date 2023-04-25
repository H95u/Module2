import java.util.ArrayList;
import java.util.Scanner;

public class ClassroomManage implements Manage<Classroom> {
    private Scanner scanner;
    private static final ArrayList<Classroom> classrooms = IOFile.readFileClassroom();

    public ClassroomManage() {
        scanner = new Scanner(System.in);
    }

    @Override
    public Classroom create() {
        int id = getLastClassroomId() + 1;
        System.out.println("Input classroom name");
        String name = scanner.nextLine();
        Classroom classroom = new Classroom(id, name);
        classrooms.add(classroom);
        IOFile.writeFileClassroom(classrooms);
        return classroom;
    }

    @Override
    public Classroom update() {
        displayAll();
        Classroom classroom = getById();
        if (classroom != null) {
            System.out.println("Input new classroom name");
            String name = scanner.nextLine();
            classroom.setName(name);
        }
        IOFile.writeFileClassroom(classrooms);
        return classroom;
    }

    @Override
    public Classroom delete() {
        displayAll();
        Classroom classroom = getById();
        if (classroom != null) {
            classrooms.remove(classroom);
        }
        IOFile.writeFileClassroom(classrooms);
        return classroom;
    }

    @Override
    public Classroom getById() {
        System.out.println("Input id you want to find");
        int id = Integer.parseInt(scanner.nextLine());
        for (Classroom classroom : classrooms) {
            if (classroom.getId() == id) return classroom;
        }
        return null;
    }

    @Override
    public void displayAll() {
        for (Classroom classroom : classrooms) {
            System.out.println(classroom);
        }
    }

    public void displayById() {
        Classroom classroom = getById();
        if (classroom != null) {
            System.out.println(classroom);
        } else System.out.println("This id doesnt exist!");
    }

    public int getLastClassroomId() {
        int size = classrooms.size();
        if (size == 0) return 0;
        else return classrooms.get(classrooms.size() - 1).getId();
    }
}
