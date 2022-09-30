import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int k = scn.nextInt();
        // 1. count no of digits
        int nod = 0;
        int temp = n;
        while (temp != 0) {
            nod++;
            temp /= 10;
        }
        // 2. manage k for -ve & k>n
        k = k % nod;
        if (k < 0) {
            k = k + nod;
        }

        // 3. find divisor & multiplier for v1 & v2
        int div = 1, mul = 1;
        for (int i = 0; i < nod; i++) {
            if (i < k) {
                div = div * 10;
            } else {
                mul = mul * 10;
            }
        }
        // 4. find v1 & v2
        int v1 = n / div;
        int v2 = n % div;
        int ans = v2 * mul + v1;
        System.out.println(ans);
    }
}