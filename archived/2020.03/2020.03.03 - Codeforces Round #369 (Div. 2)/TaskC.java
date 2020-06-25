package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] c = in.nextIntArray(n);
        int[][] p = new int[n][m];
        for (int i = 0; i < n; ++i) {
            p[i] = in.nextIntArray(m);
        }

    }

    int sumCost(int[] c, boolean[] changes, int[][] p) {
        int n = c.length;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            if (changes[i]) {
                sum += p[i][c[i] - 1];
            }
        }
        return sum;
    }

    int countBeauty(int[] c) {
        int n = c.length;
        int last = -1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (last != c[i]) {
                ++res;
                last = c[i];
            }
        }
        return res;
    }
}
