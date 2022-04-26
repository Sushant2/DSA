import java.util.*;
import java.io.*;

public class countEncodings {

    public static int recursive(char[] str, int n) {
        // base case
        if (n == 0 || n == 1)
            return 1;
        int sum = 0;
        // consider only one digit numbers : 1,2,3,4...9
        if (str[n - 1] >= '1' && str[n - 1] <= '9')
            sum = recursive(str, n - 1);
        // consider only two digit number : 10,11,12,13,14...26
        if (str[n - 2] == '1' || str[n - 2] == '2' && str[n - 1] <= '6')
            sum += recursive(str, n - 2);
        return sum;
    }

    public static int memoized(char[] str, int n, int[] qb) {
        if (n == 0 || n == 1)
            return 1;
        if (str[0] == '0')
            return 0;
        if (qb[n] != -1)
            return qb[n];
        int sum = 0;
        // consider for 1 digit ele
        if (str[n - 1] >= '1' && str[n - 1] <= '9') {
            sum = memoized(str, n - 1, qb);
        }
        // consider for 2 digit ele
        if (str[n - 2] == '1' || str[n - 2] == '2' && str[n - 1] <= '6') {
            sum += memoized(str, n - 2, qb);
        }
        return qb[n] = sum;
    }

    public static int tabulation(char[] str, int n) {
        // step1: create storage & assign meaning to cells
        int[] dp = new int[n];
        dp[0] = 1;// 1 length ke lie 1 hi encoding hogi
        // meaning of cell : dp[i] means ith string tak ke kitne encodings hai
        // step2 : direction of problem
        // small : dp[0], large: dp[n]
        // step 3 : travel & solve
        for (int i = 1; i < dp.length; i++) {
            // case 1: if i-1 & i both are zeroes
            if (str[i - 1] == '0' && str[i] == '0')
                dp[i] = 0;
            // case 2: if i-1 is zero & i is non zero
            else if (str[i - 1] == '0' && str[i] != '0')
                dp[i] = dp[i - 1];
            // case 3: if i-1 is nonzero & i is zero
            else if (str[i - 1] != '0' && str[i] == '0') {
                if (str[i - 1] == '1' || str[i - 1] == '2')
                    dp[i] = (i >= 2 ? dp[i - 2] : 1);
                else
                    dp[i] = 0;
            }
            // case 4: if i-1 & i both are nonzeros
            else {
                if (str[i - 1] == '1' || str[i - 1] == '2' && str[i] <= '6')
                    dp[i] = dp[i - 1] + (i >= 2 ? dp[i - 2] : 1);
                else
                    dp[i] = dp[i - 1];
            }
        }
        return dp[n - 1];
    }

    public static int decodeWays(char[] str, int n) {
        if (n == 0 || (n == 1 && str[0] == '0'))
            return 0;
        // return recursive(str, n);
        int[] qb = new int[n + 1];
        Arrays.fill(qb, -1);
        qb[0] = 0;
        qb[1] = 1;
        // return memoized(str, n, qb);
        return tabulation(str, n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        char[] str = String.valueOf(n).toCharArray();
        int ways = decodeWays(str, str.length);
        System.out.println(ways);
        scn.close();
    }
}
