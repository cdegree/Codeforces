package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int n;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        char[][] res = new char[n][n];

        int[][] x = new int[n][n];
        int[][] y = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                x[i][j] = in.nextInt() - 1;
                y[i][j] = in.nextInt() - 1;
                if (x[i][j] == i && y[i][j] == j) {
                    res[i][j] = 'X';
                }
            }
        }
        pf(res, out);

    }

    boolean inside(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    void dfs(char[][] res, int r, int c, int[][] x, int[][] y) {

        for (int k = 0; k < 4; ++k) {
            int nx = r + dx[k];
            int ny = c + dy[k];
            if (inside(nx, ny) && res[nx][ny] != 'X') {

            }
        }
    }

    void pf(char[][] r, PrintWriter out) {
        int n = r.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                out.print(r[i][j]);
            }
            out.println();
        }
    }
}
