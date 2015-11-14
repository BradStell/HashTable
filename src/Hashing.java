import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.LinkedList;

/**
 * Created by Brad on 11/9/2015.
 *
 * Entry point for Hashing Assignment #4
 */
public class Hashing {

    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.print(
              "\nIncorrect usage of the program.\n" +
              "Correct usage:\n" +
              "\tHashing <file> <number of elements to process> <-s size of hash table **OR** -l load factor>\n\n" +
              "Example:\n" +
              "\tHashing nums.txt 1000 100\n"
            );
        } else {

            /* Begin program execution here */
            String file = args[0];
            int sizeToProcess = Integer.parseInt(args[1]);
            int tableSize = 113;
            String flag = args[2];

            switch (flag) {

                case "-s":
                    try {

                        tableSize = Integer.parseInt(args[3]);

                        if (tableSize < 1) {
                            System.out.print("\nHash table size cannot be negative\n");
                            System.exit(-2);
                        }

                    } catch (NumberFormatException e) {
                        System.out.print("\nHash table size cannot be a decimal number\n");
                        System.exit(-1);
                    }
                    break;
                case "-l":

                    if (Double.parseDouble(args[3]) <= 0) {
                        System.out.print("\nLoad factor must be greater than 0\n");
                        System.exit(-3);
                    }

                    tableSize = (int) (sizeToProcess / Double.parseDouble(args[3]));
            }

            // Turn Specified table size into the next prime number over that size.
            // For best performance (unless it is already a prime number).
            if (!Prime.IsPrimeNumber(tableSize))
                tableSize = Prime.NextPrimeOver(tableSize);

            System.out.print("\nHash Table Size: " + tableSize + "\n");

            BufferedReader bufferedReader = null;
            HashTable<Integer> hashTable = new HashTable<>(tableSize);

            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            double finishTime = 0;
            if (bufferedReader != null) {

                String line;

                try {

                    double startTime = System.currentTimeMillis();
                    for (int i = 0; i < sizeToProcess; i++) {

                        if ((line = bufferedReader.readLine()) != null) {

                            int num = Integer.parseInt(line);
                            hashTable.insert(num);
                        }
                    }
                    finishTime = System.currentTimeMillis() - startTime;

                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            hashTable.printHashTableResults(finishTime, sizeToProcess, Double.parseDouble(args[3]));
        }
    }
}
