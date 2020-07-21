package current;

import algorithms.Pair;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            Vector<Pair> pairs = new Vector<>(m);
            for (int i = 0; i < m; ++i) {
                int a = in.nextInt();
                int b = in.nextInt();
                pairs.add(new Pair(a, b));
            }
            pairs.sort(((o1, o2) -> o2.x - o1.x));
            long[] prefixSum = new long[m + 2];
            long res = 0;
            for (int i = 1; i <= m; ++i) {
                prefixSum[i] = prefixSum[i - 1] + pairs.get(i - 1).x;
            }
            for (int i = 0; i < m; ++i) {
                long b = pairs.get(i).y;
                int left = 1;
                int right = Math.min(n, m);
                int index = -1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (pairs.get(mid - 1).x > b) {
                        left = mid + 1;
                        index = mid;
                    } else {
                        right = mid - 1;
                    }
                }
                if (index == -1) {
                    res = Math.max(res, pairs.get(i).x + (long) (n - 1) * pairs.get(i).y);
                } else {
                    if (index - 1 >= i) {
                        res = Math.max(res, prefixSum[index] + (long) (n - index) * pairs.get(i).y);
                    } else {
                        res = Math.max(res, prefixSum[index] + ((n - index - 1 >= 0) ? (pairs.get(i).x + (long) (n - index - 1) * pairs.get(i).y) : 0));
                    }
                }
            }
            out.println(res);
        }
    }
}
