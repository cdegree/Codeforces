package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        long numerator = n;
        long denominator = 1;
        long mod = 1000000007;
        long res = 0;
        for (int i = 0; i <= n; ++i) {
            long sum = i * a + (n - i) * b;
            if (containsOnlyAB(sum, a, b)) {
                //out.println(String.format("%d %d %d", i, (n - i), sum));
                if (i == 0 || i == n) {
                    res++;
                } else {
                    res += numerator * MathUtil.getInv(denominator, mod) % mod;
                    res %= mod;
                }
            }
            if (i > 0) {
                numerator = numerator * (n - i) % mod;
                denominator = denominator * (i + 1) % mod;
            }
        }
        res %= mod;
        out.println(res);
    }

    boolean containsOnlyAB(long x, int a, int b) {
        while (x > 0) {
            long r = x % 10;
            if (r != a && r != b) {
                return false;
            }
            x /= 10;
        }
        return true;
    }
}
