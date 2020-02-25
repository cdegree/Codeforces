package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long x = in.nextLong();
            long y = in.nextLong();
            boolean OK = false;
            if (x > y) {
                OK = true;
            } else if (x == y) {
                OK = true;
            } else {
                if (x == 1) {
                    OK = false;
                } else if (x == 2 || x == 3) {
                    if (y <= 3) {
                        OK = true;
                    }
                } else {
                    OK = true;
                }
            }
            out.println(OK ? "YES" : "NO");
        }
    }
}
