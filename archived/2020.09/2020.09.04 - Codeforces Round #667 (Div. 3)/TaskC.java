package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int diff = y - x;
            int[] a = new int[n];
            int best = 1 << 30;
            for (int len = 1; len <= diff; ++len) {
                if ((y - x) % len == 0) {
                    int count = (y - x) / len - 1 + 2;
                    if (count <= n) {
                        int start = y - len * (n - 1);
                        if (start <= 0) {
                            start = (y % len == 0) ? (y % len + len) : (y % len);
                        }
                        int max = start + (n - 1) * len;
                        if (best > max) {
                            best = max;
                            for (int i = 0; i < n; ++i) {
                                a[i] = start + i * len;
                            }
                        }
                    }
                }
            }
            for (int k : a) {
                out.print(k + " ");
            }
            out.println();
        }
    }
}
