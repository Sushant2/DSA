import java.io.*;
import java.util.*;

public class maxAreaHistogram {
    //! Approach - O(n)
    //? for each n every ele find it's nser, & nsel(cos we can find the max area covered by that ele/refernce)
    //? nextsmallertoleft, nextsmaller to right
    //? area = length*height i.e, 
        //? length = nser[i]-nsel[i]-1 //-1 as both/[nser[i],nsel[i]] are inclusive
    //? so find max area of all ele & return maxArea of histogram
    public static int[] nseright(int[] arr) {
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

    public static int[] nseleft(int[] arr) {
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

    public static void main(String[] str) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] nsel = nseleft(arr);
        int[] nser = nseright(arr);
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int currArea = (nser[i] - nsel[i] - 1) * arr[i];
            maxArea = Math.max(maxArea, currArea);
        }
        System.out.println("Max Area Of Histogram: " + maxArea);
    }
}
