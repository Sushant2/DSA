import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                mat[i][j] = sc.nextInt();
        }

        display(mat);
    }

    public static void display(int[][] mat) {
        for (int j = 0; j < mat.length; j++) {
            for (int i = mat.length - 1; i >= 0; i--)
                System.out.print(mat[i][j] + " ");
            System.out.println("");
        }
    }

}