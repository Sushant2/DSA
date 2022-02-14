import java.io.*;
import java.util.*;

public class printDecInc {
    public static void printDI(int n) {
        // base case
        if (n == 0)
            return;
        // preorder expec
        System.out.println(n);
        // faith
        printDI(n - 1);
        // postorder expec
        System.out.println(n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        printDI(n);
    }
}
