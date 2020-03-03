package current;

import fastio.InputReader;

import java.io.PrintWriter;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        long[] offSum = new long[800005];
        long res = 0;
        int off = 200000;
        for (int i = 0; i < n; ++i) {
            offSum[a[i] - i + off] += a[i];
            res = Math.max(offSum[a[i] - i + off], res);
        }
        out.println(res);
    }
}
