import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by Brad on 11/11/2015.
 */
public class GenRandomNumbers {

    public static void main(String[] args) throws FileNotFoundException {

        Random random = new Random();
        PrintWriter writer = new PrintWriter("lookup.txt");

        for (int i = 0; i < 3000; i++) {
            writer.println( random.nextInt(Integer.MAX_VALUE) % 7919);
        }

        writer.close();
    }
}
