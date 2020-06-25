package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int[] sum = new int[n + 1];
            for (int i = 0; i < n; ++i) sum[i + 1] = sum[i] + a[i];
            boolean[] exists = new boolean[n + 1];
            for (int i = 0; i < n; ++i) {
                for (int j = i + 2; j <= n && sum[j] - sum[i] <= n; ++j) {
                    exists[sum[j] - sum[i]] = true;
                }
            }
            int res = 0;
            for (int i = 0; i < n; ++i) {
                res += exists[a[i]] ? 1 : 0;
            }
            out.println(res);
        }
    }
}
