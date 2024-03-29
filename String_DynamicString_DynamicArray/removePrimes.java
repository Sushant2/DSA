import java.io.*;
import java.util.*;

public class Main {
    public static boolean isprime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void solution(ArrayList<Integer> al) {
        for (int i = 0; i < al.size(); i++) {
            if (isprime(al.get(i))) {
                al.remove(i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            al.add(scn.nextInt());
        }
        solution(al);
        System.out.println(al);
    }

}