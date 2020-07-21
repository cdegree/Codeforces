package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n + n);
            boolean[] used = new boolean[n + 1];
            int[] res = new int[n];
            int j = 0;
            for (int i = 0; i < n + n; ++i) {
                if (!used[a[i]]) {
                    used[a[i]] = true;
                    res[j++] = a[i];
                }
            }
            for (int i = 0; i < n; ++i) {
                out.print(res[i] + " ");
            }
        }
    }
}
