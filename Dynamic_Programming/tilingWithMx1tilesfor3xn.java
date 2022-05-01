import java.util.*;
import java.io.*;

//GFG : https://www.geeksforgeeks.org/tiling-with-dominoes/
//for understanding : https://math.stackexchange.com/questions/1317885/dominos-2-times-1-on-2-times-n-and-on-3-times-2n

public class tilingWithMx1tilesfor3xn {

    public static int tabulation(int n) {
        int[] A = new int[n + 1];
        int[] B = new int[n + 1];

        A[0] = 1;
        A[1] = 0;
        B[0] = 0;
        B[1] = 1;

        for (int i = 2; i <= n; i++) {
            A[i] = A[i - 2] + 2 * B[i - 1];
            B[i] = A[i - 1] + B[i - 2];
        }

        return A[n];
    }

    public static int tilingUp(int n) {
        return tabulation(n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int totalWays = tilingUp(n);
        System.out.println(totalWays);
    }
}
