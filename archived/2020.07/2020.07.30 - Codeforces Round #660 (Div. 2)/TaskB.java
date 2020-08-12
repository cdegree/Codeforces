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
            int n8 = (n + 3) / 4;
            int n9 = n - n8;
            for (int i = 0; i < n9; ++i) {
                out.print(9);
            }
            for (int i = 0; i < n8; ++i) {
                out.print(8);
            }
            out.println();
        }
    }
}
