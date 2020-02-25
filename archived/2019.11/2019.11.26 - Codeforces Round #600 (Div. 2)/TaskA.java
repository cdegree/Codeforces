package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            int[] b = in.readIntArray(n);
            int prediff = -1;
            int status = 0;
            boolean OK = true;
            for (int i = 0; i < n; ++i) {
                int diff = b[i] - a[i];
                if (diff < 0) {
                    OK = false;
                } else if (diff > 0) {
                    if (status == 2) {
                        OK = false;
                    } else if (status == 0) {
                        status = 1;
                        prediff = diff;
                    } else if (status == 1) {
                        if (prediff != diff) {
                            OK = false;
                        }
                    }
                } else {
                    if (status == 1) {
                        status = 2;
                    }
                }
            }
            out.println(OK ? "YES" : "NO");
        }
    }
}
