package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.nextIntArray(n);

        int left = 1;
        int right = 1000000000;
        int ans = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (judge(mid, a, k)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        out.println(ans);
    }

    boolean judge(int min, int[] a, int k) {
        int m = (k + 1) / 2;
        int cnt = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] <= min) {
                ++cnt;
                i++;
            }
            if (cnt == m) {
                if (k % 2 == 1) {
                    return true;
                } else {
                    if (i < a.length) {
                        return true;
                    }
                }
            }
        }
        m = k / 2;
        cnt = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] <= min) {
                ++cnt;
                ++i;
            }
            if (cnt == m) {
                if (k % 2 == 0) {
                    return true;
                } else {
                    if (i < a.length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
