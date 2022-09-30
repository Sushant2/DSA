import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        boolean[][] visited = new boolean[n][m];
        floodfill(arr, 0, 0, "", visited);
    }

    // asf -> answer so far
    public static void floodfill(int[][] maze, int sr, int sc, String psf, boolean[][] visited) {
        int dr = maze.length - 1;
        int dc = maze[0].length - 1;
        // -ve base case
        if (sr > dr || sc > dc || sr < 0 || sc < 0 || maze[sr][sc] == 1 || visited[sr][sc] == true)
            return;
        // +ve base case
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return;
        }
        visited[sr][sc] = true;
        // faiths
        floodfill(maze, sr - 1, sc, psf + "t", visited);
        floodfill(maze, sr, sc - 1, psf + "l", visited);
        floodfill(maze, sr + 1, sc, psf + "d", visited);
        floodfill(maze, sr, sc + 1, psf + "r", visited);
        // backtracking
        visited[sr][sc] = false;
    }
}