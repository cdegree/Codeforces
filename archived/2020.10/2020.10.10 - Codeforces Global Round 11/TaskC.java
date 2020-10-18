package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskC {
    int[] t;
    int[] x;
    int[] y;
    int n;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int r = in.nextInt();
        n = in.nextInt();
        t = new int[n + 1];
        x = new int[n + 1];
        y = new int[n + 1];
        int[] maxCeleToT = new int[n + 1];
        Arrays.fill(maxCeleToT, -1 * (1 << 30));
        int[] maxTillNow = new int[n + 1];

        maxCeleToT[0] = 0;

        for (int i = 1; i <= n; ++i) {
            t[i] = in.nextInt();
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        t[0] = 0;
        x[0] = 1;
        y[0] = 1;
        int max = 0;
        for (int i = 1; i <= n; ++i) {
            //ArrayList<Integer> ids = new ArrayList<>(100);
            for (int j = Math.max(0, i - 1000); j < i; ++j) {
                if (reachable(j, i) && maxCeleToT[j] >= 0) {
                    if (maxCeleToT[j] + 1 > maxCeleToT[i]) {
                        maxCeleToT[i] = maxCeleToT[j] + 1;
                    }
                    max = Math.max(max, maxCeleToT[i]);
                }
            }
            if (i - 1000 > 0) {
                maxCeleToT[i] = Math.max(maxCeleToT[i], maxTillNow[i - 1000] + 1);
                max = Math.max(max, maxCeleToT[i]);
            }
            maxTillNow[i] = max;
        }
        out.println(max);
    }

    boolean reachable(int i, int j) {
        return (t[j] - t[i]) >= Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);
    }
}
