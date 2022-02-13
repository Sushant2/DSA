import java.io.*;
import java.util.*;

public class nsetoright {
    // ! approach - O(n)
    // ? traverse left to right
    // ? pop those ele who are greater than u,& ur index is ans for them
    // ? push urself as you want ur ans
    public static int[] nsetoright1(int[] arr) {
        int[] nser = new int[arr.length];
        Arrays.fill(nser, arr.length);
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (stk.size() > 0 && arr[stk.peek()] > arr[i]) {
                nser[stk.pop()] = i;
            }
            stk.push(i);
        }
        return nser;
    }

    public static int[] nsetoright2(int[] arr) {
        int[] nser = new int[arr.length];
        Arrays.fill(nser, arr.length);
        Stack<Integer> stk = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (stk.size() > 0 && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            if (stk.size() > 0) {
                nser[i] = stk.peek();
            }
            stk.push(i);
        }
        return nser;
    }

    public static void display(int[] arr) {
        StringBuilder str = new StringBuilder();
        for (int val : arr) {
            str.append(val + " ");
        }
        System.out.println(str);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        int[] nser = nsetoright2(a);
        display(nser);
    }
}
