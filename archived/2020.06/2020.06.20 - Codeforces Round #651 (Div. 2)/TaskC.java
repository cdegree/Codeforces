package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    final int N = 1000006;
    int[] cache = new int[N];

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();

        while (tt-- > 0) {
            long n = in.nextInt();
            String res = Judge(n) ? "Ashishgup" : "FastestFinger";
            out.println(res);
        }
    }

    boolean Judge(long x) {
        if (x == 1) {
            return false;
        } else if (x % 2 == 1 || x == 2) {
            return true;
        } else {
            int q = (int) x;
            if (x < N && cache[q] != 0) {
                return cache[q] == 1 ? true : false;
            }
            for (long i = 3; i * i <= x; i++) {
                if (x % i == 0) {
                    long v = x / i;
                    if (i % 2 == 1 && !Judge(v)) {
                        if (q < N) {
                            cache[q] = 1;
                        }
                        return true;
                    }
                    if (v % 2 == 1 && !Judge(i)) {
                        if (q < N) {
                            cache[q] = 1;
                        }
                        return true;
                    }
                }
            }
            if (q < N) {
                cache[q] = 2;
            }
            return false;
        }
    }
}
