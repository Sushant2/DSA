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

        while (!pq.isEmpty()) {
            int val = pq.remove();
            System.out.print(val + " ");
        }
    }
}