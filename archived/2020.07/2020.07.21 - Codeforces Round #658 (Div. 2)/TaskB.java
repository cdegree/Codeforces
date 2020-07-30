package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int index = n - 1;
            for (int i = 0; i < n; ++i) {
                if (a[i] > 1) {
                    index = i;
                    break;
                }
            }
            if (index % 2 == 0) {
                out.println("First");
            } else {
                out.println("Second");
            }
        }
    }
}
