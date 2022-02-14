import java.io.*;
import java.util.*;

public class powerLinear {
    public static int powerL(int x, int n) {
        // base
        if (n == 0)
            return 1;
        // faith
        int smallAns = powerL(x, n - 1);
        // meeting expec
        return smallAns * x;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int x = scn.nextInt();
        int n = scn.nextInt();
        System.out.println(powerL(x, n));
        scn.close();
    }
}
