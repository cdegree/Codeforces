package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.Vector;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        int N = 200005;
        int[] isPrime = new int[N];
        for (int i = 2; i < N; ++i) {
            if (isPrime[i] == 0) {
                for (int j = i; j < N; j += i) {
                    isPrime[j]++;
                }
            }
        }
        int cnt = 0;
        Vector<Integer> nearlyPrimes = new Vector<>();
        for (int i = 2; i < 300; ++i) {
            TreeMap<Integer, Integer> primes = MathUtil.getPrimeDivisors(i);
            if (primes.size() == 2 && primes.firstEntry().getValue() == 1 && primes.lastEntry().getValue() == 1) {
                nearlyPrimes.add(i);
            }
        }
        while (T-- > 0) {
            int n = in.nextInt();
            if (n <= 30) {
                out.println("NO");
            } else {
                boolean found = false;
                int a = 0;
                int b = 0;
                int c = 0;
                int d = 0;
                for (int i = 0; i < nearlyPrimes.size() && !found; ++i) {
                    for (int j = i + 1; j < nearlyPrimes.size() && !found; ++j) {
                        for (int k = j + 1; k < nearlyPrimes.size() && !found; ++k) {
                            d = n - nearlyPrimes.get(i) - nearlyPrimes.get(j) - nearlyPrimes.get(k);
                            if (d != nearlyPrimes.get(i) && d != nearlyPrimes.get(j) && d != nearlyPrimes.get(k)) {
                                a = nearlyPrimes.get(i);
                                b = nearlyPrimes.get(j);
                                c = nearlyPrimes.get(k);
                                d = n - nearlyPrimes.get(i) - nearlyPrimes.get(j) - nearlyPrimes.get(k);
                                found = true;
                            }
                        }
                    }
                }
                if (found) {
                    out.println("YES");
                    out.println(String.format("%d %d %d %d", a, b, c, d));
                } else {
                    out.println("NO");
                }
            }
        }
    }
}
