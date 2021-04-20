import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Main().run();
    }

    @Override
    public void run() {
        int n = 10;

        while (n-- > 0) {
            Thread thread = new Thread(this::doSomething);
            thread.start();
        }

    }

    private void doSomething() {
        long z = 0;

        while (true) {
            z++;
        }
    }
}
