import java.io.*;
import java.util.*;

public class printDecreasing {
    public static void printDec(int n) {
        // base cond
        if (n == 0)
            return;
        // meeting expec
        System.out.println(n);
        // faith
        printDec(n - 1);
    }

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // int n = Integer.parseInt(br.readLine());
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        printDec(n);
        scn.close();
    }
}
