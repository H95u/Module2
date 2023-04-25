package day17.src.product;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManage productManage = new ProductManage();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---------MENU---------");
            System.out.println("1.Thêm sản phẩm");
            System.out.println("2.Hiển thị sản phẩm");
            System.out.println("3.Tìm kiếm thông tin sản phẩm");
            System.out.println("0.Thoát");
            System.out.println("MỜI NHẬP VÀO LỰA CHỌN CỦA BẠN");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai định dạng!!");
                continue;
            }
            switch (choice) {
                case 1:
                    productManage.addProduct();
                    break;
                case 2:
                    productManage.displayProduct();
                    break;
                case 3:
                    productManage.searchByName();
                    break;
                case 0:
                    System.exit(0);
            }
        } while (true);
    }
}
