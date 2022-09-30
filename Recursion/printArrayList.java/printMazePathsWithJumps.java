import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        printMazePaths(0, 0, n - 1, m - 1, "");
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static void printMazePaths(int sr, int sc, int dr, int dc, String output) {
        // base case
        if (sr == dr && sc == dc) {
            System.out.println(output);
            return;
        }

        // horizontally
        int jump = 1;
        while (sc + jump <= dc) {
            printMazePaths(sr, sc + jump, dr, dc, output + "h" + jump);
            jump++;
        }
        // vertically
        jump = 1;
        while (sr + jump <= dr) {
            printMazePaths(sr + jump, sc, dr, dc, output + "v" + jump);
            jump++;
        }
        // diagonally
        jump = 1;
        while (sr + jump <= dr && sc + jump <= dc) {
            printMazePaths(sr + jump, sc + jump, dr, dc, output + "d" + jump);
            jump++;
        }
    }
}