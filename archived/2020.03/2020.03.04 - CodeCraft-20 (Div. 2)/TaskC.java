package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.TreeMap;

import static algorithms.MathUtil.getPrimeDivisors;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(m);
        for (int i = 0; i < n; ++i) {
            a[i] = a[i] % p;
        }
        for (int i = 0; i < m; ++i) {
            b[i] = b[i] % p;
        }
        long start = System.currentTimeMillis();
        Random r = new Random(start);
        //ystem.out.println(start);

        if (p >= 2) {
            int idxI = 0;

            boolean found = false;
            int idxJ = 0;
            for (int i = 0; i < n; ++i) {
                //TreeMap<Long, Integer> primes = MathUtil.getPrimeDivisors(a[i]);
                if (a[i] % p != 0) {
                    idxI = i;
                    for (int j = 0; j < m; ++j) {
                        //TreeMap<Long, Integer> primes = MathUtil.getPrimeDivisors(b[j]);
                        if (b[j] % p != 0) {
                            idxJ = j;
                            int c = idxI + idxJ;
                            long sum = 0;
                            for (int k = 0; k <= Math.min(n - 1, c); ++k) {
                                if (c - k >= 0 && c - k < m) {
                                    sum += a[k] * b[c - k] % p;
                                }
                            }
                            if (sum % p != 0) {
                                out.println(c);
                                return;
                            }
                        }
                    }
                }
            }
        }


        //System.out.println(System.currentTimeMillis());
    }
}
