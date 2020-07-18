package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long n = in.nextLong();
            long r = in.nextLong();
            long m = Math.min(n - 1, r);
            long result = (1 + m) * m / 2;
            if (r >= n) {
                result++;
            }
            out.println(result);
        }
    }
}
