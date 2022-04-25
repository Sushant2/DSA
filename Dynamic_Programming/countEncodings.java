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

    public static int decodeWays(char[] str, int n) {
        return recursive(str, n);
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
