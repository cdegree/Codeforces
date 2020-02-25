package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = in.nextIntArray1(n);
            int[] p = in.nextIntArray(m);
            Arrays.sort(p);
            for (int i = 0; i < m; ) {
                int left = p[i];
                int right = p[i] + 1;
                int j = i + 1;
                while (j < m && right == p[j]) {
                    right = p[j] + 1;
                    ++j;
                }
                i = j;
                Arrays.sort(a, left, right + 1);
            }
            boolean OK = true;
            for (int i = 1; i < n; ++i) {
                if (a[i] > a[i + 1]) {
                    OK = false;
                }
            }
            out.println(OK ? "YES" : "NO");
        }
    }
}
