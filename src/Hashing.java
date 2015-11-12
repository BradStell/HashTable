import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                    tableSize = Integer.parseInt(args[3]);
                    break;
                case "-l":
                    tableSize = (int) (sizeToProcess / Double.parseDouble(args[3]));
            }

            // Turn Specified table size into the next prime number over that size.
            // For best performance
            tableSize = Prime.NextPrimeOver(tableSize);

            BufferedReader bufferedReader = null;
            HashTable<Integer> hashTable = new HashTable<Integer>(tableSize);

            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (bufferedReader != null) {
                String line;

                try {

                    for (int i = 0; i < sizeToProcess; i++) {

                        if ((line = bufferedReader.readLine()) != null) {

                            int num = Integer.parseInt(line);
                            hashTable.insert(num);
                        }
                    }

                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
