package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.nextIntArray(n);
        int res = 0;
        int left = 0, right = m;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int pre = 0;
            boolean OK = true;
            for (int i = 0; i < n; ++i) {
                int low = a[i];
                int high = a[i] + mid;
                if (high >= m) {
                    int s1High = high % m;
                    if (pre < a[i] && pre > s1High) {
                        pre = a[i];
                    }
                } else {
                    if (pre <= high) {
                        pre = Math.max(pre, low);
                    } else {
                        OK = false;
                        break;
                    }
                }
            }
            if (OK) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        out.println(res);
    }
}
