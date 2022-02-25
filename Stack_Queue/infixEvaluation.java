import java.io.*;
import java.util.*;

public class infixEvaluation {

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-')
            return 2;
        if (ch == '*' || ch == '/')
            return 1;
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
    }
}