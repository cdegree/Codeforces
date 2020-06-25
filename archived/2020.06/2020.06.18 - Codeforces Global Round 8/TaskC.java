package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] x = new int[n * 8];
        int[] y = new int[n * 8];
        x[0] = 0;
        x[1] = 0;
        x[2] = 1;
        x[3] = 1;
        x[4] = 1;
        x[5] = 2;
        x[6] = 2;

        y[0] = 0;
        y[1] = 1;
        y[2] = 0;
        y[3] = 1;
        y[4] = 2;
        y[5] = 1;
        y[6] = 2;
        int cnt = 7;
        for (int i = 2; i <= n; ++i) {
            x[cnt] = i;
            y[cnt++] = i + 1;
            x[cnt] = i + 1;
            y[cnt++] = i;
            x[cnt] = i + 1;
            y[cnt++] = i + 1;
        }
        out.println(cnt);
        for (int i = 0; i < cnt; ++i) {
            out.println(x[i] + " " + y[i]);
        }
    }
}
