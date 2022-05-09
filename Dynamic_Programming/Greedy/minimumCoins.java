import java.util.*;
import java.io.*;

public class minimumCoins {

    // !TIME COMPL : O(V), SPACE COMPL : O(1)
    public static int[] findMinCoins(int[] coins, int n, int V) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (V >= coins[i]) {
                V -= coins[i];
                ans.add(coins[i]);
            }
        }
        int[] finalA = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            finalA[i] = ans.get(i);
        }
        return finalA;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int coins[] = { 1, 2, 5, 10, 20, 50, 100, 500, 1000 };
        int n = 9;
        int V = scn.nextInt();
        int[] minCoins = findMinCoins(coins, n, V);
        System.out.println(minCoins.length);
        for (int i = 0; i < minCoins.length; i++)
            System.out.print(minCoins[i] + " ");
    }
}
