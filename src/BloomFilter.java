import java.util.BitSet;

/**
 * Created by Brad on 11/11/2015.
 */
public class BloomFilter {

    int m;
    BitSet bloomFilter;

    public BloomFilter(int size) {
        m = size;
        bloomFilter = new BitSet(size);
    }

    public void hash(int key) {

        bloomFilter.set((int) hash1(key));
        bloomFilter.set(hash2(key));
        bloomFilter.set((int) hash3(key));
    }

    public boolean exists(int key) {

        if (bloomFilter.get((int) hash1(key)))
            return true;
        else if (bloomFilter.get(hash2(key)))
            return true;
        else if (bloomFilter.get((int) hash3(key)))
            return true;
        else
            return false;
    }

    private long hash1(int key) {

        long a = 1801, b = 6277;  // Random #'s
        long p = Prime.NextPrimeOver(33000000);  // Prime # over largest number in world (in this case 10,000,000)

        return ((((a * key) * b) % p) % m);
    }

    private int hash2(int key) {

        double a = Math.PI;
        int floorOfFirstStage = (int) ((a * key));
        double firstStage = (a * key) - floorOfFirstStage;

        return (int) (firstStage * m);
    }

    private long hash3(int key) {

        long a = 6827, b = 977;  // Random #'s
        long p = Prime.NextPrimeOver(33000000);  // Prime # over largest number in world (in this case 10,000,000)

        return ((((a * key) * b) % p) % m);
    }
}
