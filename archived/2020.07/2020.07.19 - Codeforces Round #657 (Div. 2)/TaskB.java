package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long l = in.nextLong();
            long r = in.nextLong();
            long m = in.nextLong();
            long min = m - r + l;
            long max = m - l + r;
            for (long a = l; a <= r; ++a) {
                long minN = (min + a - 1) / a;
                long maxN = max / a;

                if (maxN > 0 && minN <= maxN) {
                    long n = maxN;
                    long diff = n * a - m;
                    for (long b = l; b <= r; ++b) {
                        long c = b + diff;
                        if (c >= l && c <= r) {
                            out.println(a + " " + b + " " + c);
                            //System.out.println(n * a + b - c);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}
