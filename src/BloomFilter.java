import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;

/**
 * Created by Brad on 11/11/2015.
 */
public class BloomFilter {

    int m, k;
    BitSet bloomFilter;

    public BloomFilter(int size, int k) {
        m = size;
        this.k = k;
        bloomFilter = new BitSet(size);
    }

    public void hash(int key) {

        switch (k) {
            case 3:
                bloomFilter.set((int) hash3(key));
            case 2:
                bloomFilter.set(hash2(key));
            case 1:
                bloomFilter.set((int) hash1(key));
        }
    }

    public boolean exists(int key) {

        switch (k) {
            case 3:
                if (bloomFilter.get((int) hash3(key)))
                    return true;
            case 2:
                if (bloomFilter.get(hash2(key)))
                    return true;
            case 1:
                if (bloomFilter.get((int) hash1(key)))
                    return true;
        }

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

    private double getOcupancy() {

        int full = 0;

        for (int i = 0; i < m; i++) {
            if (bloomFilter.get(i))
                full++;
        }

        return (double)full / (double)m;
    }

    public void printResults(int n) {

        BufferedWriter writer = getWriteStreamForFile("bloom-results.txt", true);

        if (writer != null) {
            try {

                writer.write(
                        "M: " + m
                        + "\nK: " + k
                        + "\nN: " + n
                        + "\n% Occupancy: " + getOcupancy()
                        + "\n\n"
                );

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printResults(int n, int noLookupCount) {

        BufferedWriter writer = getWriteStreamForFile("part3-results.txt", true);

        if (writer != null) {
            try {
                writer.write(
                        "M: " + m
                        + "\nK: " + k
                        + "\nSaved Lookups %: " + noLookupCount / 3000.0 // 3,000 because we're doing 3,000 lookups
                        + "\n\n"
                );

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedWriter getWriteStreamForFile(String fileName, boolean append) {

        try {

            File file = new File (fileName);
            return new BufferedWriter(new FileWriter(file, append));

        } catch (IOException e) {

            System.out.print("\nError opening file\n");
            e.printStackTrace();
        }

        return null;
    }
}
