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
        students = IOFile.readFileStudent();
    }

    @Override
    public Student create() {
        int id = getLastStudentId() + 1;
        int count = 0;
        System.out.println("Input student name");
        String name = scanner.nextLine();
        Integer age = getAge(count);
        if (age == null) return null;
        String gender = getGender();
        if (gender == null) return null;
        Double avgPoint = getAvgPoint(count);
        if (avgPoint == null) return null;
        Classroom classroom = getClassroom();
        if (classroom == null) return null;
        Student student = new Student(id, name, age, gender, avgPoint, classroom);
        students.add(student);
        IOFile.writeFileStudent(students);
        return student;
    }

    @Override
    public Student update() {
        displayAll();
        Student student = getById();
        int count = 0;
        System.out.println("Input new name");
        student.setName(scanner.nextLine());
        student.setAge(getAge(count));
        String gender = getGender();
        if (gender == null) return null;
        else student.setGender(gender);
        Double avgPoint = getAvgPoint(count);
        if (avgPoint == null) return null;
        else student.setAvgPoint(avgPoint);
        Classroom classroom = getClassroom();
        if (classroom == null) return null;
        else student.setClassroom(classroom);
        displayAll();
        IOFile.writeFileStudent(students);
        return student;
    }

    @Override
    public Student delete() {
        displayAll();
        Student student = getById();
        students.remove(student);
        displayAll();
        IOFile.writeFileStudent(students);
        return student;
    }

    @Override
    public Student getById() {
        System.out.println("Input id you want");
        int id = Integer.parseInt(scanner.nextLine());
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    @Override
    public void displayAll() {
        if (students.size() == 0) System.out.println("List students is empty!! Pls create!!");
        else for (Student student : students) {
            System.out.println(student);
        }
    }

    public void displayById() {
        Student student = getById();
        if (student != null) System.out.println(student);
        else System.out.println("This id doesnt exist!");
    }

    public int getLastStudentId() {
        if (students.size() == 0) return 0;
        else return students.get(students.size() - 1).getId();
    }

    public Integer getAge(int count) {
        int age = 0;
        do {
            count++;
            if (count == 4) return null;
            System.out.println("Input age");
            try {
                age = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (age < 6 || age > 60);
        return age;
    }

    public String getGender() {
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        System.out.println("Pls select");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) return "Male";
            else if (choice == 2) return "Female";
            else return "Other";
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Double getAvgPoint(int count) {
        double avgPoint = 0;
        do {
            count++;
            if (count == 4) return null;
            System.out.println("Input avgPoint of student");
            try {
                avgPoint = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (avgPoint < 0 || avgPoint > 10);
        return avgPoint;
    }

    public Classroom getClassroom() {
        classroomManage.displayAll();
        return classroomManage.getById();
    }

    public void displayStudentByClassroom() {
        classroomManage.displayAll();
        Classroom classroom = getClassroom();
        for (Student student : students) {
            if (student.getClassroom() == classroom) {
                System.out.println(student);
            }
        }
    }

    public void displayStudentByRank() {
        for (Student student : students) {
            if (student.getAvgPoint() > 8) System.out.println(student + " Rank : BEST");
            else if (student.getAvgPoint() > 6) System.out.println(student + " Rank : GOOD");
            else if (student.getAvgPoint() > 4.5) System.out.println(student + " Rank : NORMAL");
            else System.out.println(student + " Rank : POOR");
        }
    }

    public void displayByMaxPoint() {
        double max = students.get(0).getAvgPoint();
        for (Student student : students) {
            if (student.getAvgPoint() > max)
                max = student.getAvgPoint();
        }
        for (Student student : students) {
            if (student.getAvgPoint() == max)
                System.out.println(student);
        }
    }

    public void displayByMinPoint() {
        double min = students.get(0).getAvgPoint();
        for (Student student : students) {
            if (student.getAvgPoint() < min)
                min = student.getAvgPoint();
        }
        for (Student student : students) {
            if (student.getAvgPoint() == min)
                System.out.println(student);
        }
    }

    public void searchByName() {
        System.out.println("Enter name you want to search");
        String name = scanner.nextLine();
        for (Student student : students) {
            if (student.getName().contains(name))
                System.out.println(student);
        }
    }

    public void displayByGender() {
        String gender = getGender();
        for (Student student : students) {
            if (student.getGender().equals(gender))
                System.out.println(student);
        }
    }
}
