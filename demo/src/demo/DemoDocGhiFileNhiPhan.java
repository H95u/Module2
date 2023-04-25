package demo.src.demo;

import java.io.*;

public class DemoDocGhiFileNhiPhan {
    public static void main(String[] args) {
        //write
        int a[] = {1, 2, 3, 4};
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\hieujava\\demo\\src\\demo\\numbers.txt");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            for (int i = 0; i < a.length; i++) {
                dataOutputStream.writeInt(a[i]);
            }
            dataOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        readFile();
    }

    public static void readFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\hieujava\\demo\\src\\demo\\numbers.txt");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            while (true) {
                System.out.println(dataInputStream.readInt());
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
