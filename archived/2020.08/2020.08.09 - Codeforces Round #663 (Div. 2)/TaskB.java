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
            int m = in.nextInt();
            char[][] s = new char[n][];
            for (int i = 0; i < n; ++i) {
                s[i] = in.nextLine().toCharArray();
            }
            int ans = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (s[i][m - 1] == 'R') {
                    ans++;
                }
            }
            for (int j = 0; j < m - 1; ++j) {
                if (s[n - 1][j] == 'D') {
                    ++ans;
                }
            }
            out.println(ans);
        }
    }
}
