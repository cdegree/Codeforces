package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int w = in.nextInt();
        long[] a = in.nextLongArray(n);
        long left = 1, right = 100000000000000L;
        long res = 0;
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (able(a, mid, m, w)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        out.println(res);
    }

    boolean able(long[] a, long target, int m, int w) {
        int n = a.length;
        long[] plus = new long[n + 1];
        long add = 0;
        for (int i = 0; i < n; ++i) {
            add += plus[i];
            if (a[i] + add < target) {
                long need = target - a[i] - add;
                if (m < need) {
                    return false;
                } else {
                    m -= need;
                    add += need;
                    plus[Math.min(i + w, n)] = -need;
                }
            }
        }
        return true;
    }
}
