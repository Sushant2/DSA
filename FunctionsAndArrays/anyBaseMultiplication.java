import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int b = scn.nextInt();
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();

        int d = getProduct(b, n1, n2);
        System.out.println(d);
    }

    public static int getProduct(int b, int n1, int n2) {
        int ans = 0, pow = 1;
        while (n2 > 0) {
            int d2 = n2 % 10;
            int sdp = singledigitproduct(b, n1, d2);
            ans = getSum(b, ans, sdp * pow);
            pow *= 10;
            n2 /= 10;
        }
        return ans;
    }

    public static int singledigitproduct(int b, int n1, int d2) {
        int ans = 0, pow = 1, carry = 0;
        while (carry > 0 || n1 > 0) {
            int d1 = n1 % 10;
            int tempmul = d1 * d2 + carry;
            carry = tempmul / b;
            int rem = tempmul % b;
            ans += rem * pow;
            pow *= 10;
            n1 /= 10;
        }
        return ans;
    }

    public static int getSum(int b, int n1, int n2) {
        int pow = 1, ans = 0, carry = 0;
        while (carry > 0 || n1 > 0 || n2 > 0) {
            int d1 = n1 % 10;
            int d2 = n2 % 10;
            int tempsum = carry + d1 + d2;
            carry = tempsum / b;
            int rem = tempsum % b;
            ans += rem * pow;
            pow *= 10;
            n1 /= 10;
            n2 /= 10;
        }
        return ans;
    }
}