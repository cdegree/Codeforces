package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long l = in.nextLong();
            long r = in.nextLong();
            if (l * 2 <= r) {
                out.println(l + " " + l * 2);
            } else {
                out.println(-1 + " " + -1);
            }
        }
    }
}
