import java.util.LinkedList;

/**
 * Created by Brad on 11/9/2015.
 */
public class HashTable<T> {

    /* Hash table variables */
    private LinkedList<T>[] hashTable;
    private int tableSize;

    /**
     * Creates an open hashing hash table using linked lists
     * @param size -> pass in a prime number for best distribution and performance.
     *             Use Prime.NextPrimeOver(int n) to get your prime #.
     */
    public HashTable(int size) {
        tableSize = size;
        hashTable = new LinkedList[size];
    }

    public void insert(T element) {

        int hashIndex = (int) hash(element);

        if (hashTable[hashIndex] == null)
            hashTable[hashIndex] = new LinkedList<T>();

        hashTable[hashIndex].add(element);
    }

    private long hash(T element) {

        long a = 183271, b = 83759;  // Random #'s
        long p = Prime.NextPrimeOver(4000000);  // Prime # over largest number in world (in this case 4,000,000 per instructions)

        return ((((a * (Integer) element) * b) % p) % tableSize);
    }

    public T lookUp(T element) {

        int hashIndex = (int) hash(element);

        if (hashTable[hashIndex] == null)
            return null;

        LinkedList<T> list = hashTable[hashIndex];

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == element)
                return element;
        }

        return null;
    }
}
