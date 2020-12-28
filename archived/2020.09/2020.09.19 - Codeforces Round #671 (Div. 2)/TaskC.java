package current;

import algorithms.ArrayUtils;
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
            int[] a = in.nextIntArray(n);
            boolean allSame = true;
            boolean have = false;
            for (int k : a) {
                if (k != x) allSame = false;
                if (k == x) have = true;
            }
            if (allSame) {
                out.println(0);
                continue;
            }
            long sum = ArrayUtils.sum(a);
            if ((sum % n == 0 && sum / n == x) || have) {
                out.println(1);
            } else {
                out.println(2);
            }
        }
    }
}
