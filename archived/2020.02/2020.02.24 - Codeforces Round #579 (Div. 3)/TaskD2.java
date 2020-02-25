package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD2 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] s = in.nextLine().toCharArray();
        char[] t = in.nextLine().toCharArray();
        int m = t.length;
        int n = s.length;
        int[] leftMost = new int[m];
        int[] rightMost = new int[m];
        int j = m - 1;
        for (int i = n - 1; i >= 0; --i) {
            if (j >= 0 && s[i] == t[j]) {
                rightMost[j] = i;
                --j;
            }
        }
        j = 0;
        int res = rightMost[0];
        for (int i = 0; i < n; ++i) {
            if (j < m && s[i] == t[j]) {
                if (j + 1 < m) {
                    res = Math.max(res, rightMost[j + 1] - i - 1);
                } else {
                    res = Math.max(res, n - i - 1);
                }
                ++j;
            }
        }
        out.println(res);
    }
}
