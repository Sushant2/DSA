import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        ArrayList<String> ans = getMazePaths(0, 0, n - 1, m - 1);
        System.out.println(ans);
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {

        // +ve base case
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        // final ans
        ArrayList<String> res = new ArrayList<>();
        // horizontal faith
        int jump = 1;
        while (sc + jump <= dc) {
            ArrayList<String> getHor = getMazePaths(sr, sc + jump, dr, dc);
            for (String str : getHor)
                res.add("h" + jump + str);
            jump++;
        }
        // vertical faith
        jump = 1;
        while (sr + jump <= dr) {
            ArrayList<String> getVer = getMazePaths(sr + jump, sc, dr, dc);
            for (String str : getVer)
                res.add("v" + jump + str);
            jump++;
        }
        // diagonal faith
        jump = 1;
        while (sr + jump <= dr && sc + jump <= dc) {
            ArrayList<String> getDiag = getMazePaths(sr + jump, sc + jump, dr, dc);
            for (String str : getDiag)
                res.add("d" + jump + str);
            jump++;
        }
        return res;
    }

}