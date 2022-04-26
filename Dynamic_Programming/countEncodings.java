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

    public static int decodeWays(char[] str, int n) {
        if (n == 0 || (n == 1 && str[0] == '0'))
            return 0;
        // return recursive(str, n);
        int[] qb = new int[n + 1];
        Arrays.fill(qb, -1);
        qb[0] = 0;
        qb[1] = 1;
        return memoized(str, n, qb);
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
