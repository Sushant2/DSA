import java.util.*;
import java.io.*;

public class constructHashMap {

    // keys & values are of generic type
    public static class HashMap<K, V> {
        // hashmap banaenge arrays of linkedlist having node HMNode
        private class HMNode {
            K key;
            V value;

            HMNode() {
            }

            // constructor of HMNode
            HMNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        // data members
        private LinkedList<HMNode>[] buckets;
        private int noOfNodes; // size
        private int noOfBuckets; // capacity
        private double loadingFactor; // rehashing

        // constructor of hashmap
        public HashMap() {
            noOfBuckets = 4;
            noOfNodes = 0;
            loadingFactor = 0.0;
            init();
        }

        // initlise every bucket with empty linkedlist
        public void init() {
            buckets = new LinkedList[noOfBuckets];
            for (int i = 0; i < noOfBuckets; i++)
                buckets[i] = new LinkedList<>();
        }

        // !GET BUCKET ID
        public int getBucketId(K key) throws Exception {
            // O(1)
            int hashCode = key.hashCode();
            int bucketId = (Math.abs(hashCode)) % noOfBuckets;
            return bucketId;
        }

        // ! GET DATA - that node from linkedlist from that bucket
        private HMNode getData(int bucketId, K key) throws Exception {
            for (HMNode node : buckets[bucketId]) {
                if (node.key.equals(key) == true)
                    return node;// if data exists return that node
            }
            // if data doesnot exists return null
            return null;
        }

        // ! PUT - insertion + updation with rehashing
        public void put(K key, V value) throws Exception {
            // O(1)
            int bucketId = getBucketId(key);
            HMNode data = getData(bucketId, key);
            if (data == null) {
                // insertion
                double newLoadingFactor = (noOfNodes + 1.0) / (noOfBuckets);
                if (newLoadingFactor > 2.0) {
                    // rehashing
                    LinkedList<HMNode>[] oldBuckets = buckets;
                    noOfBuckets = 2 * noOfBuckets;
                    init(); // initialse empty linkedlist in new array of increased size

                    // now refill the new array with nodes of old array
                    for (int i = 0; i < oldBuckets.length; i++) {
                        for (HMNode node : oldBuckets[i]) {
                            int newBucketId = getBucketId(node.key);
                            buckets[newBucketId].addLast(node);
                        }
                    }
                }
                // add new node
                int newBucketId = getBucketId(key);
                HMNode node = new HMNode(key, value);
                buckets[newBucketId].addLast(node);
                noOfNodes++;
                loadingFactor = (noOfNodes * 1.0) / (noOfBuckets);
            } else {
                // updation
                data.value = value;
            }
        }

        // ! GET
        public V get(K key) throws Exception {
            int bucketId = getBucketId(key);
            HMNode data = getData(bucketId, key);
            if (data != null)
                return data.value;
            else
                return null;
        }

        // ! CONTAINS KEY
        public boolean containsKey(K key) throws Exception {
            int bucketId = getBucketId(key);
            HMNode data = getData(bucketId, key);
            if (data != null)
                return true;
            return false;
        }

        // !REMOVE
        public V remove(K key) throws Exception {
            int bucketId = getBucketId(key);
            HMNode data = getData(bucketId, key);
            if (data == null)
                return null;
            V value = data.value;
            buckets[bucketId].remove(data);
            noOfNodes--;
            // updated loading factor after removal
            loadingFactor = (noOfNodes * 1.0) / noOfBuckets;
            return value;

        }

        // ! KEYSET
        public ArrayList<K> keySet() throws Exception {
            ArrayList<K> keys = new ArrayList<>();
            for (int i = 0; i < noOfBuckets; i++) {
                for (HMNode node : buckets[i])
                    keys.add(node.key);
            }
            return keys;
        }

        public int size() throws Exception {
            return noOfNodes;
        }

        public void display() throws Exception {
            System.out.println("Display Begins");
            for (int i = 0; i < buckets.length; i++) {
                System.out.print("Buckets" + i + " ");
                for (HMNode node : buckets[i])
                    System.out.print(node.key + "@" + node.value + " ");
                System.out.println(".");
            }
            System.out.println("Display Ends");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // hashmap
        HashMap<String, Integer> map = new HashMap();
        String str = br.readLine();
        while (!str.equals("quit")) {
            if (str.startsWith("put")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                Integer val = Integer.parseInt(parts[2]);
                map.put(key, val);
            } else if (str.startsWith("get")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.get(key));
            } else if (str.startsWith("containsKey")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.containsKey(key));
            } else if (str.startsWith("remove")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.remove(key));
            } else if (str.startsWith("size"))
                System.out.println(map.size());
            else if (str.startsWith("keySet"))
                System.out.println(map.keySet());
            else if (str.startsWith("display"))
                map.display();
            else
                System.out.println("Enter valid command!");
            str = br.readLine();
        }
    }
}
