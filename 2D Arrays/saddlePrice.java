import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        // row wise traverse to find column no
        for (int i = 0; i < arr.length; i++) {
            int lc = 0; // initially column no = 0
            for (int j = 1; j < arr[0].length; j++) {
                if (arr[i][j] < arr[i][lc]) {
                    lc = j;
                }
            }
            // now we got col no, traverse column wise to find check max in it
            boolean flag = true;
            for (int k = 0; k < arr.length; k++) {
                if (arr[k][lc] > arr[i][lc]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(arr[i][lc]);
                return;
            }
        }
        System.out.println("Invalid input");
    }

}