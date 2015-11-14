/**
 * Created by Brad on 11/10/2015.
 */
public class Prime {

    public static int NextPrimeOver(int n) {

        if (n % 2 == 0)
            n++;

        boolean isPrime = true;

        for (int i = n; i < n * 2; i+=2) {
            isPrime = true;
            for (int j = 3; j < Math.sqrt(i) && isPrime; j++) {
                if (i % j == 0)
                    isPrime = false;
            }
            if (isPrime)
                return i;
        }

        return -1;
    }

    public static boolean IsPrimeNumber(int n) {

        if (n % 2 == 0)
            return false;

        for (int i = 3; i < Math.sqrt(n); i+=2) {

            if (n % i == 0)
                return false;
        }

        return true;
    }
}
