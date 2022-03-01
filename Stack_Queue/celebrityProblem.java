import java.util.*;
import java.io.*;

public class celebrityProblem {

    public static void findCeleb(int[][] arr) {
        // stack to store possible celebs indexes
        Stack<Integer> pCelebs = new Stack<>();
        // intially store all possible celebs in stack
        for (int i = 0; i < arr.length; i++)
            pCelebs.push(i);
        // while only one celeb is left in stack
        while (pCelebs.size() > 1) {
            // pop 2 celebs from index & check which one is actually a celeb
            int b = pCelebs.pop();
            int a = pCelebs.pop();
            if (arr[a][b] == 0) {
                // b can't be celeb in that case
                pCelebs.push(a);
            }
            if (arr[a][b] == 1) {
                // a can't celeb in that case
                pCelebs.push(b);
            }
        }
        // confirmation that the last remaining celeb is actually a celeb or not
        int celeb = pCelebs.pop();
        // if celeb - then it's row = 0, except it's cell
        for (int j = 0; j < arr.length; j++) {
            if (j == celeb)
                continue;
            if (arr[celeb][j] == 1) {
                System.out.println("No Celebrity!");
                return;
            }
        }
        // if celeb - then it's column = 0, except it's cell
        for (int i = 0; i < arr.length; i++) {
            if (i == celeb)
                continue;
            if (arr[i][celeb] == 0) {
                System.out.println("No Celebrity!");
                return;
            }
        }

        System.out.println("Celebrity is: " + celeb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        findCeleb(arr);
    }
}
