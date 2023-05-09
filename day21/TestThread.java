package day21;

class OddThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i+=2) {
            System.out.print(i + " ");
            try {
                Thread.sleep(10); // dừng 10 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class EvenThread extends Thread {
    public void run() {
        for (int i = 2; i <= 10; i+=2) {
            System.out.print(i + " ");
            try {
                Thread.sleep(15); // dừng 15 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TestThread {
    public static void main(String[] args) {
        OddThread oddThread = new OddThread();
        EvenThread evenThread = new EvenThread();

        oddThread.start();
        try {
            oddThread.join(); // đợi OddThread kết thúc trước khi start() EvenThread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        evenThread.start();
    }
}
