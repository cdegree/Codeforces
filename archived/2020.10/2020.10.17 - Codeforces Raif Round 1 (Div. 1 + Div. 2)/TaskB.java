package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            boolean[] returnable = new boolean[n + 1];
            boolean hasLeft = false;
            boolean hasRight = false;
            for (int i = 0; i < n; ++i) {
                if (s[i] == '-') {
                    returnable[i] = true;
                    returnable[(i + 1) % n] = true;
                }
                if (s[i] == '<') {
                    hasLeft = true;
                }
                if (s[i] == '>') {
                    hasRight = true;
                }
            }
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (returnable[i]) ++cnt;
            }
            if (!hasLeft || !hasRight) {
                out.println(n);
            } else {
                out.println(cnt);
            }
        }
    }
}
