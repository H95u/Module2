package demo.src.demo;

import java.io.*;

public class DemoDocGhiObject {
    public static void writeObject(Student student) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\hieujava\\demo\\src\\demo\\students.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ObjectOutputStream(fileOutputStream));
            objectOutputStream.writeObject(student);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Student readObject() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\hieujava\\demo\\src\\demo\\students.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Student) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Student student = new Student(1, "hieu");
        writeObject(student);
        Student student1 = readObject();
    }
}
