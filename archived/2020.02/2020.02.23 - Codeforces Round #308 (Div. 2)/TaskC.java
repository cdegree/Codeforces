package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {

    long[] sum = new long[105];
    long[] pw = new long[105];
    boolean OK = false;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long w = in.nextLong();
        long m = in.nextLong();
        long p = 1;
        int mx = 0;
        for (int i = 0; i < 100; ++i) {
            sum[i + 1] = p + sum[i];
            pw[i] = p;
            p *= w;
            if (p > 1L << 40) {
                mx = i;
                break;
            }
        }
        process(0, m, mx);
        out.println(OK ? "YES" : "NO");
    }

    void process(long cur, long target, int pos) {
        if (cur == target) {
            OK = true;
            return;
        }
        long remaining = sum[pos + 1];
        long next = pw[pos];
        if (cur - remaining > target) {
        } else if (cur + remaining < target) {
        } else {
            if (cur + sum[pos] < target) {
                process(cur + next, target, pos - 1);
            } else if (cur - sum[pos] > target) {
                process(cur - next, target, pos - 1);
            } else {
                process(cur, target, pos - 1);
            }
        }
    }
}
