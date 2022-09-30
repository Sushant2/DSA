import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int b = scn.nextInt();
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();

        int d = getDifference(b, n1, n2);
        System.out.println(d);
    }

    public static int getDifference(int b, int n1, int n2) {
        int pow = 1, borrow = 0, ans = 0;
        while (n2 > 0) {
            int d1 = n1 % 10;
            int d2 = n2 % 10;
            int tempdiff = d2 - d1 + borrow;
            if (tempdiff < 0) {
                ans += (tempdiff + b) * pow;
                borrow = -1;
            } else {
                ans += tempdiff * pow;
                borrow = 0;
            }
            pow *= 10;
            n1 /= 10;
            n2 /= 10;
        }
        return ans;
    }

}