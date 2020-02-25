package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long a = in.nextLong();
            long b = in.nextLong();
            if (a > b) {
                long t = a;
                a = b;
                b = t;
            }
            boolean OK = true;
            if (2 * a < b) {
                OK = false;
            } else {
                long diff = b - a;
                a -= diff;
                b -= 2 * diff;
                if (a % 3 != 0) {
                    OK = false;
                }
            }
            out.println(OK ? "YES" : "NO");
        }
    }
}
