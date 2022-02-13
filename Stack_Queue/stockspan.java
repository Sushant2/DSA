import java.io.*;
import java.util.*;

public class stockspan {

    //! approach - O(n)
    //? finding next greater ele to left of every element
    //? difference between that index * ngel of that index
    public static int[] findStockSpan(int[] arr) {
        int[] stocks = new int[arr.length];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (stk.size() > 0 && arr[stk.peek()] <= arr[i]) {
                stk.pop();
            }
            if (stk.size() > 0) {
                stocks[i] = (i - stk.peek());
            } else {
                stocks[i] = i + 1;
            }
            stk.push(i);
        }
        return stocks;
    }

    public static void display(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int val : arr) {
            sb.append(val + " ");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        int[] stockspan = findStockSpan(a);
        display(stockspan);
    }
}
