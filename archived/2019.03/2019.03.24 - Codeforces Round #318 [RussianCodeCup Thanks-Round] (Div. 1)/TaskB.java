package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] h = in.readIntArray(n);
        int[] left = new int[n];
        int[] right = new int[n];
        int hi = 0;
        for (int i = 0; i < n; ++i) {
            ++hi;
            if (hi > h[i]) {
                hi = h[i];
            }
            left[i] = hi;
        }
        hi = 0;
        for (int i = n - 1; i >= 0; --i) {
            ++hi;
            if (hi > h[i]) {
                hi = h[i];
            }
            right[i] = hi;
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, Math.min(left[i], right[i]));
        }
        out.println(mx);
    }
}
