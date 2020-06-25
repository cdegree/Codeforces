package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = 0;
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = in.nextIntArray(n);
            int[] sum = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                if (a[i] >= k) {
                    sum[i + 1]++;
                }
                sum[i + 1] += sum[i];
            }
        }
    }
}
