package Praktikum01;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class KGVServer implements CommandExecutor{


    public KGVServer() {

    }

    public String execute(String inputString) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(inputString.getBytes()));
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        String outputString = Integer.toString(kgv(a, b));
        return outputString;
    }

    private int ggt(int a ,int b) {
        int inter;
        if (b == 0) {
            inter = a;
        } else {
            inter = ggt(b, a % b);
        }
        return inter;
    }

    public int kgv(int a, int b) {
        return Math.abs(a * b) / ggt(a,b);
    }

}
