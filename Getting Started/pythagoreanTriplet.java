import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        int b = scn.nextInt();
        int c = scn.nextInt();
        if (b > a && b > c) {
            int temp = a;
            a = b;
            b = c;
            c = temp;
        } else if (c > a && c > b) {
            int temp = a;
            a = c;
            c = b;
            b = temp;
        }
        if ((a * a) == (b * b) + (c * c))
            System.out.println(true);
        else
            System.out.println(false);
    }
}