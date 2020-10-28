public class PrimeNumberUtils {

    /**
     * Returns a prime large than an number.
     * @param number
     *  Number to get prime bigger than.
     * @return A prime bigger than the input number.
     */
    public static long getPrimeLargerThan(int number) {
        long prime = number;
        if (prime <= 1) {
            return 2;
        }

        boolean foundPrime = false;

        while(!foundPrime) {
            prime++;
            foundPrime = isPrime(prime);
        }
        return prime;
    }

    /**
     * Checks if a number is prime.
     * @param number Number to check.
     * @return True if the number is prime.
     */
    private static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }

        if (number <= 3) {
            return true;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i*i <= number; i = i + 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets a random prime bigger than some number.
     * @param number Number to get a prime bigger than.
     * @param range Range of primes.
     * @return A random prime bigger than number.
     */
    public static long getRandPrimeLargerThan(int number, int range) {

       long [] randomPrimes = new long[range];
       randomPrimes[0]=getPrimeLargerThan(number);

      for (int i =1 ;i<randomPrimes.length;i++){
          randomPrimes[i]= getPrimeLargerThan((int)randomPrimes[i-1]);
          //System.out.println(randomPrimes[i]);
      }

      return randomPrimes[(int) Math.floor(Math.random() * randomPrimes.length)];
    }








}
