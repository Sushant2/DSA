import java.io.*;
import java.util.*;

public class arrangeBuildings {

    public static int recursive(int n, int last) {
        if (n == 0)
            return 0;
        if (n == 1) {
            if (last == 0)
                return 2;
            if (last == 1)
                return 1;
        }

        if (last == 0) {
            return recursive(n - 1, 0) + recursive(n - 1, 1);
        } else
            return recursive(n - 1, 0);
    }

    public static int arrangement(int n) {
        // space means = 0, building means = 1
        int ans = recursive(n, 0);
        return ans * ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int ans = arrangement(n);
        System.out.println(ans);
    }
}
