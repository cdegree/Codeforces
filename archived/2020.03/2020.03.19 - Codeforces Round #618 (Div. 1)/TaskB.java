package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; ++i) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        if (n % 2 == 0) {
            for (int i = 0; i < n / 2 - 1; ++i) {
                int j = i + n / 2;
                int x1 = x[i] + x[j];
                int y1 = y[i] + y[j];
                int x2 = x[i + 1] + x[j + 1];
                int y2 = y[i + 1] + y[j + 1];
                if (x1 != x2 || y1 != y2) {
                    out.println("NO");
                    return;
                }
            }
            out.println("YES");
            return;
        }
        out.println("NO");
    }
}
