import java.io.*;
import java.util.*;

public class nsetoleft {
    // ! approach - O(n)
    // ? traverse from left to right
    // ? pop those who are greater or equal to arr[i]
    // ? stk.peek() will be ur answer after popping
    // ? push urself cos u can be answer of someone
    public static int[] nsetoleft1(int[] arr) {
        int[] nsel = new int[arr.length];
        Arrays.fill(nsel, -1);
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (stk.size() > 0 && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            if (stk.size() > 0) {
                nsel[i] = stk.peek();
            }
            stk.push(i);
        }
        return nsel;
    }
    //! approach - O(n)
    //? traverse from right to left
    //? pop those who are larger then you/arr[i], & u become answer of those popped ele
    //? push youself as, you want your answer
    public static int[] nsetoleft2(int[] arr) {
        int[] nsel = new int[arr.length];
        Arrays.fill(nsel, -1);
        Stack<Integer> stk = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (stk.size() > 0 && arr[stk.peek()] > arr[i]) {
                nsel[stk.pop()] = i;
            }
            stk.push(i);
        }
        return nsel;
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
        int[] nsel = nsetoleft1(a);
        display(nsel);

    }
}
