import java.util.*;
import java.io.*;

//to make our own code support comparable, we gotta make it generic first
public class CCinOurPQ {
    // khud ki PQ implement
    public static class PriorityQueue<T> {
        ArrayList<T> data;
        Comparator comp;

        public PriorityQueue() {
            data = new ArrayList<>();
            this.comp = null;
        }

        public PriorityQueue(Comparator comp) {
            data = new ArrayList<>();
            this.comp = comp;
        }

        public boolean isSmaller(int i, int j) {
            if (comp == null) {
                // type cast to comparable
                Comparable ith = (Comparable) data.get(i);
                Comparable jth = (Comparable) data.get(j);
                if ((ith.compareTo(jth)) < 0)
                    return true;
                else
                    return false;
            } else {
                // if any comparator
                T ith = data.get(i);
                T jth = data.get(j);
                if (comp.compare(ith, jth) < 0)
                    return true;
                else
                    return false;
            }
        }

        // add
        public void add(T val) {
            data.add(val);
            // to maintain heap order property
            // do upheapify while insertion
            upHeapify(size() - 1);
        }

        public void upHeapify(int idx) {
            if (idx == 0)
                return;
            int parIdx = (idx - 1) / 2;
            if (isSmaller(idx, parIdx)) {
                swap(idx, parIdx);
                upHeapify(parIdx);
            }
        }

        public void swap(int i, int j) {
            T ith = data.get(i);
            T jth = data.get(j);
            data.set(i, jth);
            data.set(j, ith);
        }

        // remove
        public T remove() {
            if (this.size() == 0) {
                System.out.println("Underflow!");
                return null;
            }
            swap(0, size() - 1);
            T val = data.remove(size() - 1);
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
        public T peek() {
            if (size() == 0) {
                System.out.println("Underflow!");
                return null;
            }
            return data.get(0);
        }

        public int size() {
            return data.size();
        }
    }

    // our Student class
    static class Student implements Comparable<Student> {
        int rno;
        int ht;
        int wt;

        Student(int rno, int ht, int wt) {
            this.rno = rno;
            this.ht = ht;
            this.wt = wt;
        }

        public int compareTo(Student other) {
            return this.rno - other.rno;
        }

        public String toString() {
            return "rno= " + this.rno + ", ht= " + this.ht + ", wt= " + this.wt;
        }
    }

    static class StudentHTComparator implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
            return s1.ht - s2.ht;
        }
    }

    static class StudentWTComparator implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
            return s1.wt - s2.wt;
        }
    }

    public static void main(String[] args) throws Exception {
        // PriorityQueue<Student> pq = new PriorityQueue<>();
        PriorityQueue<Student> pq = new PriorityQueue<>(new StudentHTComparator());
        // PriorityQueue<Student> pq = new PriorityQueue<>(new StudentWTComparator());
        pq.add(new Student(1, 180, 70));
        pq.add(new Student(3, 157, 60));
        pq.add(new Student(5, 170, 90));
        pq.add(new Student(12, 145, 88));
        pq.add(new Student(16, 134, 75));

        while (pq.size() > 0)
            System.out.println(pq.remove() + " ");
    }
}
