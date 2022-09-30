import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        int b = scn.nextInt();
        int tempa = a, tempb = b;
        // find GCD(i will use Euclidean)
        while (a % b != 0) {
            int rem = a % b;
            a = b;
            b = rem;
        }
        int gcd = b;
        // lcm = a*b/gcd
        int lcm = (tempa * tempb) / gcd;
        System.out.println(gcd);
        System.out.println(lcm);
    }
}