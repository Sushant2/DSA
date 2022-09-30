import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        int frow = 0, fcol = 0, lrow = n - 1, lcol = m - 1;
        int count = 0;
        while (count <= n * m) {
            // left wall
            for (int i = frow; i <= lrow; i++) {
                System.out.println(arr[i][fcol]);
                count++;
                if (count == n * m)
                    return;
            }
            fcol++;
            // down wall
            for (int i = fcol; i <= lcol; i++) {
                System.out.println(arr[lrow][i]);
                count++;
                if (count == n * m)
                    return;
            }
            lrow--;
            // right wall
            for (int i = lrow; i >= frow; i--) {
                System.out.println(arr[i][lcol]);
                count++;
                if (count == n * m)
                    return;
            }
            lcol--;
            // top wall
            for (int i = lcol; i >= fcol; i--) {
                System.out.println(arr[frow][i]);
                count++;
                if (count == n * m)
                    return;
            }
            frow++;
        }
    }

}