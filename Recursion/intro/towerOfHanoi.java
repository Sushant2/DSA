import java.io.*;
import java.util.*;

public class towerOfHanoi {
    public static void toh(int n, int src, int des, int aux) {
        // base case
        if (n == 0)
            return;
        // faith
        toh(n - 1, src, aux, des);
        // meeting expec
        System.out.println(n + " [ " + src + " -> " + des + " ]");
        // faith
        toh(n - 1, aux, des, src);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int A = scn.nextInt();
        int B = scn.nextInt();
        int C = scn.nextInt();
        toh(n, A, B, C);
        scn.close();
    }
}
