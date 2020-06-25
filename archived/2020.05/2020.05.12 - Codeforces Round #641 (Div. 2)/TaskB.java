package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        int N = 100000;

        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray1(n);
            int[] len = new int[n + 1];
            int res = 0;
            for (int i = 1; i <= n; ++i) {
                len[i] = 1;
                for (int div : MathUtil.getDivisors(i)) {
                    if(a[div] < a[i]){
                        len[i] = Math.max(len[i], len[div] + 1);
                    }
                }
                res = Math.max(res, len[i]);
            }
            out.println(res);
        }
    }
}
