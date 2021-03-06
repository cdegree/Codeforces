package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            boolean down = false;
            int j = -1;
            for (int i = n - 2; i >= 0; --i) {
                if (!down) {
                    if (a[i] < a[i + 1]) {
                        down = true;
                    }
                } else {
                    if (a[i] > a[i + 1]) {
                        j = i;
                        break;
                    }
                }
            }
            out.println(j + 1);
        }
    }
}
