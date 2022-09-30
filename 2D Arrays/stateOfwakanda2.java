import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        // here rows and columns are same
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                mat[i][j] = scn.nextInt();
        }
        // outer loop
        // suppose gap = j-i
        for (int gap = 0; gap < mat[0].length; gap++) {
            for (int i = 0, j = gap + i; j < mat[0].length; i++, j++)
                System.out.println(mat[i][j]);
        }
    }

}