import java.util.*;
import java.io.*;

public class unboundedKnapSack {

    public static int recursive(int[] wt, int[] val, int W, int n) {
        // base case
        if (n == 0 || W == 0)
            return 0;
        if (wt[n - 1] <= W)
            return Math.max(val[n - 1] + recursive(wt, val, W - wt[n - 1], n), recursive(wt, val, W, n - 1));
        else
            return recursive(wt, val, W, n - 1);
    }

    public static int unboundedKnap(int[] wt, int[] val, int W, int n) {
        return recursive(wt, val, W, n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] wt = new int[n];
        for (int i = 0; i < n; i++)
            wt[i] = scn.nextInt();
        int[] val = new int[n];
        for (int i = 0; i < n; i++)
            val[i] = scn.nextInt();
        int W = scn.nextInt();
        int maxProfit = unboundedKnap(wt, val, W, n);
        System.out.println(maxProfit);
    }
}
