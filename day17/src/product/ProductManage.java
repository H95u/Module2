package day17.src.product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManage {
    private ArrayList<Product> products;
    private Scanner scanner;

    public ProductManage() {
        products = readDataFromFile("D:\\hieujava\\day17\\src\\product\\Products.txt");
        scanner = new Scanner(System.in);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct() {
        System.out.println("Nhập tên sp");
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập mô tả sp");
        String des = scanner.nextLine();
        Product product = new Product(name, price, des);
        products.add(product);
        writeDataToFile();
    }

    public void displayProduct() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void searchByName() {
        System.out.println("Mời nhập vào tên muốn tìm");
        String name = scanner.nextLine();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                System.out.println(product);
            }
        }
    }

    public ArrayList<Product> readDataFromFile(String path) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            products = (ArrayList<Product>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
    public void writeDataToFile(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream
                    (new FileOutputStream("D:\\hieujava\\day17\\src\\product\\Products.txt"));
            objectOutputStream.writeObject(products);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
