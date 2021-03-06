import java.io.*;
import java.lang.reflect.Array;
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

        try {
            hashTable = new LinkedList[size];
        } catch (OutOfMemoryError e) {
            System.out.print("\nHash table size too large for memory\n");
            System.exit(-5);
        }
    }

    public void insert(T element) {

        int hashIndex = (int) hash(element);

        if (hashTable[hashIndex] == null)
            hashTable[hashIndex] = new LinkedList<T>();

        hashTable[hashIndex].add(element);
    }

    private long hash(T element) {

        // SEAMS TO BE THE BEST ONE WITH 10,000 ELEMENTS, LOAD FACTER OF .75 -> 36,000 EMPTY SLOTS AND 7 MAX LIST SIZE
        long a = 3989  , b = 5021   ;  // Random #'s
        long p = Prime.NextPrimeOver(10000000);  // Prime # over largest number in world (in this case 10,000,000)

        return ((((a * (Integer) element) * b) % p) % tableSize);
    }

    public T lookUp(T element) {

        int hashIndex = (int) hash(element);

        if (hashTable[hashIndex] == null)
            return null;

        LinkedList<T> list = hashTable[hashIndex];

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            if (item.equals(element))
                return element;
        }

        return null;
    }

    public void printHashTableResults(double buildTime, int n, double loadFacter) {

        int numNull = 0;
        int longestList = 0;
        File file = new File("LF-results.txt");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file, true));

            for (int i = 0; i < tableSize; i++) {
                if (hashTable[i] != null) {
                    LinkedList<T> list = hashTable[i];

                    if (list.size() > longestList)
                        longestList = list.size();
                }
                else {
                    numNull++;
                }
            }

            writer.write(
                    "Load Factor: " + loadFacter
                    + "\nTable Size: " + n
                    + "\nPercent Empty: " + (double)numNull / (double)tableSize
                    + "\nLongest List: " + longestList
                    + "\nBuild Time: " + buildTime
                    + "\n\n"
            );

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}