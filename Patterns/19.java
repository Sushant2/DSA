/*
*	*	*	
*	*	*	
*	*	*	

 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if ((i > 1 && i <= n / 2 && j <= n / 2) || (j > n / 2 + 1 && j < n && i <= n / 2)
                        || (j > 1 && j < n / 2 + 1 && i > n / 2 + 1) || (j > n / 2 + 1 && i < n && i > n / 2 + 1)) {
                    System.out.print("\t");
                } else {
                    System.out.print("*\t");
                }
            }
            System.out.println();
        }
    }
}

// OR
/*
 * import java.util.*;
 * 
 * public class Main {
 * 
 * public static void main(String[] args) {
 * Scanner scn = new Scanner(System.in);
 * 
 * int n = scn.nextInt();
 * // for(int i=1;i<=n;i++){
 * // for(int j=1;j<=n;j++){
 * // System.out.print("*\t");
 * // }
 * // System.out.println();
 * // }
 * for (int i = 1; i <= n; i++) {
 * for (int j = 1; j <= n; j++) {
 * if ((i > 1 && i <= n / 2 && j <= n / 2) || (j > n / 2 + 1 && j < n && i <= n
 * / 2)
 * || (j > 1 && j < n / 2 + 1 && i > n / 2 + 1) || (j > n / 2 + 1 && i < n && i
 * > n / 2 + 1)) {
 * System.out.print("\t");
 * } else {
 * System.out.print("*\t");
 * }
 * }
 * System.out.println();
 * }
 * }
 * }
 */