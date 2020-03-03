package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        if (a == b) {
            out.println(0);
            return;
        }
        if (a > b) {
            long t = a;
            a = b;
            b = t;
        }
        long diff = b - a;
        Vector<Long> divisors = MathUtil.getDivisors(diff);
        long res = 1L<<61;
        long minLCM = 1L<<61;
        for (long d : divisors) {
            long k = (d - a % d) % d;
            long lcm = (a + k) * (b + k) / d;
            if (lcm < minLCM) {
                minLCM = lcm;
                res = k;
            } else if (lcm == minLCM) {
                if (res > k) {
                    res = k;
                }
            }
        }
        out.println(res);
    }
}
