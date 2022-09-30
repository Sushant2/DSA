import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int n = obj.nextInt();
        int count = 1;
        while (n / 10 > 0) {
            count++;
            n = n / 10;
        }
        System.out.println(count);
    }
}