/*
		1	
	2	3	2	
3	4	5	4	3	
	2	3	2	
		1	

 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sp = n / 2, st = 1;
        int var = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sp; j++) {
                System.out.print("\t");
            }
            for (int j = 1; j <= st; j++) {
                if (j <= st / 2) {
                    System.out.print(var + "\t");
                    var += 1;
                } else {
                    System.out.print(var + "\t");
                    var -= 1;
                }
            }
            // if(i>n/2){
            // var--;
            // }else{
            // var++;
            // }

            if (i <= n / 2) {
                sp--;
                st += 2;
                var = i + 1;
            } else {
                sp++;
                st -= 2;
                var = n - i;
            }
            System.out.println();
        }

    }
}