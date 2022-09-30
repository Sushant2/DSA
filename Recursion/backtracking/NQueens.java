import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        boolean[][] chess = new boolean[n][n];
        printNQueens(chess, "", 0);
    }

    public static boolean isQueenSafe(boolean[][] chess, int row, int col) {
        // upward col
        for (int i = 0; i < row; i++) {
            if (chess[i][col])
                return false;
        }

        // upward left diagonal
        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (chess[i][j])
                return false;
            i--;
            j--;
        }

        // upward right diagonal
        i = row;
        j = col;
        while (i >= 0 && j < chess.length) {
            if (chess[i][j])
                return false;
            i--;
            j++;
        }

        return true;
    }

    public static void printNQueens(boolean[][] chess, String qsf, int row) {
        // base case
        if (row == chess.length) {

            System.out.println(qsf + ".");
            return;
        }
        // columns as options
        for (int col = 0; col < chess[0].length; col++) {
            if (isQueenSafe(chess, row, col)) {
                // edge-pre
                chess[row][col] = true;
                // faith
                printNQueens(chess, qsf + row + "-" + col + ", ", row + 1);
                // edge-post
                chess[row][col] = false;
            }
        }
    }
}