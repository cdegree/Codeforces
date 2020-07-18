package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int pos = -1;
            for (int i = 1; i < n - 1; ++i) {
                if (a[i - 1] < a[i] && a[i] > a[i + 1]) {
                    pos = i + 1;
                }
            }
            if (pos == -1) {
                out.println("NO");
            } else {
                out.println("YES");
                out.println(String.format("%d %d %d", pos - 1, pos, pos + 1));
            }
        }
    }
}
