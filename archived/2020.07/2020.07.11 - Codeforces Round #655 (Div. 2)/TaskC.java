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
            boolean equal = false;
            boolean sorted = true;
            int status = -1;
            for (int i = 0; i < n; ++i) {
                if (a[i] == i + 1) {
                    if (status == 0) {
                        status = 1;
                    }
                } else {
                    if (status == -1) {
                        status = 0;
                    } else if (status == 1) {
                        status = 2;
                    }
                    sorted = false;
                }
            }
            if (sorted) {
                out.println(0);
            } else if (status == 2) {
                out.println(2);
            } else {
                out.println(1);
            }
        }
    }
}
