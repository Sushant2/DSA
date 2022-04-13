import java.util.*;
import java.io.*;

public class CCinOurPQ {
    // khud ki PQ implement
    public static class PriorityQueue {
        ArrayList<Integer> data;

        public PriorityQueue() {
            data = new ArrayList<>();
        }

        // add
        public void add(int val) {
            data.add(val);
            // to maintain heap order property
            // do upheapify while insertion
            upHeapify(size() - 1);
        }

        public void upHeapify(int idx) {
            int parIdx = (idx - 1) / 2;
            if (isSmaller(idx, parIdx)) {
                swap(idx, parIdx);
                upHeapify(parIdx);
            }
        }

        public boolean isSmaller(int i, int j) {
            if (data.get(i) < data.get(j))
                return true;
            return false;
        }

        public void swap(int i, int j) {
            int temp = data.get(i);
            data.set(i, data.get(j));
            data.set(j, temp);
        }

        // remove
        public int remove() {
            if (size() == 0) {
                System.out.println("Underflow!");
                return -1;
            }
            int val = peek();
            swap(0, size() - 1);
            data.remove(size() - 1);
            downHeapify(0);
            return val;
        }

        public void downHeapify(int idx) {
            int min = idx;
            int leftIdx = 2 * idx + 1;
            int rightIdx = 2 * idx + 2;
            if (leftIdx < size() && isSmaller(leftIdx, min))
                min = leftIdx;
            if (rightIdx < size() && isSmaller(rightIdx, min))
                min = rightIdx;
            if (min != idx) {
                swap(idx, min);
                downHeapify(min);
            }
        }

        // peek
        public int peek() {
            if (size() == 0) {
                System.out.println("Underflow!");
                return -1;
            }
            return data.get(0);
        }

        public int size() {
            return data.size();
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = { 10, 2, 17, 3, 9, 18, 22 };
        PriorityQueue pq = new PriorityQueue();
        for (int val : arr)
            pq.add(val);

        while (pq.size() > 0) {
            System.out.print(pq.remove() + " ");
        }
    }
}
