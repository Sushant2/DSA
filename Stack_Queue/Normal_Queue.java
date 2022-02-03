import java.io.*;
import java.util.*;

public class Normal_Queue {
    public static void main(String[] args) throws Exception {
        // TODO: Queue using java collection
        // ! we don't declare/initialise queue like this cos, queue is an interface &
        // interfaces can't be instantiated
        // Queue<Integer> q = new Queue<>();
        // ? so, we declare it like this
        Queue<Integer> q = new ArrayDeque<>();
        q.add(10);
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        q.add(20);
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        q.add(30);
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        q.add(40);
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        System.out.println("Curr Size: " + q.size());
        q.remove();
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        q.remove();
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        q.remove();
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        q.remove();
        System.out.println("Curr Size: " + q.size());
        System.out.println("Curr Queue: " + q);
        System.out.println("Curr Top: " + q.peek());
        q.remove();
    }
}
