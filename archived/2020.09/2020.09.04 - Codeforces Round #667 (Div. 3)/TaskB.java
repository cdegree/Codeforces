package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long a = in.nextLong();
            long b = in.nextLong();
            long x = in.nextLong();
            long y = in.nextLong();
            long n = in.nextLong();
            long diffA = a - x;
            long diffB = b - y;
            long best = 1L << 61;
            if (diffA > n) {
                best = Math.min(best, (a - n) * b);
            } else {
                long left = n - diffA;
                if (left < diffB) {
                    best = Math.min(best, x * (b - left));
                } else {
                    best = x * y;
                }
            }
            if (diffB > n) {
                best = Math.min(best, (b - n) * a);
            } else {
                long left = n - diffB;
                if (left < diffA) {
                    best = Math.min(best, y * (a - left));
                } else {
                    best = Math.min(best, x * y);
                }
            }
            out.println(best);
        }
    }
}
