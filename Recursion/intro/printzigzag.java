import java.io.*;
import java.util.*;

public class printzigzag {
    public static void pzz(int n) {
        // base case
        if (n == 0)
            return;
        // meeting expec
        System.out.print(n + " ");
        // faith
        pzz(n - 1);
        // meeting expec
        System.out.print(n + " ");
        // faith
        pzz(n - 1);
        // meeting expec
        System.out.print(n + " ");
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        pzz(n);
        scn.close();
    }
}
