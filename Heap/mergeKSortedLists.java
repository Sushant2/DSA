import java.io.*;
import java.util.*;

public class mergeKSortedLists {

    public static class dataPair implements Comparable<dataPair> {
        int data;
        int listIdx; // to know that which arraylist element belongs to
        int dataIdx;// to know index of next element to be inserted from the arraylist

        dataPair(int data, int listIdx, int dataIdx) {
            this.data = data;
            this.listIdx = listIdx;
            this.dataIdx = dataIdx;
        }

        public int compareTo(dataPair dp) {
            // compair pair basis of data - small data - higher priority
            return this.data - dp.data;
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<dataPair> pq = new PriorityQueue<>();

        // insert 0th element of each arraylists
        for (int i = 0; i < lists.size(); i++)
            pq.add(new dataPair(lists.get(i).get(0), i, 0));
        // algo
        while (pq.size() > 0) {
            // pop one pair
            dataPair top = pq.remove();
            res.add(top.data);
            // insert next pair if exists
            if (top.dataIdx + 1 < lists.get(top.listIdx).size()) {
                pq.add(new dataPair(lists.get(top.listIdx).get(top.dataIdx + 1), top.listIdx, top.dataIdx + 1));
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            int n = Integer.parseInt(br.readLine());
            String[] elements = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                list.add(Integer.parseInt(elements[j]));
            }

            lists.add(list);
        }

        ArrayList<Integer> mlist = mergeKSortedLists(lists);
        for (int val : mlist) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
