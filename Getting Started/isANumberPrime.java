import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        int c = 1;
        while (c <= t) {
            // for(int i = 0;i<t;i++){
            int n = scn.nextInt();
            boolean flag = true;
            int j = 2;
            while (j * j <= n) {
                if (n % j == 0) {
                    flag = false;
                    break;
                }
                j++;
            }
            if (flag) {
                System.out.println("prime");
            } else {
                System.out.println("not prime");
            }
            c = c + 1;
        }
        // }
    }
}