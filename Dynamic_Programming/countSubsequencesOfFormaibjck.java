import java.io.*;
import java.util.*;

public class countSubsequencesOfFormaibjck {

    // time compl : O(n) , space comp : O(1)
    public static int countSubsequences(String s) {
        // initally patterns of a, ab, & abc all are zero/0
        int a = 0;
        int ab = 0;
        int abc = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a')
                a = 2 * a + 1;
            else if (ch == 'b')
                ab = 2 * ab + a;
            else if (ch == 'c')
                abc = 2 * abc + ab;
        }

        return abc;
    }

    // time comp: O(n), space comp : O(n)
    public static int tabulation(String s) {
        // step 1: creating storage & assign meaning to cells
        // 3 rows = a+ as 0, a+b+ as 1 & a+b+c+ as 2
        int[][] dp = new int[3][s.length() + 1];
        // meaning of cell : dp[1][i] means for pattern "a+b+" & string length 'i', what
        // are the subsequences
        // step 2: direction of problem : choti problem dp[0][i], badi problem dp[2][n]
        // step 3: travel & solve
        for (int i = 1; i < dp[0].length; i++) {
            char ch = s.charAt(i - 1);
            if (ch == 'a') {
                dp[0][i] = 2 * dp[0][i - 1] + 1;
                dp[1][i] = dp[1][i - 1];
                dp[2][i] = dp[2][i - 1];
            } else if (ch == 'b') {
                dp[0][i] = dp[0][i - 1];
                dp[1][i] = 2 * dp[1][i - 1] + dp[0][i];
                dp[2][i] = dp[2][i - 1];
            } else if (ch == 'c') {
                dp[0][i] = dp[0][i - 1];
                dp[1][i] = dp[1][i - 1];
                dp[2][i] = 2 * dp[2][i - 1] + dp[1][i];
            }
        }
        return dp[2][s.length()];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        // int count = countSubsequences(str);
        int count = tabulation(str);
        System.out.println(count);
    }
}
