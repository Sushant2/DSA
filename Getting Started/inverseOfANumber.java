import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int inv_ans = 0;
        int pv = 1;
        while (n != 0) {
            int ipv = n % 10;
            int ifv = pv;
            int div = 1;
            for (int i = 1; i < ipv; i++) {
                div *= 10;
            }
            inv_ans += (ifv * div);
            pv++;
            n /= 10;
        }
        System.out.println(inv_ans);
    }
}