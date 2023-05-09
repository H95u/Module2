import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManage implements IOFileInterface<Account>, ManageInterface<Account> {
    private ArrayList<Account> accounts;
    private Scanner scanner;
    private final String accountPath = "D:\\hieujava\\casestudy\\productManage\\src\\data\\account.txt";
    private final String loggingUserPath = "D:\\hieujava\\casestudy\\productManage\\src\\data\\loggingUser.txt";
    private static AccountManage accountManage;

    private AccountManage() {
        accounts = readFile(accountPath);
        scanner = new Scanner(System.in);
    }

    public static AccountManage getInstance() {
        if (accountManage == null) {
            accountManage = new AccountManage();
        }
        return accountManage;
    }

    @Override
    public void writeFile(ArrayList<Account> accounts, String path) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(accounts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Account> readFile(String path) {
        ArrayList<Account> newAccounts = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            newAccounts = (ArrayList<Account>) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newAccounts;
    }


    @Override
    public Account create() {
        int id = getLastId() + 1;
        String userName = getValidUserName();
        String passWord = getValidPassWord();
        String phoneNumber = getValidPhoneNumber();
        String email = getValidEmail();
        Account account = new Account(id, userName, passWord, phoneNumber, email);
        if (account.getUserName().equalsIgnoreCase("hieu123")) {
            account.setRole("admin");
        } else account.setRole("user");
        accounts.add(account);
        writeFile(accounts, accountPath);
        return account;
    }

    @Override
    public Account update() {
        return null;
    }

    @Override
    public Account delete() {
        return null;
    }

    @Override
    public void displayAll() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public int getLastId() {
        if (accounts.size() == 0) return 0;
        else return accounts.get(accounts.size() - 1).getId();
    }

    public String getValidUserName() {
        boolean checkRegex;
        boolean checkUserNameExisted;
        String userName;
        do {
            System.out.println("Enter username (at least 6 char _ is allowed)");
            userName = scanner.nextLine();
            checkRegex = ValidateData.validateUserName(userName);
            checkUserNameExisted = checkUserNameExisted(userName);
        } while (!checkRegex || checkUserNameExisted);
        return userName;
    }

    public boolean checkUserNameExisted(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equalsIgnoreCase(userName)) return true;
        }
        return false;
    }

    private String getValidPassWord() {
        boolean check;
        String passWord;
        do {
            System.out.println("Enter password (at least 6 char, start with capital letter)");
            passWord = scanner.nextLine();
            check = ValidateData.validatePassWord(passWord);
            if (!check) System.out.println("Invalid !! Pls re-input");
        } while (!check);
        return passWord;
    }

    private String getValidPhoneNumber() {
        boolean check;
        String phoneNumber;
        do {
            System.out.println("Enter phone number (10 numbers, start with 0)");
            phoneNumber = scanner.nextLine();
            check = ValidateData.validatePhone(phoneNumber);
            if (!check) System.out.println("Invalid !! Pls re-input");
        } while (!check);
        return phoneNumber;
    }

    private String getValidEmail() {
        boolean check;
        String email;
        do {
            System.out.println("Enter email (example: example@gmail.com)");
            email = scanner.nextLine();
            check = ValidateData.validateEmail(email);
            if (!check) System.out.println("Invalid !! Pls re-input");
        } while (!check);
        return email;
    }

    public void login() {
        boolean checkLogin;
        String userName;
        int count = 0;
        do {
            count++;
            System.out.println("Enter your username");
            userName = scanner.nextLine();
            System.out.println("Enter your password");
            String passWord = scanner.nextLine();
            checkLogin = checkLogin(userName, passWord);
            if (count == 3) break;
            if (checkLogin) {
                System.out.println("Login success !! welcome " + userName);
            } else {
                System.out.println("Pls re-input because username or password is wrong !!");
            }
        } while (!checkLogin);
        ArrayList<Account> loggingUser = new ArrayList<>();
        loggingUser.add(getAccountByUserName(userName));
        writeFile(loggingUser, loggingUserPath);
    }

    private boolean checkLogin(String userName, String passWord) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName) && account.getPassWord().equals(passWord)) {
                return true;
            }
        }
        return false;
    }

    private Account getAccountByUserName(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) return account;
        }
        return null;
    }

    public ArrayList<Account> getLoggingUser() {
        return readFile(loggingUserPath);
    }
    public void findPassWord() {
        System.out.println("Input your username");
        String userName = scanner.nextLine();
        Account account = getAccountByUserName(userName);
        if (account != null) {
            System.out.println("Input your phone number");
            String phoneNumber = scanner.nextLine();
            if (account.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Your password is : " + account.getPassWord());
            } else {
                System.out.println("Your phone number is wrong !!");
            }
        } else {
            System.out.println("Your username is wrong !!");
        }
    }
}
