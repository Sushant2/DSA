
//! Comparable and Comparator in Java's PQ
import java.util.*;
import java.io.*;

public class CCinJavaPQ {

    // class student
    // class student implements comparable interface(which have compareTo method)
    static class Student implements Comparable<Student> {
        int rno;
        int ht;
        int wt;

        Student(int rno, int ht, int wt) {
            this.rno = rno;
            this.ht = ht;
            this.wt = wt;
        }

        // sorted acc to roll number - in ascending order
        // public int compareTo(Student other) {
        // return this.rno - other.rno;
        // }

        // sorted acc to roll number - in descending order
        public int compareTo(Student other) {
            return other.rno - this.rno;
        }

        // for printing student objects make tostring function
        public String toString() {
            return "rno= " + this.rno + ", ht= " + this.ht + ", wt= " + this.wt;
        }
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue<Student> pq = new PriorityQueue<>();
        pq.add(new Student(1, 180, 70));
        pq.add(new Student(3, 157, 60));
        pq.add(new Student(5, 170, 90));
        pq.add(new Student(12, 145, 88));
        pq.add(new Student(16, 134, 75));

        while (!pq.isEmpty())
            System.out.println(pq.remove() + " ");
    }
}