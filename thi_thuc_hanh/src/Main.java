import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void sortMenu(ProductManage productManage) {
        int choice = -1;
        do {
            System.out.println("1. Sắp xếp tăng dần");
            System.out.println("2. Sắp xếp giảm dần");
            System.out.println("3. Thoát");
            try {
                System.out.println("Mời nhập vào lựa chọn");
                choice = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            switch (choice) {
                case 1:
                    productManage.sortByPriceIncrease();
                    break;
                case 2:
                    productManage.sortByPriceDecrease();
                    break;
                case 3:
                    choice = 0;
                    break;
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        ProductManage productManage = new ProductManage();
        int choice = -1;
        do {
            System.out.println("1. Xem danh sách ");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp theo giá");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("9. Thoát");
            System.out.println("Mời chọn chức năng :");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (choice) {
                case 1:
                    productManage.displayAll();
                    break;
                case 2:
                    productManage.addOneProduct();
                    break;
                case 3:
                    productManage.updateProduct();
                    break;
                case 4:
                    productManage.deleteById();
                    break;
                case 5:
                    sortMenu(productManage);
                    break;
                case 6:
                    productManage.searchByMaxPrice();
                    break;
                case 7:
                    productManage.readFileCsv();
                    break;
                case 8:
                    productManage.writeFileCsv();
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
