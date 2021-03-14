package design.datastructure_design;

/*
* Separate Chaining Technique,
* Please note there are other open addressing techniques like double hashing and
* linear probing whose efficiency is almost same as to that of separate chaining and you can read more about them at
* (https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/)
*
* Initialise HashMap
* Get
* Put
* Remove key  (1)
* containsKey (1)
* (Rehashing) => when current load > load factor
* entries per bucket = n/m where n is the total no elements across buckets, m is the no of buckets.
*
* 2 approaches to collision resolutions
* 1. Separate Chaining
* 2. Open Addressing (https://www.geeksforgeeks.org/hashing-set-3-open-addressing/)
*
* */
public class MyHashMap<K, V> {
    private static int SIZE;
    Entry<K, V>[] entries = new Entry[SIZE];
    float loadFactor;
    int numBuckets;
    int size;

    public MyHashMap() {
        loadFactor = 0.75f;
        SIZE = 16;
    }

    //Identify the bucket and read
    public V get(K key) {
        int pos = getKeyPosition(key);
        Entry<K, V>head = entries[pos];
        Entry<K, V> cur = head;
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int pos = getKeyPosition(key);

        Entry<K, V> entry = new Entry<>(key, value);
        Entry<K, V> head = entries[pos];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        //update head
        entry.next = head;
        entries[0] = entry;
        size++;

        float sizePerBucket = (float) (size / numBuckets);

        if (sizePerBucket > 0.75) {
            rehash();
        }
    }

    private void rehash() {

        System.out.println("*******************Rehashing started**************");
        Entry<K, V>[] temp = entries;
        entries = new Entry[2 * SIZE];

        SIZE = 2 * SIZE;
        size = 0;

        for (int i = 0; i < temp.length; i++) {

            Entry<K,V> head = entries[i];

            while (head != null) {
                K key = head.key;
                V val = head.value;
                put(key, val);
                head = head.next;
            }
        }
        System.out.println("*******************Rehashing ended**************");

    }

    public boolean containsKey(K key) {
        int pos = getKeyPosition(key);
        Entry<K, V> head = entries[pos];

        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }

        return false;
    }

    public V remove(K key) {
        int pos = getKeyPosition(key);
        Entry<K, V> head = entries[pos];
        Entry<K, V> prev = null;

        if (head == null) {
            return null;
        }
        while (head.next != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (prev != null)
            prev.next = head.next;
        else
            entries[pos] = head.next;  //first entry is the target

        size --;

        return head.value;
    }

    private int getKeyPosition(K key) {
        int code = key.hashCode();
        return code % SIZE;
    }
}

class Entry<K, V> {
    Entry<K, V> next;
    K key;
    V value;

    public Entry(K key, V value) {

    }
}