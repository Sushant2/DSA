import java.io.*;
import java.util.*;

public class Dynamic_Queue {
    public static class dynamicQueue {
        int[] data;
        int front;
        int rear;
        int size = 0;

        public dynamicQueue(int cap) {
            data = new int[cap];
            front = 0;
            rear = 0;
            size = 0;
        }

        int size() {
            return size;
        }

        void display() {
            int counter = size();
            int idx = front;
            while (counter-- > 0) {
                System.out.print(data[idx] + " ");
                idx = (idx + 1) % data.length;
            }
            System.out.println();
        }

        void add(int val) {
            if (size() == data.length) {
                int[] temp = new int[2 * size()];
                int counter = size();
                int idx = front;
                int j = 0;
                while (counter-- > 0) {
                    temp[j] = data[idx];
                    j++;
                    idx = (idx + 1) % data.length;
                }
                front = 0;
                rear = size();
                data = temp;
            }
            data[rear] = val;
            size++;
            rear = (rear + 1) % data.length;
        }

        int remove() {
            if (size() == 0) {
                System.out.println("Queue underflow!");
                return -1;
            }
            int val = data[front];
            size--;
            front = (front + 1) % data.length;
            return val;
        }

        int peek() {
            if (size() == 0) {
                System.out.println("Queue underflow!");
                return -1;
            }
            return data[front];
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dynamicQueue qu = new dynamicQueue(n);

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
            } else if (str.startsWith("display")) {
                qu.display();
            } else {
                System.out.println("Enter valid command!");
            }
            str = br.readLine();
        }
    }
}
