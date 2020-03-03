package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int[] a = in.nextIntArray1(n);
        long[] prefix = new long[n + 2];
        long[] suffix = new long[n + 2];
        long mul = 1;
        while (k-- > 0) {
            mul *= x;
        }
        for (int i = 1; i <= n; ++i) {
            prefix[i] = prefix[i - 1] | a[i];
        }
        for (int i = n; i >= 1; --i) {
            suffix[i] = suffix[i + 1] | a[i];
        }
        long res = 0;
        for (int i = 1; i <= n; ++i) {
            res = Math.max(res, prefix[i - 1] | (a[i] * mul) | suffix[i + 1]);
        }
        out.println(res);
    }
}
