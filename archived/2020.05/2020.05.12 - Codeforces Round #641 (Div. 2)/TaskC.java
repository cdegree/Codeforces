package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.TreeSet;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        long res = 1;
        TreeSet<Integer> primes = new TreeSet<>();
        TreeMap<Integer, Integer>[] primeDivs = new TreeMap[n + 1];
        for (int i = 0; i < n; ++i) {
            primeDivs[i] = MathUtil.getPrimeDivisors(a[i]);
        }

        primes.addAll(primeDivs[0].keySet());
        primes.addAll(primeDivs[1].keySet());
        int N = 1000000;
        for (int prime : primes) {
            int min = N;
            int sec_min = N;
            for (int i = 0; i < n; ++i) {
                int cnt = 0;
                if (primeDivs[i].containsKey(prime)) {
                    cnt = primeDivs[i].get(prime);
                }
                //System.out.println(a[i]+"  = "+prime+"^"+cnt);
                if (cnt < min) {
                    sec_min = min;
                    min = cnt;
                } else if (cnt < sec_min) {
                    sec_min = cnt;
                }
            }
            //System.out.println("prime = "+prime +" sec_min = "+sec_min);
            //System.out.println("min = "+min +" sec_min = "+sec_min);
            for (int i = 0; i < sec_min; ++i) {
                res *= prime;
            }
        }
        out.println(res);
    }

    int lcm(int a, int b) {
        return a / MathUtil.getGCD(a, b) * b;
    }
}
