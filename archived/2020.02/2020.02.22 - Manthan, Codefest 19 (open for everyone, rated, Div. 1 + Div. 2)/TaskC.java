package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int cnt = 0;
        int[][] a = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                a[i][j] = transform(i, j, n);
                out.print(a[i][j] + " ");
            }
            out.println();
        }
    }

    int transform(int i, int j, int n) {
        int col = j / 4;
        int row = i / 4;
        int ret = 4 * n * row + col * 16;
        int srow = i % 4;
        int scol = j % 4;
        ret += srow * 4 + scol;
        return ret;
    }
}
