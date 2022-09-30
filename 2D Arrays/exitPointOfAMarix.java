import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                arr[i][j] = scn.nextInt();
        }
        int direction = 0; // 0-east, 1-south, 2-west, 3-north
        int r = 0, c = 0;
        while (true) {
            direction = (direction + arr[r][c]) % 4;
            if (direction == 0) {
                c++;
            } else if (direction == 1) {
                r++;
            } else if (direction == 2) {
                c--;
            } else {
                r--;
            }
            if (r < 0) {
                r++;
                break;
            }
            if (c < 0) {
                c++;
                break;
            }
            if (r == arr.length) {
                r--;
                break;
            }
            if (c == arr[0].length) {
                c--;
                break;
            }
        }
        System.out.println(r);
        System.out.println(c);

    }

}

// OR

/*
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                arr[i][j] = scn.nextInt();
        }
        int direction = 0; // 0-east, 1-south, 2-west, 3-north
        int r = 0, c = 0;
        int currR = 0;
        int currC = 0;
        while (r >= 0 && c >= 0 && r < arr.length && c < arr.length) {
            if (arr[r][c] == 1) {
                direction = (direction + 1) % 4;
            }
            currR = r;
            currC = c;
            if (direction == 0) {
                c++;
            } else if (direction == 1) {
                r++;
            } else if (direction == 2) {
                c--;
            } else {
                r--;
            }
        }
        System.out.println(currR);
        System.out.println(currC);

    }

}


OR

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = scn.nextInt();
            }
        }
        int direction = 0;
        //towards east = 0,towards south = 1,towards west = 2,towards north = 3
        //intitally i=0,j=0;
        int i = 0,j = 0;
        while(true){
            direction = (direction + arr[i][j]) % 4;
            if(direction == 0){
                j++;
            }
            else if(direction == 1){
                i++;
            }
            else if(direction == 2){
                j--;
            }
            else{
                i--;
            }
            if(i<0){
                i++;
                break;
            }
            else if(j<0){
                j++;
                break;
            }
            else if(i==n){
                i--;
                break;
            }else if(j==m){
                j--;
                break;
            }
        }
        System.out.println(i);
        System.out.println(j);
    }

}

*/