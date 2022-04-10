import java.io.*;
import java.util.*;

public class heap {
    public static void main(String[] args) throws Exception {
        // ! priority queue is bydefault min heap & max priority queue is max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(80);
        pq.add(30);
        pq.add(40);
        pq.add(60);
        pq.add(50);

        System.out.println(pq);

        System.out.println("Size: " + pq.size());

        while (!pq.isEmpty()) {
            int val = pq.remove();
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println("Size: " + pq.size());

        // max Heap
        PriorityQueue<Integer> mpq = new PriorityQueue<>(Collections.reverseOrder());

        mpq.add(10);
        mpq.add(40);
        mpq.add(20);
        mpq.add(60);
        mpq.add(90);
        mpq.add(30);

        System.out.println(mpq);

        System.out.println("Size: " + mpq.size());

        while (mpq.size() > 0) {
            int val = mpq.remove();
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println("Size: " + mpq.size());
    }
}