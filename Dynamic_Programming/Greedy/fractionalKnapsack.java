import java.io.*;
import java.util.*;

//!TIME COMPL : O(nlogn) + O(n), SPACE COMPL : O(1)
public class fractionalKnapsack {

    public static class Item {
        int weight, value;

        Item(int w, int v) {
            this.weight = w;
            this.value = v;
        }
    }

    public static class itemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            double r1 = (double) (a.value) / (double) (a.weight);
            double r2 = (double) (b.value) / (double) (b.weight);
            if (r1 < r2)
                return 1;
            else if (r1 > r2)
                return -1;
            else
                return 0;
        }
    }

    public static double findMaxProfit(int W, Item[] arr, int n) {
        Arrays.sort(arr, new itemComparator());
        int currWeight = 0;
        double finalValue = 0.0;
        for (int i = 0; i < n; i++) {
            if (currWeight + arr[i].weight <= W) {
                currWeight += arr[i].weight;
                finalValue += arr[i].value;
            } else {
                int remain = W - currWeight;
                finalValue += ((double) arr[i].value / (double) arr[i].weight) * remain;
                break;
            }
        }
        return finalValue;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int W = scn.nextInt();
        Item[] arr = new Item[n];
        for (int i = 0; i < n; i++) {
            int val = scn.nextInt();
            int weight = scn.nextInt();
            arr[i] = new Item(weight, val);
        }
        double maxProfit = findMaxProfit(W, arr, n);
        System.out.println(maxProfit);
    }

}
