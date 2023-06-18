import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        br.close();

        long res = n < 3 ? 1 : isPrime(n) ? n - 1 : eulerPhi(n);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(res));
        bw.close();
    }

    private static boolean isPrime(long n) {
        for (int k = 2; k <= Math.sqrt(n); k++) {
            if (n % k == 0) return false;
        }
        return true;
    }

    private static long eulerPhi(long n) {
        long res = n;
        List<Long> primes = new ArrayList<>();

        for (long k = 2; ; k++) {
            if (n % k == 0 && isPrime(k)) {
                primes.add(k);

                while (n % k == 0) n /= k;

                if (isPrime(n)) break;
            }
        }
        if (n > 1) primes.add(n);

        for (long p : primes) {
            res /= p;
            res *= p - 1;
        }
        return res;
    }
}