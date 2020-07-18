package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            int n = in.nextInt();
            out.println((n - y) / x * x + y);
        }
    }
}
