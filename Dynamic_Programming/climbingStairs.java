
//similar to climbing stairs paths - here we're just counting all paths, not printing them
import java.io.*;
import java.util.*;

public class climbingStairs {

    public static int climbStairs(int n) {
        // base condition
        if (n == 0)
            // 0 se 0, ek path
            return 1;
        if (n < 0)
            return 0;

        int nm1 = climbStairs(n - 1);
        int nm2 = climbStairs(n - 2);
        int nm3 = climbStairs(n - 3);

        int allPaths = nm1 + nm2 + nm3;
        return allPaths;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int paths = climbStairs(n);
        System.out.println(paths);
    }
}
