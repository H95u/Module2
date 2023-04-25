import java.util.ArrayList;
import java.util.Scanner;

public class StudentManage implements Manage<Student> {
    private Scanner scanner;
    private ArrayList<Student> students;
    private ClassroomManage classroomManage;

    public ClassroomManage getClassroomManage() {
        return classroomManage;
    }

    public void setClassroomManage(ClassroomManage classroomManage) {
        this.classroomManage = classroomManage;
    }

    public StudentManage(ClassroomManage classroomManage) {
        this.classroomManage = classroomManage;
        scanner = new Scanner(System.in);
        students = new ArrayList<>();
    }

    @Override
    public Student create() {
        return null;
    }

    @Override
    public Student update() {
        return null;
    }

    @Override
    public Student delete() {
        return null;
    }

    @Override
    public Student getById() {
        System.out.println("Input id you want to find");
        int id = Integer.parseInt(scanner.nextLine());
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    @Override
    public void displayAll() {

    }

    public void displayById() {
        Student student = getById();
        if (student != null) System.out.println(student);
        else System.out.println("This id doesnt exist!");
    }
}
