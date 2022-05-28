import java.util.*;

public class anyBaseToAnyBase {
    private static int anyBaseToDec(int n, int b) {
        int ans = 0;
        int pow = 1;
        while (n != 0) {
            int rem = n % 10;
            ans += rem * pow;
            pow *= b;
            n /= 10;
        }
        return ans;
    }

    private static int decToAnyBase(int n, int b) {
        int ans = 0;
        int pow = 1;
        while (n != 0) {
            int rem = n % b;
            ans += rem * pow;
            pow *= 10;
            n /= b;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sourceBase = scn.nextInt();
        int destBase = scn.nextInt();
        // any base to decimal & decimal to anybase
        int ad = anyBaseToDec(n, sourceBase);
        int da = decToAnyBase(ad, destBase);
        System.out.println(da);
    }
}