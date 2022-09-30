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
        // -ve base
        if (sr > dr || sc > dc) {
            ArrayList<String> base = new ArrayList<>();
            return base;
        }
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        // faith
        ArrayList<String> getHorizontal = getMazePaths(sr, sc + 1, dr, dc);
        for (String s : getHorizontal)
            ans.add("h" + s);
        ArrayList<String> getVertical = getMazePaths(sr + 1, sc, dr, dc);
        for (String s : getVertical)
            ans.add("v" + s);
        return ans;
    }

}