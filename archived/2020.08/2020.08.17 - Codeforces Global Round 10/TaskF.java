package current;

import fastio.InputReader;

import java.io.PrintWriter;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] h = in.nextLongArray(n);
        long S = 0;
        long base = h[0];
        for (int i = 0; i < n; ++i) {
            S += h[i] - base - i;
        }
        long remain = S % n;
        long cnt = S / n;
        for (int i = 0; i < n; ++i) {
            h[i] = base + i + cnt + ((i < remain) ? 1 : 0);
        }
        for (int i = 0; i < n; ++i) {
            out.print(h[i] + " ");
        }
        out.println();
    }
}
