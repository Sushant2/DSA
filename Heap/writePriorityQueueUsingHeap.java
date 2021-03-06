import java.io.*;
import java.util.*;

public class writePriorityQueueUsingHeap {
    public static class PriorityQueue {
        ArrayList<Integer> data;

        public PriorityQueue() {
            data = new ArrayList<>();
        }

        public void add(int val) {
            // O(logn) - to insert an element
            data.add(val); // add at last
            upheapify(size() - 1); // upheapify call to
        }

        // O(logn) - n for no of nodes in complete binary tree & logn is the height of
        // tree
        public void upheapify(int idx) {
            // no need for base case - as it'll stop at -1/2 == 0(for root node)
            int parIdx = (idx - 1) / 2;
            if (isSmaller(idx, parIdx)) {
                swap(idx, parIdx);
                upheapify(parIdx);
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

        public int peek() {
            // O(1
            if (size() == 0) {
                System.out.println("Underflow!");
                return -1;
            }
            return data.get(0);
        }

        public int size() {
            return data.size();
        }

        public int remove() {
            // O(logn)
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
            int min = idx; // khud ka index
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
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue qu = new PriorityQueue();

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("add")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                qu.add(val);
            } else if (str.startsWith("remove")) {
                int val = qu.remove();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("peek")) {
                int val = qu.peek();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(qu.size());
            } else
                System.out.println("Write valid command!");
            str = br.readLine();
        }
    }
}
