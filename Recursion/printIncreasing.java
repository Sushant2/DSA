import java.io.*;
import java.util.*;

public class printIncreasing {
    public static void printInc(int n) {
        // base case
        if (n == 0)
            return;
        // faith
        printInc(n - 1);
        // meeting expec
        System.out.println(n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        printInc(n);
        scn.close();
    }

}
