import java.io.*;

/**
 * Created by Brad on 11/14/2015.
 */
public class BloomFilterMain {

    public static void main (String[] args) {

        if (args.length != 4) {
            System.out.print(
                    "\nIncorrect usage of program."
                    + "\nUse program like follows:"
                    + "\n\n\tBloomFilterMain <file> <# to read from file> "
                    + "<m> <k>"
                    + "\n\n\t* Where m -> size of bloom filter"
                    + "\n\t\t\tk -> # of hash functions to run {1, 2, 3}"
                    + "\n\nExample:"
                    + "\n\tUse 1,000 elements from nums.txt with bloom filter size"
                    + "\n\tof 2,000 and 3 hash functions"
                    + "\n\t\tBloomFilterMain nums.txt 1000 2000 3"
                    + "\n"
            );
        }

        /**
         * If instantiated correctly program starts here.
         */
        else {

            int n = 0, m = 0, k = 0;
            BloomFilter bloomFilter = null;
            BufferedReader reader = null;
            //int[] part3Array = new int[4000000];

            try {
                File file = new File(args[0]);
                n = Integer.parseInt(args[1]);
                m = Integer.parseInt(args[2]);
                k = Integer.parseInt(args[3]);
                reader = new BufferedReader(new FileReader(file));
                bloomFilter = new BloomFilter(m, k);
                String line;

                //File file2 = new File("lookup.txt");
                //BufferedReader reader2 = new BufferedReader(new FileReader(file2));


                for (int i = 0; i < n; i++) {
                    if ((line = reader.readLine()) != null) {
                        int num = Integer.parseInt(line);
                        bloomFilter.hash(num);
                        //part3Array[i] = num;
                    }
                }

                /*int noLookupCount = 0;
                for (int i = 0; i < 3000; i++) {
                    if ((line = reader2.readLine()) != null) {
                        if (!bloomFilter.exists(Integer.parseInt(line))) {
                            noLookupCount++;
                        }
                    }
                }*/
                reader.close();

                // Prints results to Problem B
                bloomFilter.printResults(n);

                // Prints results to Number 3
                //bloomFilter.printResults(n, noLookupCount);

            } catch (NumberFormatException e) {
                System.out.print("Invalid use of parameters.\n");
                System.exit(-1);
            } catch (FileNotFoundException e) {
                System.out.print("File Not Found\n");
                System.exit(-2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
