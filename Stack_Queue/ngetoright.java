import java.io.*;
import java.util.*;

public class ngetoright {
    // ! BRUTE FORCE - O(n^2)
    public static int[] ngeToRightBrute(int[] arr) {
        int[] nge = new int[arr.length];
        Arrays.fill(nge, -1);
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (arr[j] > curr) {
                    nge[i] = arr[j];
                    break;
                }
            }
        }
        return nge;
    }

    // ! using 1st approach - left to right - O(n)
    // ? - traverse from left to right
    // ? - pop stk top till - if top of stack is smaller than arr[i]
    // ? - push ourselves - as we want our ans from next elems
    public static int[] ngeToRight1(int[] arr) {
        // resultant array to return
        int[] nge = new int[arr.length];
        // to fill array initially as -1
        Arrays.fill(nge, -1);
        // stack to push/pop
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (stk.size() > 0 && arr[stk.peek()] < arr[i]) {
                nge[stk.pop()] = arr[i];
            }
            // push ourself
            stk.push(i);
        }
        return nge;
    }

    // ! using 2nd approach - right to left - O(n)
    // ? - traverse from right to left
    // ? - pop stk top till - if arr[i]>=stk.peek()
    // ? - push ourselves - as we can be ans of someonelelse
    public static int[] ngeToRight2(int[] arr) {
        int[] nge = new int[arr.length];
        Arrays.fill(nge, -1);
        Stack<Integer> stk = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            // pop smaller/equal elements to right(in the stack)
            while (stk.size() > 0 && arr[i] >= arr[stk.peek()]) {
                stk.pop();
            }
            if (stk.size() > 0) {
                nge[i] = arr[stk.peek()];
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
        int[] nge = ngeToRight2(a);
        display(nge);
    }
}