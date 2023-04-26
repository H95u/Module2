import java.io.*;
import java.util.ArrayList;

public class IOFile {
    public static void writeFileStudent(ArrayList<Student> students) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\hieujava\\minitest\\src\\data\\student.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Student student : students) {
                bufferedWriter.write(student.writeStudent() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Student> readFileStudent() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("D:\\hieujava\\minitest\\src\\data\\student.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String c;
            String[] str;
            while ((c = bufferedReader.readLine()) != null) {
                str = c.split(",");
                int id = Integer.parseInt(str[0]);
                String name = str[1];
                int age = Integer.parseInt(str[2]);
                String gender = str[3];
                Double avgPoint = Double.parseDouble(str[4]);
                Classroom classroom = new Classroom(Integer.parseInt(str[5]), str[6]);
                students.add(new Student(id, name, age, gender, avgPoint, classroom));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static void writeFileClassroom(ArrayList<Classroom> classrooms) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\hieujava\\minitest\\src\\data\\classroom.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Classroom classroom : classrooms) {
                bufferedWriter.write(classroom.toString() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Classroom> readFileClassroom() {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("D:\\hieujava\\minitest\\src\\data\\classroom.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String c;
            String[] str;
            while ((c = bufferedReader.readLine()) != null) {
                str = c.split(",");
                classrooms.add(new Classroom(Integer.parseInt(str[0]), str[1]));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classrooms;
    }
}
