import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ngetoleft {

    public static int[] ngetoleft1(int[] arr) {
        int[] nge = new int[arr.length];
        Arrays.fill(nge, -1);
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (stk.size() > 0 && arr[stk.peek()] <= arr[i]) {
                stk.pop();
            }
            if (stk.size() > 0) {
                nge[i] = stk.peek();
            }
            stk.push(i);
        }
        return nge;
    }

    public static int[] ngetoleft2(int[] arr) {
        int[] nge = new int[arr.length];
        Arrays.fill(nge, -1);
        Stack<Integer> stk = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (stk.size() > 0 && arr[stk.peek()] < arr[i]) {
                nge[stk.pop()] = i;
            }
            stk.push(i);
        }
        return nge;
    }

    public static void display(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int val : arr) {
            sb.append(val + "\n");
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
        int[] nge = ngetoleft2(a);
        display(nge);
    }
}
