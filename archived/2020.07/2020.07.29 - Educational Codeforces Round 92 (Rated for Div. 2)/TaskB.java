package current;

import fastio.InputReader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int z = in.nextInt();
            int[] a = in.nextIntArray(n);
            long[] prefixSum = new long[n + 1];
            for (int i = 1; i <= n; ++i) {
                prefixSum[i] = prefixSum[i - 1] + a[i - 1];
            }
            long res = 0;
            for (int i = 1; i <= k; ++i) {
                for (int j = 0; j <= Math.min(z, 5); ++j) {
                    if (k - j * 2 + 1 >= i && i < n) {
                        //System.out.println(String.format("%d  %d %d %d", k - j * 2 + 1, i, j, prefixSum[k - j * 2 + 1] + (a[i - 1] + a[i]) * j));
                        res = Math.max(res, prefixSum[k - j * 2 + 1] + (a[i - 1] + a[i]) * j);
                    }
                }
            }
            out.println(res);
        }
    }
}
