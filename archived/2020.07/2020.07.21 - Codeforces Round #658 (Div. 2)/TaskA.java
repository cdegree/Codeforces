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
            int m = in.nextInt();
            int[] a = in.nextIntArray(n);
            int[] b = in.nextIntArray(m);
            boolean[] used = new boolean[1004];
            for (int x : a) {
                used[x] = true;
            }
            int r = -1;
            for (int x : b) {
                if (used[x]) {
                    r = x;
                    break;
                }
            }
            if (r == -1) {
                out.println("NO");
            } else {
                out.println("YES");
                out.println(1 + " " + r);
            }
        }
    }
}
