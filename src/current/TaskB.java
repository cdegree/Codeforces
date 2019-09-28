package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int H = in.nextInt();
        int[] a = in.readIntArray(m);
        int[] b = in.readIntArray(n);
        int[][] h = new int[n][m];
        for (int i = 0; i < n; ++i) {
            h[i] = in.readIntArray(m);
        }

        for (int i = 0; i < n; ++i) {
            int index = -1;
            if (b[i] == 0) {
                continue;
            }
            for (int j = 0; j < m; ++j) {
                if (h[i][j] > 0 && b[i] <= a[j]) {
                    index = j;
                }
            }
            if (index == -1) {
                out.println("not found for col " + i);
                return;
            }
            h[i][index] = b[i];
        }

        for (int j = 0; j < m; ++j) {
            int index = -1;
            if (a[j] == 0) {
                continue;
            }
            for (int i = 0; i < n; ++i) {
                if (h[i][j] > 0 && a[j] <= b[i]) {
                    index = i;
                }
            }
            if (index == -1) {
                out.println("not found for row " + j);
                return;
            }
            h[index][j] = a[j];
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                out.print(h[i][j] + " ");
            }
            out.println();
        }
    }
}
