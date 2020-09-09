package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.stream.IntStream;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);

            if (a[0] + a[1] <= a[n - 1]) {
                out.println("1 2 " + n);
            } else {
                out.println(-1);
            }
        }
    }
}
