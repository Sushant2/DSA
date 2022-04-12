import java.io.*;
import java.util.*;

public class heapSort {
    // heapsort krna hai, array of heap form ka
    // same code of PQusingHeap
    public static class PriorityQueue {
        ArrayList<Integer> data;
        private int size;

        public PriorityQueue() {
            data = new ArrayList<>();
            size = 0;
        }
        // if given an array & to perfrom insertion in one go we can modify our
        // constructor & make insertion in O(n) - instead of calling upheapify while
        // inserting, we'll call downheapify - so we'll get efficient time complexity of
        // O(n) for inserting n elements & O(1) for inserting one element otherwise
        // we've to insert all elements one by one in O(nlogn)

        // ! MODIFIED Constructor
        public PriorityQueue(int[] arr) {
            data = new ArrayList<>();
            for (int val : arr) {
                size++;
                data.add(val);
            }
            // now these element inserted above are not following heap order property - to
            // do so, we'll perfrom downheapify from last level (accurately last 2nd level
            // cos last/leaf level doesn't have any children to peform downheapify)
            for (int i = (size() - 1) / 2; i >= 0; i--) {
                downHeapify(i);
            }
        }

        public void add(int val) {
            // O(logn) - to insert an element
            data.add(val); // add at last
            size++;
            upheapify(size() - 1); // upheapify call
        }

        // O(logn) - n for no of nodes in complete binary tree & logn is the height of
        // tree

        public void upheapify(int idx) {
            // no need for base case - as it'll stop at -1/2 == 0 for root node
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
            // O(1)
            if (size() == 0) {
                System.out.println("Underflow!");
                return -1;
            }
            return data.get(0);
        }

        public int size() {
            return size;
        }

        public int remove() {
            // O(logn)
            if (size() == 0) {
                System.out.println("Underflow!");
                return -1;
            }
            int val = peek();
            swap(0, size() - 1);
            // data.remove(size() - 1);
            size--; // remove nhi kia, sirf size ko dec krdia
            downHeapify(0);
            return val;
        }

        public void downHeapify(int idx) {
            int min = idx;
            int leftIdx = 2 * idx + 1;
            int rightIdx = 2 * idx + 2;
            if (leftIdx < size && isSmaller(leftIdx, min))
                min = leftIdx;
            if (rightIdx < size && isSmaller(rightIdx, min))
                min = rightIdx;
            if (min != idx) {
                swap(idx, min);
                downHeapify(min);
            }
        }

        // !HEAP SORT - w/o inplace
        public ArrayList<Integer> heapSort() {
            // removal from heap & adding in arraylist
            ArrayList<Integer> sorted = new ArrayList<>();
            while (size() > 0) {
                int val = remove();
                sorted.add(val);
            }
            return sorted;
        }
        // now after sorting if we call peek/remove - we get underflow

        // ! HEAP SORT - Inplace - we've to perform do some modifications - use size as
        // size property not function - O(1) extra space
        public ArrayList<Integer> heapSortIn() {
            // reverse order m print
            while (size() > 0)
                remove();
            return data;
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
            } else if (str.startsWith("heapSort")) {
                ArrayList<Integer> sorted = qu.heapSort();
                System.out.println(sorted);
            } else if (str.startsWith("heapSortIn")) {
                ArrayList<Integer> sorted = qu.heapSortIn();
                System.out.println(sorted);
            } else if (str.startsWith("insertArr")) {
                int n = Integer.parseInt(str.split(" ")[1]);
                int[] arr = new int[n];
                String[] values = br.readLine().split(" ");
                for (int i = 0; i < n; i++)
                    arr[i] = Integer.parseInt(values[i]);
                qu = new PriorityQueue(arr);
            } else
                System.out.println("Write valid command!");
            str = br.readLine();
        }
    }
}
