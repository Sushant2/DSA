
//! Comparable and Comparator in Java's PQ
import java.util.*;
import java.io.*;

public class CCinJavaPQ {

    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] arr = { 10, 2, 17, 3, 18, 9, 22 };
        for (int val : arr)
            pq.add(val);
        while (!pq.isEmpty())
            System.out.print(pq.remove() + " ");
    }
}
