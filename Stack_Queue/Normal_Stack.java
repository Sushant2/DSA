import java.io.*;
import java.util.*;

public class Normal_Stack {
    public static void main(String[] args) throws Exception {
        // ! Stack Using Java Collections
        Stack<Integer> stk = new Stack<>();
        // initialised an empty stack
        if (stk.empty())
            System.out.println("Stack is empty!");
        else
            System.out.println("Stack is not empty!");
        stk.push(10);
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        stk.push(20);
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        stk.push(30);
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        stk.push(40);
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        stk.push(50);
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        System.out.println("Curr Size: " + stk.size());
        int idx = stk.search(20);
        System.out.println("20 is found at index: " + idx);
        stk.pop();
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        System.out.println("Curr Size: " + stk.size());
        stk.pop();
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        System.out.println("Curr Size: " + stk.size());
        stk.pop();
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        System.out.println("Curr Size: " + stk.size());
        stk.pop();
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        System.out.println("Curr Size: " + stk.size());
        stk.pop();
        System.out.println("Curr Stack: " + stk);
        System.out.println("Top: " + stk.peek());
        System.out.println("Curr Size: " + stk.size());
    }
}
