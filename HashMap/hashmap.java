import java.io.*;
import java.util.*;

//hashmap - kind of data structure - It provides the basic implementation of the Map interface of Java
//hashmap is known as hashmap as it uses a technique called hashing
//it stores KEY : VALUE Pair
//keys will always be unique in hashmap
//Initial Capacity -2^2=16, it can hold 16 key:value pairs initially
//hashmap doesn't allow duplicated keys, but allows duplicate values
//hashmap allow null key but only once & nultiple null values
//Order of hashmap will not ramain constant - input order may or may not be same, random order

public class hashmap {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();

        // hokages & their rank
        // PUT - insert
        hm.put("Naruto", 7);
        hm.put("Kakashi", 6);
        hm.put("Sunade", 5);
        hm.put("Hashirama", 1);

        // GET - display
        System.out.println(hm.get("Naruto"));
        System.out.println(hm.get("Kakashi"));
        System.out.println(hm.get("Hashirama"));
        System.out.println(hm.get("Madara"));

        // PUT - update
        hm.put("Naruto", 6);

        System.out.println(hm.get("Naruto"));

        // REMOVE - remove & get
        int rank = hm.remove("Hashirama");

        System.out.println(rank);
        System.out.println(hm.get("Hashirama"));

        // Print whole hasmap at once
        System.out.println(hm);

        // adding key as null & values
        hm.put(null, null);
        System.out.println(hm.get(null));

        hm.put(null, 10);
        System.out.println(hm.get(null));

        System.out.println(hm);

        // Contains Key
        if (hm.containsKey("Jiraiya"))
            System.out.println(hm.get("Jiraiya"));
        else
            System.out.println("OOPS!");

        if (hm.containsKey("Naruto"))
            System.out.println(hm.get("Naruto"));
        else
            System.out.println("OOPS!");

        // KEY SET - to get only the keys - it returns a set of keys
        Set<String> keys = hm.keySet();
        for (String x : keys)
            System.out.println(x);
    }
}
