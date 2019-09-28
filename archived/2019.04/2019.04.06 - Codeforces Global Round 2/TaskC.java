package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];
        for (int i = 0; i < n; ++i) {
            a[i] = in.readIntArray(m);
        }
        for (int i = 0; i < n; ++i) {
            b[i] = in.readIntArray(m);
        }
//        for (int i = 0; i < n; ++i) {
//            for (int j = 0; j < m; ++j) {
//                System.out.print(a[i][j]);
//            }
//            System.out.println();
//        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (a[i][j] != b[i][j]) {
                    int ni = i + 1;
                    int nj = j + 1;
                    while (ni < n && a[ni][j] == b[ni][j]) ++ni;
                    while (nj < m && a[i][nj] == b[i][nj]) ++nj;
                    //System.out.println(String.format("found one %d %d %d %d", i, j, ni, nj));
                    if (ni < n && nj < m) {
                        //System.out.println(String.format("found one %d %d %d %d", i, j, ni, nj));
                        a[i][j] ^= 1;
                        a[ni][j] ^= 1;
                        a[i][nj] ^= 1;
                        a[ni][nj] ^= 1;
                    }
                }
            }
        }
        boolean ok = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (a[i][j] != b[i][j]) {
                    ok = false;
                }
            }
        }
        out.println(ok ? "Yes" : "No");
    }
}
