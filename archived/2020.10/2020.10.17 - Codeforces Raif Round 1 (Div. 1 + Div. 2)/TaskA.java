package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            if (x1 == x2) {
                out.println(Math.abs(y1 - y2));
            } else if (y1 == y2) {
                out.println(Math.abs(x1 - x2));
            } else {
                out.println(Math.abs(x1 - x2) + Math.abs(y1 - y2) + 2);
            }
        }
    }
}
