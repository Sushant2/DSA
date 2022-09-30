/*
1												1	
1	2										2	1	
1	2	3								3	2	1	
1	2	3	4						4	3	2	1	
1	2	3	4	5				5	4	3	2	1	
1	2	3	4	5	6		6	5	4	3	2	1	
1	2	3	4	5	6	7	6	5	4	3	2	1	
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int size = scn.nextInt();

        int sp = 2 * size - 3;

        for (int i = 1; i <= size; i++) {

            // number printing loop
            for (int j = 1; j <= i; j++) {
                if (j == size) {
                    // due to this last number in last iteration
                    // will not be printed.
                    continue;
                }
                System.out.print(j + "\t");
            }

            // spaces printing loop
            for (int j = 1; j <= sp; j++) {
                System.out.print("\t");
            }

            // number printing loop in reverse
            for (int j = i; j >= 1; j--) {
                System.out.print(j + "\t");
            }

            // Enter new line
            System.out.println();
            sp -= 2;
        }

    }
}