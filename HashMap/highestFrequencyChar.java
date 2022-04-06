import java.io.*;
import java.util.*;

public class highestFrequencyChar {

    public static void highestFreq(String str) {
        HashMap<Character, Integer> hash = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (hash.containsKey(ch)) {
                int var = hash.get(ch);
                hash.put(ch, var + 1);
            } else
                hash.put(ch, 1);
        }

        // System.out.println(hash);
        Set<Character> keys = hash.keySet();
        char high = 'z';
        int maxFreq = 0;
        for (Character x : keys) {
            if (hash.get(x) > maxFreq) {
                maxFreq = hash.get(x);
                high = x;
            }
        }

        System.out.println(high + " " + maxFreq);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        highestFreq(str);

    }
}
