import java.util.*;
import java.io.*;

public class celebrityProblem {

    public static int[][] arr;

    // ! using stack - time complexity - O(n), extra spcae - O(n) for stack

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

    // ! using recursion - time complexity - O(n) , Space complexity - O(1)
    public static int knows(int x, int y) {
        return arr[x][y];
    }

    public static int celebRecusive(int n) {
        // base case
        if (n == 0)
            return -1;
        // faith
        int id = celebRecusive(n - 1);
        // meeting expec
        // initially when id == -1,means it reached at the base case, so return n-1
        // value
        if (id == -1)
            return n - 1;
        else if (knows(id, n - 1) == 1)
            // id knows nth person, so id can't be celeb
            return n - 1;
        else if (knows(id, n - 1) == 0)
            // id doesn't know nth person, so n can't be celeb
            return id;
        // if there is no celebrity
        return -1;
    }

    public static int findCelebRec(int[][] arr, int n) {
        int celeb = celebRecusive(n);
        // final confirmation to check that returned celeb is really a celeb or not
        if (celeb == -1)
            return celeb;
        else {
            int c1 = 0, c2 = 0;
            // it a celeb, then;s it's row should be zero & colum should be one
            for (int i = 0; i < n; i++) {
                if (i != celeb) {
                    c1 += knows(celeb, i);
                    c2 += knows(i, celeb);
                }
            }
            if (c1 == 0 && c2 == n - 1)
                return celeb;
        }
        return -1;
    }

    // ! using 2 pointers - time complexity - O(n), spapce complexity - O(1) no
    // extra space
    public static int findCeleb2ptr(int[][] arr, int n) {
        // 2 pointers, one from starting - i, one from end - j
        int i = 0, j = n - 1;
        while (i < j) {
            if (arr[j][i] == 1)
                // j knows i, j can't be celeb
                j--;
            else
                // j doesn't know i,i can't be celeb
                i++;
        }

        // at last i is pointing to remaining ele
        int potceleb = i;
        // confirmation check whether postceleb is actually a celeb or not
        for (int k = 0; k < n; k++) {
            if (k != potceleb) {
                if (arr[k][potceleb] == 0 || arr[potceleb][k] == 1) {
                    return -1;
                }
            }
        }
        return potceleb;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        // findCeleb(arr);
        int id = findCelebRec(arr, n);
        int candidate = findCeleb2ptr(arr, n);
        if (candidate == -1)
            System.out.println("No celebrity!");
        else
            System.out.println(candidate + " is celebrity!");

    }
}
