import java.util.*;
import java.io.*;

public class constructHashMap {

    // key and value are of generic type
    public static class HashMap<K, V> {
        // ? hashmap ki node
        private class HMNode {
            K key; // K type ki key
            V value; // V type ki value
            HMNode next;

            HMNode() {
            }

            HMNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        // ? hashmap ka Data structure - array of linkedlist
        private LinkedList<HMNode>[] buckets;
        private int size;
        private int capacity;

        public HashMap() {
            capacity = 4;
            size = 0;
            buckets = new LinkedList[capacity];

            for (int i = 0; i < capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        public int getBucketId(K key) {
            // O(1)
            int hashCode = key.hashCode();
            int bucketId = (Math.abs(hashCode)) % capacity;
            return bucketId;
        }

        // to get node index from the linkedlist of that bucket
        public int getDataId(int bucketId, K key) {
            int count = 0;
            for (HMNode node : buckets[bucketId]) {
                // to check - we're also comparing data, rather than comparing references only
                if (node.key.equals(key) == true)
                    // if data exists, return index/count
                    return count;
                count++;
            }
            // if data doesn't exists
            return -1;
        }

        public void setDataId(int bucketId, int dataId, V value) {
            int count = 0;
            for (HMNode node : buckets[bucketId]) {
                if (count == dataId)
                    node.value = value;
                count++;
            }
        }

        // !PUT
        public void put(K key, V value) throws Exception {
            // if key already exist, then update value
            int bucketId = getBucketId(key);
            int dataId = getDataId(bucketId, key);
            if (getDataId(bucketId, key) != -1) {
                // update that node's value
                setDataId(bucketId, dataId, value);
            } else {
                HMNode node = new HMNode(key, value);
                // O(1)
                buckets[bucketId].addLast(node);
                size++;
            }
        }

        // ! GET
        public V get(K key) throws Exception {
            int bucketId = getBucketId(key);
            int dataId = getDataId(bucketId, key);
            if (dataId != -1) {
                int counter = 0;
                for (HMNode node : buckets[bucketId]) {
                    if (counter == dataId)
                        return node.value;
                    counter++;
                }
            }
            return null;
        }

        // ! CONTAINS KEY
        public boolean contains(K key) {
            int bucketId = getBucketId(key);
            int dataId = getDataId(bucketId, key);
            if (dataId == -1)
                return false;
            else
                return true;
        }

        // ! REMOVE
        public V remove(K key) throws Exception {
            int bucketId = getBucketId(key);
            int dataId = getDataId(bucketId, key);
            if (dataId == -1)
                return null;
            else {
                V val = null;
                int count = 0;
                for (HMNode node : buckets[bucketId]) {
                    if (count == dataId) {
                        val = node.value;
                        buckets[bucketId].remove(val);
                    }
                    count++;
                }
                size--;
                return val;
            }
        }

        // ! KEYSET
        public ArrayList<K> keySet() throws Exception {
            ArrayList<K> keys = new ArrayList<>();
            for (int i = 0; i < buckets.length; i++) {
                for (HMNode node : buckets[i])
                    keys.add(node.key);
            }
            return keys;
        }

        // ! SIZE
        public int size() {
            return size;
        }

        // ! DISPLAY
        public void display() {
            System.out.println("Display Begins ");
            for (int bi = 0; bi < buckets.length; bi++) {
                System.out.print("Bucket " + bi + " ");
                for (HMNode node : buckets[bi]) {
                    System.out.print(node.key + "@" + node.value);
                }
                System.out.println(".");
            }
            System.out.println("Display Ends ");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }
}
