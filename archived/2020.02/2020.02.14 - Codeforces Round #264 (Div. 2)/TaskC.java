package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    int n;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        long[] slash = new long[2 * n - 1];
        long[] counterSlash = new long[2 * n - 1];
        int[][] a = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                a[i][j] = in.nextInt();
                int slashIndex = positionToSlashIndex(i, j);
                int cSlashIndex = positionToCounterSlashIndex(i, j);
                slash[slashIndex] += a[i][j];
                counterSlash[cSlashIndex] += a[i][j];
            }
        }
        long mx1 = -1, mx2 = -1;
        int idx1 = 0, idy1 = 0, idx2 = 0, idy2 = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int slashIndex = positionToSlashIndex(i, j);
                int cSlashIndex = positionToCounterSlashIndex(i, j);
                long crossValue = slash[slashIndex] + counterSlash[cSlashIndex] - a[i][j];
                if (toCategory(i, j) == 0) {
                    if (mx1 < crossValue) {
                        mx1 = crossValue;
                        idx1 = i + 1;
                        idy1 = j + 1;
                    }
                } else {
                    if (mx2 < crossValue) {
                        mx2 = crossValue;
                        idx2 = i + 1;
                        idy2 = j + 1;
                    }
                }
            }
        }
        out.println(mx1 + mx2);
        out.println(idx1 + " " + idy1 + " " + idx2 + " " + idy2);
    }

    int toCategory(int x, int y) {
        return (x + y) % 2;
    }

    int positionToSlashIndex(int x, int y) {
        return x + y;
    }

    int positionToCounterSlashIndex(int x, int y) {
        return x - y + n - 1;
    }

}
